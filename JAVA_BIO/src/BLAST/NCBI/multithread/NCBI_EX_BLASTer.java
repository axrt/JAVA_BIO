/**
 * 
 */
package BLAST.NCBI.multithread;

//TODO: document
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import format.fasta.Fasta;

import BLAST.BLAST;
import BLAST.NCBI.local.exec.NCBI_EX_BLAST;
import BLAST.event.BLAST_FinishedEvent;
import BLAST.multithread.BLASTer;

/**
 * @author axrt
 * 
 */
public abstract class NCBI_EX_BLASTer extends BLASTer {

	protected List<NCBI_EX_BLAST> blasts;
	protected File tempDir;
	protected File executive;
	protected String[] parameterList;

	/**
	 * @param queryList
	 * @param numberOfThreads
	 * @param batchSize
	 */
	protected NCBI_EX_BLASTer(List<? extends Fasta> queryList,
			List<String> queryListAC, int numberOfThreads, int batchSize,
			File tempDir, File executive, String[] parameterList) {
		super(queryList, queryListAC, numberOfThreads, batchSize);
		this.blasts = Collections
				.synchronizedList(new ArrayList<NCBI_EX_BLAST>());
        this.tempDir=tempDir;
        this.executive=executive;
        this.parameterList=parameterList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * BLAST.event.BLAST_TaskFinished_listener#handleAFinishedBLAST(BLAST.event
	 * .BLAST_FinishedEvent)
	 */
	@Override
	public synchronized void handleAFinishedBLAST(BLAST_FinishedEvent event) {
		  if(event.getSource() instanceof BLAST){
			  BLAST blast=(BLAST)event.getSource();
			  blast.removeListener(this);
		  }
	}
}
