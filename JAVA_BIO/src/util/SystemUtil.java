package util;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.util.zip.GZIPInputStream;

import com.ice.tar.TarInputStream;
import com.ice.tar.TarEntry;
import org.apache.commons.net.ftp.FTPFile;

public class SystemUtil {
    /**
     * ncbi FTP server address
     */
    public static final String NCBI_FTP = "ftp.ncbi.nlm.nih.gov";
    /**
     * Anonymous ftp login/password
     */
    public static final String ANONYMOUS = "anonymous";
    /**
     * ncbi FTP server taxonomy subfolder
     */
    public static final String NCBI_TAXONOMY = "/pub/taxonomy/";
    /**
     * ncbi FTP server address for taxonomy files
     */
    public static final String NCBI_TAXONOMY_FTP = SystemUtil.NCBI_FTP + SystemUtil.NCBI_TAXONOMY;
    /**
     * Dump file .dmp extension
     */
    public static final String DMP_SUF = ".dmp";
    /**
     * Archive format suffix
     */
    public static final String TAR_SUF = ".tar";
    /**
     * Tar format suffix
     */
    public static final String ARCH_SUF = ".gz";
    /**
     * Tar.GZ format suffix
     */
    public static final String TAR_GZ_SUF = SystemUtil.TAR_SUF+SystemUtil.ARCH_SUF;
    /**
     * Perfix for the gi_taxid database dump
     */
    public static final String GI_TAXID = "gi_taxid";
    /**
     * GI_TAXID nucl unarchived file
     */
    public static final String GI_TAXID_NUCL= SystemUtil.GI_TAXID+"_nucl"+ SystemUtil.DMP_SUF;
    /**
     * GI_TAXID dump file archive name
     */
    public static final String GI_TAXID_DMP_ARCH =  GI_TAXID_NUCL+ SystemUtil.ARCH_SUF;
    /**
     * GI_TAXID dump diff file archive name
     */
    public static final String GI_TAXID_ARCH = SystemUtil.GI_TAXID + "_nucl_diff" + SystemUtil.DMP_SUF + SystemUtil.ARCH_SUF;
    /**
     * GI_TAXID dump file name
     */
    public static final String GI_TAXID_FILE = "gi_taxid" + SystemUtil.DMP_SUF;
    /**
     * GI_TAXID updates dump archive file name
     */
    public static final String GI_TAXID_UPD_FILE_ARCH = SystemUtil.GI_TAXID+"_nucl_diff" + SystemUtil.DMP_SUF+SystemUtil.ARCH_SUF;
    /**
     * GI_TAXID updates dump file name
     */
    public static final String GI_TAXID_UPD_FILE = SystemUtil.GI_TAXID+"_nucl_diff" + SystemUtil.DMP_SUF;
    /**
     * Perfix for the nodes database dump
     */
    public static final String NODES = "nodes";
    /**
     * Nodes dump file name
     */
    public static final String NODES_FILE = "nodes" + SystemUtil.DMP_SUF;
    /**
     * Perfix for the nodes database dump
     */
    public static final String NAMES = "names";
    /**
     * Nodes dump file name
     */
    public static final String NAMES_FILE = "names" + SystemUtil.DMP_SUF;
    /**
     * Taxdump name
     */
    public static final String TAXDUMP = "taxdump";
    /**
     * Taxdump archive file name
     */
    public static final String TAXDUMP_ARCH = SystemUtil.TAXDUMP +SystemUtil.TAR_GZ_SUF;
    /**
     * Constructor prevents instantiation
     */
    private SystemUtil() {
        throw new AssertionError();
    }

    /**
     * System file system path separator
     */
    public static final String SysFS = System.getProperty("file.separator");
    /**
     * User home directory
     */
    public static final String userHomeDir = System.getProperty("user.home",
            ".");
    /**
     * Platform (OS) name
     */
    public static final String platform = System.getProperty("os.name");
    /**
     * Capacity here is "32/64 bit" feature of the OS
     */
    public static final String capacity = System
            .getProperty("sun.arch.data.model");
    /**
     * A path to the jar that is being executed
     */
    public static final String jarPath = new File(SystemUtil.class
            .getProtectionDomain().getCodeSource().getLocation().getPath())
            .getParentFile().getParent();

