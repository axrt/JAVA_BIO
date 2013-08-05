package test;

import format.clustal.AlignmentException;
import format.fasta.nucleotide.NucleotideFasta_AC_BadFormatException;
import format.fasta.nucleotide.NucleotideFasta_BadFromat_Exception;
import format.fasta.nucleotide.NucleotideFasta_Sequence_BadFormatException;
import format.fasta.otu.EncodedOTU;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: alext
 * Date: 7/8/13
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class EncodedOTUTest {
    @Test
    public void testEncodedOTU(){

        File otuFile=new File("/home/alext/Documents/tuit/OTU Seq90/Seqs/Otu1417.fasta");
        File tmpDir=new File("/home/alext/Downloads/tmp");
        File executable=new File("/usr/local/bin/clustalw2");
        try {
            EncodedOTU encodedOTU=EncodedOTU.newDefaultInstanceFromFile(otuFile,tmpDir,executable);
            System.out.println(encodedOTU.getOtuConsensus(90));
        } catch (IOException | NucleotideFasta_AC_BadFormatException | NucleotideFasta_Sequence_BadFormatException | NucleotideFasta_BadFromat_Exception | InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (AlignmentException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
