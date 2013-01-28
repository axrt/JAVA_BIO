/**
 * 
 */
package BLAST.NCBI;

import java.util.ArrayList;
import java.util.List;

import format.fasta.Fasta;
import BLAST.BLAST;
//TODO: document
/**
 * @author axrt A general abstraction of a "Basic Local Alignment Tool" service
 *         at http://blast.ncbi.nlm.nih.gov/Blast.cgi provided by NCBI
 */
public abstract class NCBI_BLAST extends BLAST {
	/**
	 * A Fasta record, that contains the query
	 */
	protected final List<Fasta> query;
	/**
	 * A list of genbak/EMBL ids that shall be used for a BLAST search
	 */
	protected final List<String> query_IDs;
	/**
	 * A flag that indicates whether this instance has already been BLASTed or
	 * not
	 */
	protected boolean BLASTed;
    
	protected NCBI_BLAST(List<Fasta> query, List<String> query_IDs) {
		this.query = query;
		this.query_IDs = query_IDs;
		this.BLASTed = false;
	}

	protected NCBI_BLAST(List<Fasta> query) {
		this.query = query;
		this.query_IDs = new ArrayList<String>();
		this.BLASTed = false;
	}
}
