package blast.ncbi.remote;

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
	/**
	 * @return the {@link String} key.
	 */
	public String getKey() {
		return this.key;
	}
	/**
	 * @return the {@link String} value.
	 */
	public String getValue() {
		return this.value;
	}
}
