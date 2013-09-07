package format.fasta.protein;

import blast.ncbi.remote.NCBI_Q_BLAST_Parameter;
import format.BadFormatException;

/**
 * This exception is thrown whenever a forbidden {@link NCBI_Q_BLAST_Parameter}
 * is being attempted to set for a blast
 * 
 * @author axrt
 * 
 */
public class ProteinFasta_AC_BadFormatException extends BadFormatException {
	/**
	 * Counstructor
	 */
	public ProteinFasta_AC_BadFormatException() {

	}

	/**
	 * Constructor from message
	 * 
	 * @param message {@link String} message
	 */
	public ProteinFasta_AC_BadFormatException(String message) {
		super(message);

	}

	/**
	 * Counstructor from a {@link Throwable}
	 * 
	 * @param cause {@link Throwable} cause
	 */
	public ProteinFasta_AC_BadFormatException(Throwable cause) {
		super(cause);

	}

	/**
	 * Contructor from both message and a {@link Throwable}
	 * 
	 * @param message  {@link String} message
	 * @param cause   {@link Throwable} cause
	 */
	public ProteinFasta_AC_BadFormatException(String message, Throwable cause) {
		super(message, cause);

	}

}
