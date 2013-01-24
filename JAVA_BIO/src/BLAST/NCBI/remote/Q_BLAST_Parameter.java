package BLAST.NCBI.remote;

/**
 * An abstract parameter key-value pare for a QBLAST request
 * @author axrt
 *
 */
public abstract class Q_BLAST_Parameter {
	/**
	 * Key name
	 */
	protected final String key;
	/**
	 * Value
	 */
	protected final String value;

	/**
	 * Constructor 
	 * @param {@link String} key
	 * @param {@link String} value
	 */
	protected Q_BLAST_Parameter(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

}
