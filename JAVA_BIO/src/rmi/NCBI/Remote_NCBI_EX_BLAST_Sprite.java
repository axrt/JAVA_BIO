/**
 * 
 */
package rmi.NCBI;

import java.io.File;
import java.util.List;

import format.fasta.Fasta;

import BLAST.BLAST;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_FinishedEvent;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_TaskFinished_listener;
import rmi.RemoteBLASTSprite;

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
	public Remote_NCBI_EX_BLAST_Sprite(String name, int port, String uri,
			NCBI_EX_BLASTer blaster, int blastBatchSize, File executable,
			File tmpDir, File databaseDir) {
		super(name, port, uri, blastBatchSize, executable, tmpDir, databaseDir);
		this.blaster = blaster;
	}

	@Override
	public void selfDeploy() throws Exception {
		super.selfDeploy();
		this.blaster.addListener(this);
	}

	@Override
	public List<? extends BLAST> processDelegatedBLASTBatch(
			List<? extends Fasta> queryList) throws Exception {
		{
			this.blaster.appendFasta(queryList);
			if (!this.blaster.isWorking()) {
				this.blaster.launch();
			}
			//
			wait();
			return this.blaster.getFinishedBLASTs();
		}
	}

	@Override
	public synchronized void handleAFinishedBLASTer(
			NCBI_EX_BLASTer_FinishedEvent event) {
		notify();
	}
}
