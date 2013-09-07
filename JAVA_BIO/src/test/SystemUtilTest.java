package test;

import org.junit.Test;
import util.SystemUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: alext
 * Date: 6/6/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class SystemUtilTest {

    //@Test
    public void unArchiveTarGZFileTest() throws IOException {
        final File inputTarGZFile= new File("/home/alext/Downloads/ncbi/taxdump.tar.gz");
        final File outputDir=new File("/home/alext/Downloads/tmp");

        SystemUtil.unArchiveTarGZFile(inputTarGZFile,outputDir);

    }

    @Test
    public void downloadFileFromNCBIFTPTest() {

        final File tmpDownloadDir =  new File("/home/alext/Downloads/tmp");
        final String subDir  = SystemUtil.NCBI_TAXONOMY;
        final String fileName = SystemUtil.TAXDUMP_ARCH;

        try {
            SystemUtil.downloadFileFromNCBIFTP(tmpDownloadDir,subDir,fileName);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    //@Test
    public void unArchiveGZFileTest() throws IOException {
        final File inputGZFile= new File("/home/alext/Downloads/ncbi/gi_taxid_nucl.dmp.gz");
        final File outputDir=new File("/home/alext/Downloads/tmp");

        SystemUtil.unArchiveGZFile(inputGZFile,outputDir);

    }

}
