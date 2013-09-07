/**
 * 
 */
package blast.ncbi.remote;

/**
 * An exception that is being thrown any time some illegal option is attempted
 * to be added to a parameter list.
 * 
 * @author axrt
 * 
 */
public class Bad_Q_BLAST_Parameter_Exception extends Exception {

	/**
	 * Computed
	 */
	private static final long serialVersionUID = -3868446971681761916L;

	/**
	 * Constructor accepts the {@link NCBI_Q_BLAST_Parameter} in order to
	 * extract it and send a message to indicate the wrong parameter and thereby
	 * help debug the code
	 * 
	 * @param parameter
	 *            {@link NCBI_Q_BLAST_Parameter} that caused the
	 *            {@link Bad_Q_BLAST_Parameter_Exception} to be thrown
	 */
	public Bad_Q_BLAST_Parameter_Exception(NCBI_Q_BLAST_Parameter parameter) {
		super("Inacceptable parameter: " + parameter.toString() + ", key \""
				+ parameter.getKey() + " not allowed for this type of QBLAST");
	}

	/**
	 * An overridden superclass constructor, that allows to just pass the
	 * message about the {@link Bad_Q_BLAST_Parameter_Exception}
	 * 
	 * @param message
	 *            {@link String}
	 */
	public Bad_Q_BLAST_Parameter_Exception(String message) {
		super(message);
	}

}
