/**
 * 
 */
package BLAST.NCBI.local.exec;

import java.io.File;
import java.util.List;

import format.fasta.Fasta;

import BLAST.NCBI.NCBI_BLAST;
//TODO: document
/**
 * @author axrt
 * A generalized abstraction of an NCBI BLAST process that is performed locally
 * through an NCBI executable from 
 * ftp://ftp.ncbi.nlm.nih.gov/blast/executables/blast+/LATEST/
 */
public abstract class NCBI_EX_BLAST extends NCBI_BLAST {
	
    protected File inputFile;
    protected File tempDir;
    protected File outputFile;
    protected File executive;
	
    
    
    /**
	 * @param query
	 * @param query_IDs
	 */
	private NCBI_EX_BLAST(List<Fasta> query, List<String> query_IDs) {
		super(query, query_IDs);
	}

	public abstract void BLAST() throws Exception;

}
