/**
 * 
 */
package blast.ncbi.remote;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
		this.allowedParameters=new HashSet<>();
		this.allowedParameters.addAll(Arrays
				.asList(allowedParameters));
	}

	/**
     * Constructor from parameters
	 * @param parameters {@code NCBI_Q_BLAST_Parameter} set
	 */
	public NCBI_Q_BLAST_ParameterSet(String[] allowedParameters,Collection<NCBI_Q_BLAST_Parameter> parameters) {
		super(parameters);
		this.allowedParameters=new HashSet<>();
		this.allowedParameters.addAll(Arrays
				.asList(allowedParameters));
	}

	/**
     * Constructor from parameters
	 * @param param {@code int} capacity
	 */
	public NCBI_Q_BLAST_ParameterSet(String[] allowedParameters,int param) {
		super(param);
		this.allowedParameters=new HashSet<>();
		this.allowedParameters.addAll(Arrays
				.asList(allowedParameters));
	}

	/**
     * Constructor from parameters
     * @param      arg0   the initial capacity of the hash map
     * @param      arg1        the load factor of the hash map
	 */
	public NCBI_Q_BLAST_ParameterSet(String[] allowedParameters,int arg0, float arg1) {
		super(arg0, arg1);
		this.allowedParameters=new HashSet<>();
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
        return this.allowedParameters.contains(e.getKey()) && super.add(e);
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
