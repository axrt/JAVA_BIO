/**
 * 
 */
package blast.ncbi;

import java.util.List;

import format.fasta.Fasta;
import blast.BLAST;
import blast.ncbi.output.BlastOutput;
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
 * @author axrt A general abstraction of a "Basic Local Alignment Tool" service
 *         at http://blast.ncbi.nlm.nih.gov/Blast.cgi provided by ncbi
 */
public abstract class NCBI_BLAST<T extends Fasta> extends BLAST {
	/**
	 * An object representation of the output, returned from the ncbi server
	 */
	protected BlastOutput blastOutput;
	/**
	 * A Fasta record, that contains the query
	 */
	protected List<T> query;
	/**
	 * A list of genbak/EMBL ids that shall be used for a blast search
	 */
	protected List<String> query_IDs;
	/**
	 * A flag that indicates whether this instance has already been BLASTed or
	 * not
	 */
	protected boolean BLASTed;

	/**
	 * @return {@code boolean} BLASTed, presumably {@code true} if blasted,
	 *         {@code false} elsewise
	 */
	protected boolean isBLASTed() {
		return this.BLASTed;
	}

	/**
	 * @return {@link BlastOutput} blast output from as a current result,
	 *         {@code null} if the blast has not been accomplished yet
	 */
	public synchronized BlastOutput getBlastOutput() {
		return this.blastOutput;
	}

	/**
	 * Constructor
	 * 
	 * @param query
	 *            {@link List} a list of query fasta-formatted
	 *            records
	 * @param query_IDs
	 *            {@link List} a list of AC numbers of sequences in a
	 *            database
	 */
	protected NCBI_BLAST(List<T> query, List<String> query_IDs) {
		super();
		this.query = query;
		this.query_IDs = query_IDs;
		this.BLASTed = false;
	}

	/**
	 * @return {@link List} the query
	 */
	public List<? extends Fasta> getQuery() {
		return query;
	}

	/**
	 * @return {@link List} the query_IDs
	 */
	public List<String> getQuery_IDs() {
		return query_IDs;
	}

}
