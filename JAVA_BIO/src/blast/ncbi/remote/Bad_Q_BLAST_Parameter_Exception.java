/**
 * 
 */
package blast.ncbi.remote;
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
