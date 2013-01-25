/**
 * 
 */
package BLAST.NCBI.remote;

//TODO: document this
/**
 * @author axrt
 * 
 */
public class Bad_Q_BLAST_Parameter_Exception extends Exception {

	public Bad_Q_BLAST_Parameter_Exception(NCBI_Q_BLAST_Parameter parameter) {
		super("Inacceptable parameter: " + parameter.toString() + ", key \""
				+ parameter.getKey() + " not allowed for this type of QBLAST");
	}

	public Bad_Q_BLAST_Parameter_Exception(String message) {
		super(message);
	}

}
