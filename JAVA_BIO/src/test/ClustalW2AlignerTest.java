package test;

import align.local.clustal.ClustalW2Aligner;
import format.clustal.AlignmentException;
import format.fasta.nucleotide.NucleotideFasta;
import format.fasta.nucleotide.NucleotideFasta_AC_BadFormatException;
import format.fasta.nucleotide.NucleotideFasta_BadFromat_Exception;
import format.fasta.nucleotide.NucleotideFasta_Sequence_BadFromatException;
import org.junit.Test;
import util.ExecutableUtilFileOperator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alext
 * Date: 7/8/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClustalW2AlignerTest {

    @Test
    public void testClustalW2Aligner(){

        try {
            NucleotideFasta nucleotideFasta1=NucleotideFasta.newInstanceFromFromattedText(
                    ">1\n"
                            + "CGTGGGAATCTACCCAACTCTACGGAATAACTCAGGGAAACTTGTGCTAATACCGTATACGCCCTTCGGGGGAAAGATTTATCGGAGTT"
                            + "GGATGAGCTCGCGTCTGATTAGCTAGTTGGTGGGGTAAAGGCCTACCAAGGCGACGATCAGTAGCCGGTCTGAGAGGATGAACGGCCA" +
                            "CATTGTAACTGAGACACGGTCCAAACTCCTACGGGAGGCAGCAGTGGGGAATATTGCACAATGGGGGAAACCCTGATGCAGCG" +
                            "ACGCCGCGTGAGCGAAGAAGGCTTTCGAGTCGTAAAGCTCTGTCCTATGAGAAGATAATGACGGTATCATAGGAGGAAGCCCTGGCTAAATACGTG"

            );
            NucleotideFasta nucleotideFasta2=NucleotideFasta.newInstanceFromFromattedText(
                    ">2\n"
                            + "CGTGGGATTCTAGCCAACTCTACGGAATAACTCAGGGAAACTTGTGCTAATACCGTATACGCCCTTCGGGGGAAAGATTTATCGGAGTT"
                            + "GGATGAGCTCGCGTCTGATTAGCTAGTTGGTGGGGTAAAGGCCTACCAAGGCGACGATCAGTAGCCGGTCTGAGAGGATGAACGGCCA" +
                            "CATTGTAACTGAGACACGGTCCAAACTCCTACGGGAGGCAGCAGTGGGGAATATTGCACAATGGGGGAAACCCTGATGCAGCG" +
                            "ACGCCGCGTGAGCGAAGAAGGCTTTCGAGTCGTAAAGCTCTGTCCTATGAGAAGATAATGACGGTATCATAGGAGGAAGCCCTGGCTAAATACGTG"

            );

            List<NucleotideFasta> nucleotideFastas=new ArrayList<>();
            nucleotideFastas.add(nucleotideFasta1);
            nucleotideFastas.add(nucleotideFasta2);
            File tmpDir=new File("/home/alext/Downloads/tmp");
            File executable=new File("/usr/local/bin/clustalw2");
            String[]parameterList=new String[]{};
            ClustalW2Aligner<NucleotideFasta> nucleotideFastaClustalW2Aligner=
                    ClustalW2Aligner.newInstance(
                            nucleotideFastas, tmpDir,executable,parameterList,new ExecutableUtilFileOperator<NucleotideFasta>() {
                    });
            nucleotideFastaClustalW2Aligner.align();
            System.out.println(nucleotideFastaClustalW2Aligner.getOutput().toString());


        } catch (NucleotideFasta_BadFromat_Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NucleotideFasta_AC_BadFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NucleotideFasta_Sequence_BadFromatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (AlignmentException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
