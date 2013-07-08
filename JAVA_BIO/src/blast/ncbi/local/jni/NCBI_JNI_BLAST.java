/**
 * 
 */
package blast.ncbi.local.jni;

import java.util.List;
//TODO: document
import format.fasta.Fasta;
import blast.ncbi.NCBI_BLAST;

/**
 * @author axrt A generalized abstractiona of a local ncbi blast process that is
 *         performed via a jni-C++-method call on an ncbi blast precompiled src.
 */
public abstract class NCBI_JNI_BLAST extends NCBI_BLAST {

	/**
	 * @param query
	 *            {@link List<? extends Fasta>} a list of query fasta-formatted
	 *            records
	 * @param query_IDs
	 *            {@link List<String>} a list of AC numbers of sequences in a
	 *            database
	 */
	protected NCBI_JNI_BLAST(List<? extends Fasta> query, List<String> query_IDs) {
		super(query, query_IDs);
	}

}
