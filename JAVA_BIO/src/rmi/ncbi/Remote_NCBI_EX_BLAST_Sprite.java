/**
 * 
 */
package rmi.ncbi;

import java.rmi.RemoteException;
import java.util.List;

import rmi.RemoteBLASTSprite;
import blast.BLAST;
import blast.ncbi.multithread.NCBI_EX_BLASTer;
import blast.ncbi.multithread.NCBI_EX_BLASTer_FinishedEvent;
import blast.ncbi.multithread.NCBI_EX_BLASTer_TaskFinished_listener;
import format.fasta.Fasta;

/**
 * @author axrt
 * 
 */
public abstract class Remote_NCBI_EX_BLAST_Sprite extends RemoteBLASTSprite
		implements NCBI_EX_BLASTer_TaskFinished_listener {
	private NCBI_EX_BLASTer blaster;

	/**
	 * @param name
	 * @param port
	 * @param uri
	 * @param blaster
	 * @param blastBatchSize
	 * @param executable
	 * @param tmpDir
	 * @param databaseDir
	 */
	protected Remote_NCBI_EX_BLAST_Sprite(String name, int port, String uri,
			NCBI_EX_BLASTer blaster,int blastBatchSize) {
		super(name, port, uri,blastBatchSize);
		this.blaster = blaster;
	}

	@Override
	public void selfDeploy() throws RemoteException {
		super.selfDeploy();
		this.blaster.addListener(this);
	}

	@Override
	public synchronized List<? extends BLAST> processDelegatedBLASTBatch(
			List<? extends Fasta> queryList) throws InterruptedException {	
			
		    this.blaster.appendFasta(queryList);

			this.blaster.launch();

			//
			wait();
			return this.blaster.getFinishedBLASTs();
	}

	@Override
	public synchronized void handleAFinishedBLASTer(
			NCBI_EX_BLASTer_FinishedEvent event) {
		notify();
	}
}
