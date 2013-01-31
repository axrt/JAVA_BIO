/**
 * 
 */
package BLAST.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import format.fasta.Fasta;

import BLAST.BLAST;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_FinishedEvent;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_TaskFinished_listener;
import BLAST.event.BLAST_TaskFinished_listener;

/**
 * As we had to learn with empirical tests, the "-num_threads" option within the
 * blast executives from NCBI may not perform as efficiently as just running
 * several blast processes in parallel. Thereby this call is supposed to come in
 * handy and automate the process of scattering batches of sequences in a
 * query-list among several processes.
 * 
 * @author axrt
 * 
 */
public abstract class BLASTer implements BLAST_TaskFinished_listener {

	/**
	 * Private executor service, only allows {@link BLAST} tasks via
	 * submitBLAST(BLAST blast);
	 */
	private ExecutorService executorService;
	/**
	 * number of parallel runs, ideally is the same as the number of cores of
	 * the processor
	 */
	protected int numberOfThereds;
	/**
	 * A number of sequence-queries in a batch being delegated to a blast run
	 */
	protected int batchSize;
	/**
	 * A list of queries in fasta format (represented by {@link Fasta} or
	 * extending) that will be taken to perform the blast search
	 */
	private Queue<Fasta> queryList;
	/**
	 * A list of database ACs of sequences that will be taken to perform the
	 * blast search
	 */
	private Queue<String> queryListACs;
	/**
	 * A flag, that indicates that the task is being performed (in order to
	 * prevent illegal state calls)
	 */
	protected boolean working;
	/**
	 * A list of {@link NCBI_EX_BLASTer_TaskFinished_listener}-implementing
	 * modules that get notified when a BLASTer finishes a task
	 */
	private List<NCBI_EX_BLASTer_TaskFinished_listener> listeners;

	/**
	 * Constructor
	 */
	protected BLASTer(List<? extends Fasta> queryList,
			List<String> queryListACs, int numberOfThreads, int batchSize) {
		// TODO: think of smth less dumb, make a single batch, that allows
		// mixing ACs with fasta records
		// Initialize a list if null is passed in order to prevent falures
		// (should be corrected)
		if (queryList == null) {
			queryList = new ArrayList<Fasta>();
		}
		// Initialize a list if null is passed in order to prevent falures
		// (should be corrected)
		if (queryListACs == null) {
			queryListACs = new ArrayList<String>();
		}
		// A list of query Fasta records must handle operations atomically
		this.queryList = new ConcurrentLinkedQueue<Fasta>(queryList);
		// A list of query AC numbers must handle operations atomically
		this.queryListACs = new ConcurrentLinkedQueue<String>(queryListACs);
		// The rest of constructor
		this.numberOfThereds = numberOfThreads;
		this.batchSize = batchSize;
		// Fixed thread pool
		this.executorService = Executors
				.newFixedThreadPool(this.numberOfThereds);
		// Indicate as not working
		this.working = false;
		this.listeners = new ArrayList<NCBI_EX_BLASTer_TaskFinished_listener>();
	}

	/**
	 * 
	 * @param {@link BLAST} blast that is being submitted for execution
	 * @return {@link Future<?>} that allows to track the state of execution
	 */
	protected synchronized Future<?> submitBLAST(BLAST blast) {
		return this.executorService.submit(blast);
	}

	/**
	 * Should launch the process of multithreaded BLAST. May be reasonable to
	 * check the <b>working</b> field for whether the process is already running
	 * to prevent redundant invocations, as well as may not be senseless to
	 * assemble an initial batch of blasts in an overriding method
	 */
	public abstract void launch();

	/**
	 * Polls next {@link Fasta} query record from the query
	 * 
	 * @return a next {@link Fasta} query record
	 */
	protected synchronized Fasta pollFasta() {
		return this.queryList.poll();
	}

	/**
	 * Polls next {@link String} query AC number from the query
	 * 
	 * @return a next {@link String} query record
	 */
	protected synchronized String pollAC() {
		return this.queryListACs.poll();
	}

	/**
	 * 
	 * @return {@code int} the size of the query list
	 */
	public int getQueryListSize() {
		return this.queryList.size();
	}

	/**
	 * 
	 * @return {@code int} the size of the AC records list
	 */
	public int getQueryACListSize() {
		return this.queryListACs.size();
	}

	/**
	 * Stops the executor service (presumably, when all the blast tasks have
	 * been accomplished)
	 */
	public void stop() {
		this.executorService.shutdown();
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
