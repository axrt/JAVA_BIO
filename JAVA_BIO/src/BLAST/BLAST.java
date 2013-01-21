/**
 * 
 */
package BLAST;

/**
 * @author axrt
 * A general abstraction, represents a "Basic Local Alignment Tool process" concept.
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
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
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
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
}
