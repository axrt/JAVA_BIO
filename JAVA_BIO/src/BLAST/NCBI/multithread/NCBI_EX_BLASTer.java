/**
 * 
 */
package BLAST.NCBI.multithread;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import format.fasta.Fasta;

import BLAST.BLAST;
import BLAST.NCBI.local.exec.NCBI_EX_BLAST;
import BLAST.event.BLAST_FinishedEvent;
import BLAST.event.BLAST_TaskFinished_listener;
import BLAST.multithread.BLASTer;

/**
 * An abstract representation of how a {@link BLASTer} should look for the
 * blast+ executables, provided by the <a
 * href="ftp://ftp.ncbi.nih.gov/blast/executables/LATEST">NCBI ftp.</a>
 * 
 * @author axrt
 * 
 */
public abstract class NCBI_EX_BLASTer extends BLASTer {
	/**
	 * A {@link List<NCBI_EX_BLAST>} of blasts that are currently being operated
	 * upon (running) by the {@link NCBI_EX_BLASTer}. *<i><b>Is wrapped to a
	 * synchronized implementation in the constructor</i></b>
	 */
	protected List<NCBI_EX_BLAST> blasts;
	/**
	 * {@link File} - A temporary directory that will be used to dump the input
	 * and output files, that are used by the ncbi+ executable
	 */
	protected File tempDir;
	/**
	 * A full path (or a valid alias) to a blast+ executable
	 */
	protected File executable;
	/**
	 * A list of parameters. Should maintain a certain order. {"<-command>",
	 * "[value]"}, just the way if in the blast+ executable input
	 * 
	 */
	protected String[] parameterList;
	/**
	 * A list of {@link NCBI_EX_BLASTer_TaskFinished_listener}-implementing
	 * modules that get notified when a BLASTer finishes a task
	 */
	private List<NCBI_EX_BLASTer_TaskFinished_listener> listeners;

	/**
	 * @param queryList
	 *            {@link List<Fasta>} - a list of query fasta records
	 * @param numberOfThreads
	 *            {@link int} a number of simultanious blasts running
	 * @param batchSize
	 *            {@link int} a number of fasta {@link Fasta} records in on
	 *            single batch
	 */
	protected NCBI_EX_BLASTer(List<? extends Fasta> queryList,
			List<String> queryListAC, int numberOfThreads, int batchSize,
			File tempDir, File executive, String[] parameterList) {
		super(queryList, queryListAC, numberOfThreads, batchSize);
		// In order to push/pull the blasts from the list, the list should be
		// synchronized
		this.blasts = Collections
				.synchronizedList(new ArrayList<NCBI_EX_BLAST>());
		this.tempDir = tempDir;
		this.executable = executive;
		this.parameterList = parameterList;
		this.listeners = new ArrayList<NCBI_EX_BLASTer_TaskFinished_listener>();
	}

	/**
	 * Allows to react to a {@link BLAST_FinishedEvent}. This should further be
	 * overriden with a call to the method of a superclass. This implementation
	 * deletes the calling "finished" balst instance form the tracking array
	 * 
	 * @param {@link BLAST_FinishedEvent} an event wrapper to a blast that has
	 *        finished the blast task
	 */
	@Override
	public synchronized void handleAFinishedBLAST(BLAST_FinishedEvent event) {
		// Untracks the shot-off blast, leaving it to the GC in case no onther
		// pointers are kept to the instance
		if (event.getSource() instanceof BLAST) {
			BLAST blast = (BLAST) event.getSource();
			blast.removeListener(this);
		}
	}
	/**
	 * Adds another listener to a list of those being notified when the task
	 * finishes
	 * 
	 * @param {@link BLAST_TaskFinished_listener} listener
	 */
	public synchronized void addListener(
			NCBI_EX_BLASTer_TaskFinished_listener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Removes a certain listener from a list of those being notified when the
	 * task finishes
	 * 
	 * @param {@link BLAST_TaskFinished_listener} listener to remove
	 */
	public synchronized void removeListener(
			NCBI_EX_BLASTer_TaskFinished_listener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Notifies all the listening modules form the list of listeners
	 */
	protected void notifyListeners() {
		for (int i = 0; i < this.listeners.size(); i++) {
			this.listeners.get(i).handleAFinishedBLASTer(
					new NCBI_EX_BLASTer_FinishedEvent(this));
		}
	}
}
