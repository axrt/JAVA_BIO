/**
 * 
 */
package BLAST;

import java.util.ArrayList;
import java.util.List;

import BLAST.event.BLAST_FinishedEvent;
import BLAST.event.BLAST_TaskFinished_listener;

//TODO: re-document
/**
 * @author axrt A general abstraction, represents a
 *         "Basic Local Alignment Tool process" concept.
 */
public abstract class BLAST implements Runnable {
	
	private List<BLAST_TaskFinished_listener> listeners;

	/**
	 * Constructor
	 */
	protected BLAST() {
		this.listeners = new ArrayList<BLAST_TaskFinished_listener>();
	}

	/**
	 * Adds another listener
	 * 
	 * @param {@link BLAST_TaskFinished_listener} listener
	 */
	public synchronized void addListener(BLAST_TaskFinished_listener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Removes a certain listener
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
}
