package format.fasta.otu;

import align.local.clustal.ClustalW2Aligner;
import format.fasta.Fasta;
import format.fasta.nucleotide.NucleotideFasta;
import format.fasta.nucleotide.NucleotideFasta_AC_BadFormatException;
import format.fasta.nucleotide.NucleotideFasta_BadFromat_Exception;
import format.fasta.nucleotide.NucleotideFasta_Sequence_BadFromatException;
import util.ExecutableUtilFileOperator;

import java.io.*;

/**
 * TODO Document
 */
public class EncodedOTU extends AlignableOTU<EncodedNucleotideFasta> {

    protected EncodedOTU(String name, ClustalW2Aligner<EncodedNucleotideFasta> aligner) {
        super(name, aligner);
    }

    protected EncodedOTU(int initialCapacity, String name, ClustalW2Aligner<EncodedNucleotideFasta> aligner) {
        super(initialCapacity, name, aligner);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        for (EncodedNucleotideFasta e:this){
            stringBuilder.append(e.toString());
        }
        return stringBuilder.toString();
    }

    public static EncodedOTU newDefaultInstanceFromFile(File otuFile, File tmpDir, File executable) throws IOException, NucleotideFasta_AC_BadFormatException, NucleotideFasta_Sequence_BadFromatException, NucleotideFasta_BadFromat_Exception {

        BufferedReader bufferedReader = null;
        EncodedOTU encodedOTU = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(otuFile));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            String[] split = stringBuilder.toString().split(Fasta.fastaStart);
            encodedOTU = new EncodedOTU(otuFile.getName().split("\\.")[0],
                    ClustalW2Aligner.newDefaultInstance(encodedOTU, tmpDir, executable));
            for (int i=1;i<split.length;i++) {
                encodedOTU.add(EncodedNucleotideFasta.convertFromNucleotideFasta(NucleotideFasta.newInstanceFromFromattedText(Fasta.fastaStart+split[i])));
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return encodedOTU;
    }
}
