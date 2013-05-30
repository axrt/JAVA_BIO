package format.fasta.nucleotide;

import format.BadFromatException;

public class NucleotideFasta_Sequence_BadFromatException extends BadFromatException {

	public NucleotideFasta_Sequence_BadFromatException() {
		
	}

	public NucleotideFasta_Sequence_BadFromatException(String message) {
		super(message);
		
	}

	public NucleotideFasta_Sequence_BadFromatException(Throwable cause) {
		super(cause);
		
	}

	public NucleotideFasta_Sequence_BadFromatException(String message,
                                                       Throwable cause) {
		super(message, cause);
		
	}

}
