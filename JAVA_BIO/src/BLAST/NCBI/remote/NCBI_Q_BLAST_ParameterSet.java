/**
 * 
 */
package BLAST.NCBI.remote;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A customized version of a {@link HashSet} that allows to store
 * {@link NCBI_Q_BLAST_Parameter} values and maintain which parameters may or
 * not be applied in a particular case
 * 
 * @author axrt
 * 
 */
public class NCBI_Q_BLAST_ParameterSet extends
		HashSet<NCBI_Q_BLAST_Parameter> {

	/**
	 * A {@link HashSet} that will allow to track whether a given is applicable
	 * to a certain {@link NCBI_Q_BLAST} implementation
	 */
	protected HashSet<String> allowedParameters;
	/**
	 * An ampersand character, used to concatenate parameters upon a QBLAST
	 * request formation
	 */
	protected static final String ampersand = "&";

	/**
	 * 
	 */
	public NCBI_Q_BLAST_ParameterSet(String[] allowedParameters) {
		this.allowedParameters=new HashSet<String>();
		this.allowedParameters.addAll(Arrays
				.asList(allowedParameters));
	}

	/**
	 * @param arg0
	 */
	public NCBI_Q_BLAST_ParameterSet(String[] allowedParameters,Collection<NCBI_Q_BLAST_Parameter> arg0) {
		super(arg0);
		this.allowedParameters=new HashSet<String>();
		this.allowedParameters.addAll(Arrays
				.asList(allowedParameters));
	}

	/**
	 * @param arg0
	 */
	public NCBI_Q_BLAST_ParameterSet(String[] allowedParameters,int arg0) {
		super(arg0);
		this.allowedParameters=new HashSet<String>();
		this.allowedParameters.addAll(Arrays
				.asList(allowedParameters));
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NCBI_Q_BLAST_ParameterSet(String[] allowedParameters,int arg0, float arg1) {
		super(arg0, arg1);
		this.allowedParameters=new HashSet<String>();
		this.allowedParameters.addAll(Arrays
				.asList(allowedParameters));
	}

	/**
	 * Checks whether a given {@link NCBI_Q_BLAST_Parameter} is allowed in the
	 * extending type of QBLAST.
	 * 
	 * @return {@code true} in case added, {@code false} in case not added or
	 *         rejected due to "allowed parameters restrictions".
	 */
	// TODO: think of a way how to restrict any attempts to add unique
	// parameters multiple times
	@Override
	public boolean add(NCBI_Q_BLAST_Parameter e) {
		if (this.allowedParameters.contains(e.getKey())) {
			return super.add(e);
		} else {
			return false;
		}
	}

	/**
	 * @return {@link String} ready to form a QBLAST request
	 */
	@Override
	public String toString() {
		// Prepare a StringBuilder
		StringBuilder sb = new StringBuilder();
		// Now iterate over the set of parameters in order to form the request
		// string
		for(NCBI_Q_BLAST_Parameter parameter:this){
			sb.append(parameter.toString());
			sb.append(NCBI_Q_BLAST_ParameterSet.ampersand);
		}
		return new String(sb);
	}
}
