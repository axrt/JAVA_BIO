/**
 * 
 */
package rmi;

//TODO: document
import java.rmi.Remote;
import java.util.List;

import format.fasta.Fasta;

import blast.BLAST;

/**
 * Adds to an implementing class ability to get a remote blast task from a
 * calling module
 * 
 * @author axrt
 * 
 */
public interface RemoteBLASTer<B extends BLAST,T extends Fasta> extends Remote {

	/**
	 * 
	 * @param queryList {@link List} a list of query records
	 * @return a {@link List} of processed BLASTs with results
	 * @throws InterruptedException 
	 */
	public List<B> processDelegatedBLASTBatch(
			List<T> queryList) throws Exception;
    /**
     * Returns a number of query fastas per one batch
     * @return {@code int} number of query records
     */
    public int preferredBatchSize();
}
