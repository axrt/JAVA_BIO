package format.fasta.nucleotide;

import format.fasta.Fasta;

import java.util.HashSet;

/**
 * A class that represents a nucleotide fasta record
 */
public class NucleotideFasta extends Fasta {

    static {
        allowedChars = new HashSet<>(java.util.Arrays.asList(new Character[]{'A', 'T', 'G', 'C', 'N', '-','\n', '/'}));
    }

    protected NucleotideFasta(String AC, String sequence){
        super(AC, sequence);
    }
    /**
     * A static factory to assemble a {@link NucleotideFasta} from AC and a
     * sequence.
     *
     * @param AC {@link String} AC
     * @param sequence {@link String} sequence
     * @return a new instance of a {@link NucleotideFasta}
     */
    public static NucleotideFasta newInstanceFromParts(String AC, String sequence) {

        // If all goes well, return a new NucleotideFasta record object.
        return new NucleotideFasta(AC, sequence);
    }
    /**
     * A static factory to assemble a {@link NucleotideFasta} from a {@link String}
     * , that represents a fasta-formatted record
     *
     * @param fastaRecord {@link String} fastaRecord
     * @return a new instance of a {@link NucleotideFasta}
     * @throws NucleotideFasta_BadFormat_Exception
     *
     * @throws NucleotideFasta_AC_BadFormatException
     *
     * @throws NucleotideFasta_Sequence_BadFormatException
     *
     */
    public static NucleotideFasta newInstanceFromFormattedText(String fastaRecord)
            throws NucleotideFasta_BadFormat_Exception,
            NucleotideFasta_AC_BadFormatException,
            NucleotideFasta_Sequence_BadFormatException {
        // Get the first row and check whether it is good for an AC
        String[] splitter = fastaRecord.split("\n");
        if (splitter.length < 2) {
            throw new NucleotideFasta_BadFormat_Exception(
                    "Nucleotide Fasta record: bad format; represented by a single line.");
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
            String AC=splitter[0];
            // Enforce uppercase
            AC = AC.toUpperCase();
            String sequence = sb.toString().toUpperCase();
            // First check for whether the AC does not contain an illegal '>'
            // diamond character anywhere, except for the very beginning
            if (AC.contains(Fasta.fastaStart)) {
                // If so, check whether if is in the beginning
                if (!AC.startsWith(Fasta.fastaStart)) {
                    throw new NucleotideFasta_AC_BadFormatException(
                            "Error within the AC: \'>\' at position "
                                    + String.valueOf(AC.indexOf(Fasta.fastaStart))
                                    + '!');
                } else {
                    AC = AC.trim().substring(1);
                }
            } else{
                throw new NucleotideFasta_AC_BadFormatException(
                        "Error within the AC: wrong record start");
            }
            // Ac should also contain no new line symbols
            if (AC.contains("\n")) {
                throw new NucleotideFasta_AC_BadFormatException(
                        "Error within the AC: \'\n\' at position "
                                + AC.indexOf("\n") + '!');
            }
            // If the sequence provided contains any illegal characters - complain
            // and point to the position of an illegal character
            int check = Fasta.checksOutForIllegalCharacters(sequence);
            if (check != 0) {
                throw new NucleotideFasta_Sequence_BadFormatException(
                        "Error within the Sequence: illegal character at positon: "
                                + String.valueOf(check));
            }

            return NucleotideFasta.newInstanceFromParts(AC,
                    sequence);
            // TODO: this implementation may not be the fastest, see if smth
            // could be done
        }
    }
}
