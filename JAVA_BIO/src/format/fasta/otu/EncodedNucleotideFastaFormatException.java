package format.fasta.otu;

import format.fasta.nucleotide.NucleotideFasta_AC_BadFormatException;

/**
 *TODO Document
 */
public class EncodedNucleotideFastaFormatException extends NucleotideFasta_AC_BadFormatException {
    public EncodedNucleotideFastaFormatException() {
    }

    public EncodedNucleotideFastaFormatException(String message) {
        super(message);
    }

    public EncodedNucleotideFastaFormatException(Throwable cause) {
        super(cause);
    }

    public EncodedNucleotideFastaFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
