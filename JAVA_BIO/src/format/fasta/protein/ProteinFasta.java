package format.fasta.protein;

import format.fasta.Fasta;

import java.util.HashSet;
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
 * A class that represents a protein fasta record
 */

public class ProteinFasta extends Fasta {

    static {
        // Initializing the legal character set for a protein sequence
        allowedChars = new HashSet<>(
                java.util.Arrays.asList(new Character[]{'A', 'R', 'N', 'D',
                        'C', 'E', 'Q', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P',
                        'S', 'T', 'W', 'Y', 'V', 'U', 'O', '\n', '/'}));
    }

    /**
     * Constructor
     *
     * @param AC {@link String} AC
     * @param sequence {@link String} sequence
     */
    protected ProteinFasta(String AC, String sequence) {
        super(AC, sequence);
    }

    /**
     * A static factory to assemble a {@link ProteinFasta} from AC and a
     * sequence.
     *
     * @param AC {@link String} AC
     * @param sequence {@link String} sequence
     * @return a new instance of a {@link ProteinFasta}
     * @throws ProteinFasta_Sequence_BadFormatException
     *          in case smth is not all right with the sequence
     */
    public static ProteinFasta newInstanceFromParts(String AC, String sequence)
            throws
            ProteinFasta_Sequence_BadFormatException {

        // If all goes well, return a new ProteinFasta record object.
        return new ProteinFasta(AC, sequence);
    }

    /**
     * A static factory to assemble a {@link ProteinFasta} from a {@link String}
     * , that represents a fasta-formatted record
     *
     * @param fastaRecord {@link String} fastaRecord
     * @return a new instance of a {@link ProteinFasta}
     * @throws ProteinFasta_BadFormat_Exception
     *
     * @throws ProteinFasta_AC_BadFormatException
     *
     * @throws ProteinFasta_Sequence_BadFormatException
     *
     */
    public static ProteinFasta newInstanceFromFormattedText(String fastaRecord)
            throws ProteinFasta_BadFormat_Exception,
            ProteinFasta_AC_BadFormatException,
            ProteinFasta_Sequence_BadFormatException {
        // Get the first row and check whether it is good for an AC
        String[] splitter = fastaRecord.split("\n");
        if (splitter.length < 2) {
            throw new ProteinFasta_BadFormat_Exception(
                    "Protein Fasta record: bad format; represented by a single line.");
        } else {
            // Prepare a StringBuilder of a proper (at least close to proper)
            // size
            StringBuilder sb = new StringBuilder(splitter.length
                    * splitter[1].length());
            for (int i = 1; i < splitter.length; i++) {
                // Get rid of all the spaces on the fly
                // Concatenate the sequence
                sb.append(splitter[i].replaceAll(" ", ""));
            }
            // Enforce uppercase
            String AC = splitter[0].toUpperCase();
            String sequence = sb.toString().toUpperCase();
            // First check for whether the AC does not contain an illegal '>'
            // diamond character anywhere, except for the very beginning
            if (AC.contains(Fasta.fastaStart)) {
                // If so, check whether if is in the beginning
                if (!AC.startsWith(Fasta.fastaStart)) {
                    throw new ProteinFasta_AC_BadFormatException(
                            "Error within the AC: \'>\' at position "
                                    + String.valueOf(AC.indexOf(Fasta.fastaStart))
                                    + '!');
                } else {
                    AC = AC.trim().substring(1);
                }
            }else{
                throw new ProteinFasta_AC_BadFormatException(
                        "Error within the AC: wrong record start");
            }
            // Ac should also contain no new line symbols
            if (AC.contains("\n")) {
                throw new ProteinFasta_AC_BadFormatException(
                        "Error within the AC: \'\n\' at position "
                                + AC.indexOf("\n") + '!');
            }
            // If the sequence provided contains any illegal characters - complain
            // and point to the position of an illegal character
            int check = Fasta.checksOutForIllegalCharacters(sequence);
            if (check != 0) {
                throw new ProteinFasta_Sequence_BadFormatException(
                        "Error within the Sequence: illegal character at positon: "
                                + String.valueOf(check));
            }
            return ProteinFasta.newInstanceFromParts(AC,
                   sequence);
            // TODO: this implementation may not be the fastest, see if smth
            // could be done
        }
    }
}
