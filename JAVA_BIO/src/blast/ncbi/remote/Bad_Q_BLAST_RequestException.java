package blast.ncbi.remote;
/**
 * Thrown in case something went wrong with the blast results Put/Get
 * @author axrt
 *
 */
public class Bad_Q_BLAST_RequestException extends Exception {

	/**
	 * 
	 */
	protected Bad_Q_BLAST_RequestException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	protected Bad_Q_BLAST_RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	protected Bad_Q_BLAST_RequestException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	protected Bad_Q_BLAST_RequestException(Throwable cause) {
		super(cause);
	}

}
