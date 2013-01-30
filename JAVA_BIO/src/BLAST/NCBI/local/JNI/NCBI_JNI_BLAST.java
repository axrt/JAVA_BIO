/**
 * 
 */
package BLAST.NCBI.local.JNI;

import java.util.List;
//TODO: document
import format.fasta.Fasta;
import BLAST.NCBI.NCBI_BLAST;

/**
 * @author axrt
 * A generalized abstractiona of a local NCBI BLAST process that is
 * performed via a JNI-C++-method call on an NCBI BLAST precompiled src.
 */
public abstract class NCBI_JNI_BLAST extends NCBI_BLAST {

	/**
	 * @param query
	 * @param query_IDs
	 */
	protected NCBI_JNI_BLAST(List<? extends Fasta> query, List<String> query_IDs) {
		super(query, query_IDs);
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param query
//	 */
//	protected NCBI_JNI_BLAST(List<? extends Fasta> query) {
//		super(query);
//		// TODO Auto-generated constructor stub
//	}

	
	
}
