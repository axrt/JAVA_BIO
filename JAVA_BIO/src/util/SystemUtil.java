package util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SystemUtil {
    /**
     * NCBI FTP server address
     */
    private static String NCBI_FTP = "ftp.ncbi.nlm.nih.gov";
    /**
     * Anonymous ftp login/password
     */
    private static String ANONYMOUS = "anonymous";
    /**
     * NCBI FTP server taxonomy subfolder
     */
    private static String NCBI_TAXONOMY = "/pub/taxonomy/";
    /**
     * NCBI FTP server address for taxonomy files
     */
    private static String NCBI_TAXONOMY_FTP = SystemUtil.NCBI_FTP + SystemUtil.NCBI_TAXONOMY;
    /**
     * Dump file .dmp extension
     */
    private static String DMP_SUF = ".dmp";
    /**
     * Archive format suffix
     */
    private static String ARCH_SUF = ".gz";
    /**
     * Perfix for the gi_taxid database dump
     */
    private static String GI_TAXID = "gi_taxid";
    /**
     * GI_TAXID dump file archive name
     */
    private static String GI_TAXID_ARCH = SystemUtil.GI_TAXID + "_nucl_diff" + SystemUtil.DMP_SUF + SystemUtil.ARCH_SUF;
    /**
     * GI_TAXID dump file name
     */
    private static String GI_TAXID_FILE = "gi_taxid" + SystemUtil.DMP_SUF;
    /**
     * Perfix for the nodes database dump
     */
    private static String NODES = "nodes";
    /**
     * Nodes dump file name
     */
    private static String NODES_FILE = "nodes" + SystemUtil.DMP_SUF;
    /**
     * Perfix for the nodes database dump
     */
    private static String NAMES = "names";
    /**
     * Nodes dump file name
     */
    private static String NAMES_FILE = "names" + SystemUtil.DMP_SUF;
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
     * Downloads a file for a given file name from the NCBI FTP (Taxonomy directory) server
     * @param tmpDownloadDir {@link File} a temporary directory to store the files downloaded
     * @param fileName {@link String} requested file name
     * @param subDir {@link File} a subdirectory to download file from
     * @return {@link File} pointer to the file that has been retrieved and saved locally to the temporary folder
     * @throws java.io.IOException in case anything goes wrong during the ftp communication of file saving locally
     */
    public static File downloadFileFromNCBIFTP(File tmpDownloadDir,File subDir, String fileName) throws IOException {

        //Prepare an FTP client
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fileOutputStream = null;
        File outputFile = null;

        try {
            //Connect to the server
            ftpClient.connect(SystemUtil.NCBI_FTP);
            //Login
            ftpClient.login(SystemUtil.ANONYMOUS, SystemUtil.ANONYMOUS);
            //Set binary mode
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //Change the directory to the one that contains taxonomic infomation
            ftpClient.cwd(subDir.getAbsolutePath());
            //Prepare the outputstream to save the file
            fileOutputStream = new FileOutputStream(outputFile = new File(tmpDownloadDir.getAbsoluteFile() + SystemUtil.SysFS + fileName));
            //Download the required file
            ftpClient.retrieveFile(fileName, fileOutputStream);

        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if(fileOutputStream!=null){
                fileOutputStream.close();
            }
            ftpClient.disconnect();
        }
        return outputFile;
    }
}
