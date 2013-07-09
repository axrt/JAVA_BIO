package test;

import format.clustal.AlignmentException;
import format.fasta.Fasta;
import format.fasta.nucleotide.NucleotideFasta;
import format.fasta.nucleotide.NucleotideFasta_AC_BadFormatException;
import format.fasta.nucleotide.NucleotideFasta_BadFromat_Exception;
import format.fasta.nucleotide.NucleotideFasta_Sequence_BadFromatException;
import format.fasta.otu.AlignableOTU;
import format.fasta.otu.EncodedNucleotideFasta;
import format.fasta.otu.EncodedOTU;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alext
 * Date: 7/10/13
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class OTUReducedRedundancyTest {

    @Test
    public void testOTUReducedRedundancyTest() {

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
                        List<EncodedNucleotideFasta> nonredundantSequencesList= AlignableOTU.reduceRedundantSequences(encodedOTU);
                        bufferedWriter = new BufferedWriter(new FileWriter(new File(f.getParent(), fileName + ".nonred")));
                        System.out.println("Processing "+fileName);
                        for(EncodedNucleotideFasta e:nonredundantSequencesList){
                            bufferedWriter.write(e.toString());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }

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
