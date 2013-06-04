/**
 * 
 */
package BLAST;

import java.util.ArrayList;
import java.util.List;

import BLAST.event.BLAST_FinishedEvent;
import BLAST.event.BLAST_TaskFinished_listener;

/**
 * @author axrt A general abstraction, represents a
 *         "Basic Local Alignment Tool process" concept.
 */
public abstract class BLAST implements Runnable {
	/**
	 * A list of {@link BLAST_TaskFinished_listener}-implementing modules that
	 * get notified when a BLAST finishes a task
	 */
	private List<BLAST_TaskFinished_listener> listeners;

	/**
	 * Constructor
	 */
	protected BLAST() {
		// Initialize a list of listeners
		this.listeners = new ArrayList<BLAST_TaskFinished_listener>();
	}

	/**
	 * Adds another listener to a list of those being notified when the task
	 * finishes
	 * 
	 * @param {@link BLAST_TaskFinished_listener} listener
	 */
	public synchronized void addListener(BLAST_TaskFinished_listener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Removes a certain listener from a list of those being notified when the
	 * task finishes
	 * 
	 * @param {@link BLAST_TaskFinished_listener} listener to remove
	 */
	public synchronized void removeListener(BLAST_TaskFinished_listener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Notifies all the listening modules form the list of listeners
	 */
	protected void notifyListeners() {
		for (int i = 0; i < this.listeners.size(); i++) {
			this.listeners.get(i).handleAFinishedBLAST(
					new BLAST_FinishedEvent(this));
		}
	}
    /**
     * Should launch the blast process. In case a separate thread is created
     * alongside the main thread, this should be called within the run()
     *
     * @throws Exception
     */

    protected abstract void BLAST() throws Exception;
}
