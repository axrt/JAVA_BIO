/**
 * 
 */
package BLAST;

import java.util.EventObject;
import java.util.List;

import BLAST.event.BLAST_TaskFinished_listener;

//TODO: re-document
/**
 * @author axrt A general abstraction, represents a
 *         "Basic Local Alignment Tool process" concept.
 */
public abstract class BLAST implements Runnable {
	/**
	 * Any BLAST takes a query sequence as an input
	 */
	protected String query;
	/**
	 * A BLAST process results in an output with a list of hits
	 */
	protected String result;
	/**
	 * A list of modules that get notified when the BLAST finishes
	 */
	private List<BLAST_TaskFinished_listener> listeners;

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Constructor
	 */
	protected BLAST() {

	}
	
	/**
	 * Adds another listener
	 * @param {@link BLAST_TaskFinished_listener} listener
	 */
	protected synchronized void addListener(BLAST_TaskFinished_listener listener){
		this.listeners.add(listener);
	}
	
	/**
	 * Removes a certain listener
	 * @param {@link BLAST_TaskFinished_listener} listener to remove
	 */
	protected synchronized void removeListener(BLAST_TaskFinished_listener listener){
		this.listeners.remove(listener);
	}
}
