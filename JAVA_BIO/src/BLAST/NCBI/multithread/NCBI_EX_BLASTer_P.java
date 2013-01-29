/**
 * 
 */
package BLAST.NCBI.multithread;
//TODO: document
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import format.fasta.Fasta;
import format.fasta.ProteinFasta;
import BLAST.event.BLAST_FinishedEvent;
import BLAST.multithread.BLASTer;

/**
 * @author axrt
 *
 */
public class NCBI_EX_BLASTer_P extends BLASTer {
    
	protected List<NCBI_EX_BLASTer_P> blasts;
	
	/**
	 * @param queryList
	 * @param numberOfThreads
	 * @param batchSize
	 */
	protected NCBI_EX_BLASTer_P(List<ProteinFasta> queryList,List<String> queryListAC,
			int numberOfThreads, int batchSize) {
		super(queryList,queryListAC, numberOfThreads, batchSize);
        this.blasts=Collections.synchronizedList(new ArrayList<NCBI_EX_BLASTer_P>());
	}

	/* (non-Javadoc)
	 * @see BLAST.event.BLAST_TaskFinished_listener#handleAFinishedBLAST(BLAST.event.BLAST_FinishedEvent)
	 */
	@Override
	public void handleAFinishedBLAST(BLAST_FinishedEvent event)
			throws Exception {

	}

}
