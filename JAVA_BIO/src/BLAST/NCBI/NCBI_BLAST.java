/**
 * 
 */
package BLAST.NCBI;

import java.util.ArrayList;
import java.util.List;

import format.fasta.Fasta;
import BLAST.BLAST;
import BLAST.NCBI.output.BlastOutput;

//TODO: document
/**
 * @author axrt A general abstraction of a "Basic Local Alignment Tool" service
 *         at http://blast.ncbi.nlm.nih.gov/Blast.cgi provided by NCBI
 */
public abstract class NCBI_BLAST extends BLAST {
	/**
	 * An object representation of the output, returned from the NCBI server
	 */
	protected BlastOutput blastOutput;
	/**
	 * A Fasta record, that contains the query
	 */
	protected final List<? extends Fasta> query;
	/**
	 * A list of genbak/EMBL ids that shall be used for a BLAST search
	 */
	protected final List<String> query_IDs;
	/**
	 * A flag that indicates whether this instance has already been BLASTed or
	 * not
	 */
	protected boolean BLASTed;

	/**
	 * @return the BLASTed
	 */
	protected boolean isBLASTed() {
		return this.BLASTed;
	}

	/**
	 * @return {@link BlastOutput}the blastOutput
	 */
	public BlastOutput getBlastOutput() {
		return this.blastOutput;
	}

	protected NCBI_BLAST(List<? extends Fasta> query, List<String> query_IDs) {
		super();
		this.query = query;
		this.query_IDs = query_IDs;
		this.BLASTed = false;
	}

	protected NCBI_BLAST(List<? extends Fasta> query) {
		super();
		this.query = (List<Fasta>) query;
		this.query_IDs = new ArrayList<String>();
		this.BLASTed = false;
	}

	/**
	 * @return {@link List<Fasta>}the query
	 */
	public List<? extends Fasta> getQuery() {
		return query;
	}

	/**
	 * @return {@link List<String>} the query_IDs
	 */
	public List<String> getQuery_IDs() {
		return query_IDs;
	}
	
	

}
