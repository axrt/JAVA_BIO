/**
 * 
 */
package blast.ncbi.local.jni;

import java.util.List;
//TODO: document
import format.fasta.Fasta;
import blast.ncbi.NCBI_BLAST;
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
 * @author axrt A generalized abstractiona of a local ncbi blast process that is
 *         performed via a jni-C++-method call on an ncbi blast precompiled src.
 */
public abstract class NCBI_JNI_BLAST<T extends Fasta> extends NCBI_BLAST<T> {

	/**
	 * @param query
	 *            {@link List} a list of query fasta-formatted
	 *            records
	 * @param query_IDs
	 *            {@link List} a list of AC numbers of sequences in a
	 *            database
	 */
	protected NCBI_JNI_BLAST(List<T> query, List<String> query_IDs) {
		super(query, query_IDs);
	}

}
