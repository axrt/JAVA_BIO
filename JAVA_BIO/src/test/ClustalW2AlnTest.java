package test;

import format.clustal.AlignmentException;
import format.clustal.ClustalW2Aln;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: alext
 * Date: 7/5/13
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClustalW2AlnTest {
    @Test
    public void toStringTest(){
        File alnFile=new File("/home/alext/Documents/tuit/otu Seq90/test.aln");
        try {
            ClustalW2Aln clustalW2Aln=ClustalW2Aln.newInstaceFromFile(alnFile);
            //System.out.println(clustalW2Aln.toString());
            System.out.println(clustalW2Aln.getConsensus(50));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (AlignmentException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