    /**
     * Extracts a given tar.gz file into a given directory
     *
     * @param archiveFile {@link File} tar.gz file to extract
     * @param outputDir   {@link File} a directory to extract to
     * @throws IOException in case something goes wrong during file extract
     * @return {@link File} an abstract path of the directory where the archive
     * has been extracted
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File unArchiveTarGZFile(final File archiveFile, File outputDir) throws IOException {
        InputStream inputStream = null;
        OutputStream fileOutputStream;
        TarInputStream tarInputStream = null;

        outputDir=SystemUtil.createASubDirFromFileName(archiveFile, outputDir);
        outputDir.mkdir();

        try {
            inputStream = new FileInputStream(archiveFile);
            inputStream = new GZIPInputStream(inputStream);
            tarInputStream = new TarInputStream(inputStream);
            TarEntry tarEntry;
            byte[] buff = new byte[1024];
            while (null != (tarEntry = tarInputStream.getNextEntry())) {
                final File next = new File(outputDir, tarEntry.getName());
                int bytesRead;
                if (tarEntry.isDirectory()) {
                    if (!next.exists()) {
                        if (!next.mkdirs()) {
                            throw new IllegalStateException("Could not create a directory..");
                        }
                    }
                } else {   //TODO: correct this to IOUtils copy
                    fileOutputStream = new FileOutputStream(next);
                    while ((bytesRead = tarInputStream.read(buff, 0, 1024)) > -1) {
                        fileOutputStream.write(buff, 0, 1024);
                    }
                }
            }
        }  finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (tarInputStream != null) {
                tarInputStream.close();
            }

        }
        return outputDir;
    }

    /**
     * Extracts a given .gz file into a given directory
     *
     * @param archiveFile {@link File} tar.gz file to extract
     * @param outputDir   {@link File} a directory to extract to
     * @throws IOException in case something goes wrong during file extract
     * @return {@link File} an abstract path of the directory where the archive
     * has been extracted
     */
    public static File unArchiveGZFile(final File archiveFile, File outputDir) throws IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        outputDir=SystemUtil.createASubDirFromFileName(archiveFile, outputDir);
        outputDir.mkdir();

        try {
            inputStream = new FileInputStream(archiveFile);
            inputStream = new BufferedInputStream(inputStream);
            inputStream=new GzipCompressorInputStream(inputStream);
            final String[] split=archiveFile.getName().split("\\.gz");
            outputStream=new FileOutputStream(new File(outputDir,split[0]));
            IOUtils.copy(inputStream, outputStream);
        } finally {
            if(inputStream!=null){
               IOUtils.closeQuietly(inputStream);
            }
            if(outputStream!=null){
                IOUtils.closeQuietly(outputStream);
            }

        }
        return outputDir;
    }

    /**
     * Downloads a file for a given file name from the ncbi FTP (Taxonomy directory) server
     *
     * @param tmpDownloadDir {@link File} a temporary directory to store the files downloaded
     * @param subDir         {@link String} a subdirectory of the ncbi FTP to download file from
     * @param fileName       {@link String} requested file name
     * @return {@link File} pointer to the file that has been retrieved and saved locally to the temporary folder
     * @throws java.io.IOException in case anything goes wrong during the ftp communication of file saving locally
     */
    public static File downloadFileFromNCBIFTP(final File tmpDownloadDir, final String subDir, final String fileName) throws IOException {
        //TODO: create a separate utility class, make it extendable and make a more general abstraction for ftp downloads, remove all the printouts
        //Prepare an FTP client
        final FTPClient ftpClient = new FTPClient();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        File outputFile = null;

        try {
            //Connect to the server
            ftpClient.connect(SystemUtil.NCBI_FTP, 21);
            ftpClient.enterLocalPassiveMode();
            //Login
            ftpClient.login(SystemUtil.ANONYMOUS, "");
            System.out.print(ftpClient.getReplyString());
            //Set binary mode
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //Change the directory to the one that contains taxonomic information
            ftpClient.changeWorkingDirectory(subDir);
            //Prepare the outputstream to save the file
            System.out.print(ftpClient.getReplyString());
            outputStream = new FileOutputStream(outputFile=new File(tmpDownloadDir, fileName));
            System.out.print(ftpClient.getReplyString());
            inputStream = ftpClient.retrieveFileStream(fileName);
            System.out.print(ftpClient.getReplyString());
            IOUtils.copy(inputStream, outputStream);
        } finally {
            if (outputStream != null) {
                IOUtils.closeQuietly(outputStream);
            }
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
            ftpClient.logout();
            ftpClient.disconnect();
        }
        return outputFile;
    }

    /**
     * A utility method for the file unarchiving methods. In order to create a subdirectory to unarchive
     * a file it makes sense to create a name that is derivend from the archive file name. This method
     * tries to cut off everything form the beginning of the file (like tar.gz for example) and leaves only the name
     * for the subdirectoryt naming
     * @param file {@link File} to unarchive
     * @param outputDir {@link File} directory where the archive is destined to be uncompressed
     * @return {@link File} an abstract path to the subdirectory where the archive is being uncompressed
     */
    private static File createASubDirFromFileName(final File file, File outputDir) {
        //Create a subdirectory to store the files, which corresponds to the name of the archive
        String[] split = file.getName().split("\\.");
        if (split.length > 1) {
            //Checking for whether the file was a hidden by a dot in the beginning of the filename
            if(split[0].length()>0){
                outputDir = new File(outputDir, split[0]);
            }else{
                outputDir = new File(outputDir, split[1]);
            }

        } else {
            outputDir = new File(outputDir, file.getName() + "_dir");
        }
        outputDir.mkdir();
        return outputDir;
    }
}
