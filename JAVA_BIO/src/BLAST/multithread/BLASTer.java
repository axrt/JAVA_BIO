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
import BLAST.event.BLAST_TaskFinished_listener;

//TODO: document
/**
 * @author axrt
 * 
 */
public abstract class BLASTer implements BLAST_TaskFinished_listener {

	private ExecutorService executorService;

	protected int numberOfThereds;

	protected int batchSize;

	private Queue<Fasta> queryList;
	private Queue<String> queryListACs;

	protected boolean working;

	/**
	 * Constructor
	 */
	protected BLASTer(List<? extends Fasta> queryList,
			List<String> queryListACs, int numberOfThreads, int batchSize) {
		// TODO: think of smth less dumb
		if (queryList == null) {
			queryList = new ArrayList<Fasta>();
		}
		if (queryListACs == null) {
			queryListACs = new ArrayList<String>();
		}
		this.queryList = new ConcurrentLinkedQueue<Fasta>(queryList);
		this.queryListACs = new ConcurrentLinkedQueue<String>(queryListACs);
		this.numberOfThereds = numberOfThreads;
		this.batchSize = batchSize;
		this.executorService = Executors
				.newFixedThreadPool(this.numberOfThereds);
		this.working = false;
	}

	protected synchronized Future<?> submitBLAST(BLAST blast) {
		return this.executorService.submit(blast);
	}

	public abstract void launch();

	protected synchronized Fasta pollFasta() {
		return this.queryList.poll();

	}

	protected synchronized String pollAC() {
		return this.queryListACs.poll();
	}

	public int getQueryListSize() {
		return this.queryList.size();
	}
	public void stop(){
		this.executorService.shutdown();
	}
}
