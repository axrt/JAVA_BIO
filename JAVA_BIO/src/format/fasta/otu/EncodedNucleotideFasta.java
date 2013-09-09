package format.fasta.otu;

import format.fasta.Fasta;
import format.fasta.nucleotide.NucleotideFasta;
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
 *TODO Document
 */
public class EncodedNucleotideFasta extends NucleotideFasta{

    protected final String barcode;

    protected EncodedNucleotideFasta(String AC,String barcode, String sequence) {
        super(AC, sequence);
        this.barcode=barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Fasta.fastaStart);
        sb.append(this.AC);
        sb.append('\t');
        sb.append(this.getBarcode());
        sb.append('\n');
        int line = 0;
        for (int i = 0; i < this.sequence.length(); i++) {
            sb.append(this.sequence.charAt(i));
            line++;
            if (line % Fasta.fastaLineLenght == 0) {
                sb.append('\n');
            }
        }
        return new String(sb);
    }
    public static EncodedNucleotideFasta convertFromNucleotideFasta (NucleotideFasta nucleotideFasta) throws EncodedNucleotideFastaFormatException {

        String [] split = nucleotideFasta.getAC().split("\t");
        if(split.length<2){
            throw new EncodedNucleotideFastaFormatException("AC does not seem to contain a barcode, please check formatting.");
        }

        return new EncodedNucleotideFasta(split[0].trim(),split[1].trim(),nucleotideFasta.getSequence());
    }
}
