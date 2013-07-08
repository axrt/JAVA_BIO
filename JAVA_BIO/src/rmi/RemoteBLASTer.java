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
public interface RemoteBLASTer extends Remote {

	/**
	 * 
	 * @param queryList
	 * @return
	 * @throws InterruptedException 
	 */
	public List<? extends BLAST> processDelegatedBLASTBatch(
			List<? extends Fasta> queryList) throws Exception;
    /**
     * Returns a number of query fastas per one batch
     * @return {@code int} number of query records
     */
    public int preferredBatchSize() throws Exception;
}
