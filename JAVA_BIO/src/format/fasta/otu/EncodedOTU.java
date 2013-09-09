package format.fasta.otu;

import align.local.clustal.ClustalW2Aligner;
import format.fasta.Fasta;
import format.fasta.nucleotide.NucleotideFasta;
import format.fasta.nucleotide.NucleotideFasta_AC_BadFormatException;
import format.fasta.nucleotide.NucleotideFasta_BadFormat_Exception;
import format.fasta.nucleotide.NucleotideFasta_Sequence_BadFormatException;

import java.io.*;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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

    public static EncodedOTU newDefaultInstanceFromFile(File otuFile, File tmpDir, File executable) throws IOException, NucleotideFasta_AC_BadFormatException, NucleotideFasta_Sequence_BadFormatException, NucleotideFasta_BadFormat_Exception {

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
                    ClustalW2Aligner.<EncodedNucleotideFasta>newDefaultInstance(encodedOTU, tmpDir, executable));
            for (int i=1;i<split.length;i++) {
                encodedOTU.add(EncodedNucleotideFasta.convertFromNucleotideFasta(NucleotideFasta.newInstanceFromFormattedText(Fasta.fastaStart + split[i])));
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return encodedOTU;
    }
}
