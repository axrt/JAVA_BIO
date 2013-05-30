package format.fasta.protein;

import BLAST.NCBI.remote.NCBI_Q_BLAST_Parameter;
import format.BadFromatException;

/**
 * This exception is thrown whenever a forbidden {@link NCBI_Q_BLAST_Parameter}
 * is being attempted to set for a BLAST
 * 
 * @author axrt
 * 
 */
public class ProteinFasta_AC_BadFormatException extends BadFromatException {
	/**
	 * Counstructor
	 */
	public ProteinFasta_AC_BadFormatException() {

	}

	/**
	 * Constructor from message
	 * 
	 * @param message
	 */
	public ProteinFasta_AC_BadFormatException(String message) {
		super(message);

	}

	/**
	 * Counstructor from a {@link Throwable}
	 * 
	 * @param cause
	 */
	public ProteinFasta_AC_BadFormatException(Throwable cause) {
		super(cause);

	}

	/**
	 * Contructor from both message and a {@link Throwable}
	 * 
	 * @param message
	 * @param cause
	 */
	public ProteinFasta_AC_BadFormatException(String message, Throwable cause) {
		super(message, cause);

	}

}
