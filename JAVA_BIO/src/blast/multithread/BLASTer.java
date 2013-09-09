/**
 * 
 */
package blast.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import format.fasta.Fasta;

import blast.BLAST;
import blast.event.BLAST_TaskFinished_listener;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * As we had to learn with empirical tests, the "-num_threads" option within the
 * blast executives from ncbi may not perform as efficiently as just running
 * several blast processes in parallel. Thereby this call is supposed to come in
 * handy and automate the process of scattering batches of sequences in a
 * query-list among several processes.
 * 
 * @author axrt
 * 
 */
public abstract class BLASTer<B extends BLAST,T extends Fasta> implements Runnable, BLAST_TaskFinished_listener {

	/**
	 * Private executor service, only allows {@link BLAST} tasks via
	 * submitBLAST(blast blast);
	 */
	protected final ExecutorService executorService;
	/**
	 * number of parallel runs, ideally is the same as the number of cores of
	 * the processor
	 */
	protected final int numberOfThereds;
	/**
	 * A number of sequence-queries in a batch being delegated to a blast run
	 */
	protected final int batchSize;
	/**
	 * A list of queries in fasta format (represented by {@link Fasta} or
	 * extending) that will be taken to perform the blast search
	 */
    protected Queue<T> queryList;
	/**
	 * A list of database ACs of sequences that will be taken to perform the
	 * blast search
	 */
    protected Queue<String> queryListACs;
	/**
	 * A que of finished blasts, ready to return their results
	 */
    protected Queue<B> finishedBLASTs;
	/**
	 * A flag, that indicates that the task is being performed (in order to
	 * prevent illegal state calls)
	 */
	protected boolean working;

	/**
	 * Constructor
	 */
	protected BLASTer(List<T> queryList,
			List<String> queryListACs, int numberOfThreads, int batchSize) {
		// TODO: think of smth less dumb, make a single batch, that allows
		// mixing ACs with fasta records
		// Initialize a list if null is passed in order to prevent falures
		// (should be corrected)
		if (queryList == null) {
			queryList = new ArrayList<>();
		}
		// Initialize a list if null is passed in order to prevent falures
		// (should be corrected)
		if (queryListACs == null) {
			queryListACs = new ArrayList<>();
		}
		// A list of query Fasta records must handle operations atomically
		this.queryList = new ConcurrentLinkedQueue<>(queryList);
		// A list of query AC numbers must handle operations atomically
		this.queryListACs = new ConcurrentLinkedQueue<>(queryListACs);
		this.finishedBLASTs = new ConcurrentLinkedQueue<>();
		// The rest of constructor
		this.numberOfThereds = numberOfThreads;
		this.batchSize = batchSize;
		// Fixed thread pool
		this.executorService = Executors
				.newFixedThreadPool(this.numberOfThereds);
		// Indicate as not working
		this.working = false;
	}

	/**
	 * Returns a list of finished BLASTs that are awaiting processing of their
	 * results.
	 * 
	 * @return {@link List} {@link BLAST}s
	 */
	public List<B> getFinishedBLASTs() {
		List<B> fBLASTs = new ArrayList<>();
		while (!this.finishedBLASTs.isEmpty()) {
			fBLASTs.add(this.finishedBLASTs.poll());
		}
		return fBLASTs;
	}

	/**
	 * Returs working. Allows to chech whether the BLASTer is working or not.
	 * 
	 * @return {@code true} if so, else - {@code false}
	 */
	public synchronized boolean isWorking() {
		return working;
	}

	/**
	 * Submits a given BLAST for execution
	 * @param blast {@link blast} blast that is being submitted for execution
	 * @return  {@link Future} that allows to track the state of execution
	 */
	protected synchronized Future<?> submitBLAST(BLAST blast) {
		return this.executorService.submit(blast);
	}

	/**
	 * Should launch the process of multithreaded blast. May be reasonable to
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
	public T pollFasta() {
		return this.queryList.poll();
	}

	/**
	 * Allows to add additional fasta-formatted records on the fly.
	 * 
	 * @param fastas {@link List} of fasta records
	 */
	public void appendFasta(List<T> fastas) {
		this.queryList.addAll(fastas);
	}

	/**
	 * Polls next {@link String} query AC number from the query
	 * 
	 * @return a next {@link String} query record
	 */
	protected String pollAC() {
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
	 * Allows to store finished {@link BLAST}s in a queue.
	 * 
	 * @param blast {@link blast} that has finished and needs to be stored
	 */
	protected void storeAFinishedBLAST(B blast) {
		this.finishedBLASTs.add(blast);
	}

	/**
	 * Allows to ask whether the queue for fasta-formatted records has more
	 * 
	 * @return {@code true} if is, {@code false}
	 */
	public boolean hasMoreTasksForQuery() {
		return !this.queryList.isEmpty();
	}

}
