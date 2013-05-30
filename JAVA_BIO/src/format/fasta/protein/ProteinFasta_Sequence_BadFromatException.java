package format.fasta.protein;

import format.BadFromatException;

public class ProteinFasta_Sequence_BadFromatException extends BadFromatException {

	public ProteinFasta_Sequence_BadFromatException() {
		
	}

	public ProteinFasta_Sequence_BadFromatException(String message) {
		super(message);
		
	}

	public ProteinFasta_Sequence_BadFromatException(Throwable cause) {
		super(cause);
		
	}

	public ProteinFasta_Sequence_BadFromatException(String message,
			Throwable cause) {
		super(message, cause);
		
	}

}
