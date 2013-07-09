package test;

import format.clustal.AlignmentException;
import format.fasta.Fasta;
import format.fasta.nucleotide.NucleotideFasta_AC_BadFormatException;
import format.fasta.nucleotide.NucleotideFasta_BadFromat_Exception;
import format.fasta.nucleotide.NucleotideFasta_Sequence_BadFromatException;
import format.fasta.otu.EncodedOTU;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: alext
 * Date: 7/9/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class OTUConsensusConversionTest {

    @Test
    public void testOTUConsensusConversion() {
        File otuDir = new File("/home/alext/Documents/tuit/OTU Seq90/Seqs");
        File tmpDir = new File("/home/alext/Downloads/tmp");
        File executable = new File("/usr/local/bin/clustalw2");
        File[] otuFiles = otuDir.listFiles();
        for (File f : otuFiles) {

            if (f.getPath().endsWith(".fasta")) {
                try {
                    EncodedOTU encodedOTU = EncodedOTU.newDefaultInstanceFromFile(f, tmpDir, executable);
                    String fileName = f.getName().split(".fasta")[0];
                    BufferedWriter bufferedWriter = null;
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(new File(f.getParent(), fileName + ".cons")));
                        bufferedWriter.write(Fasta.fastaStart+fileName);
                        bufferedWriter.newLine();
                        bufferedWriter.write(encodedOTU.getOtuConsensus(51));
                        bufferedWriter.flush();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (AlignmentException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } finally {
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NucleotideFasta_AC_BadFormatException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NucleotideFasta_Sequence_BadFromatException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NucleotideFasta_BadFromat_Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
    }


}
