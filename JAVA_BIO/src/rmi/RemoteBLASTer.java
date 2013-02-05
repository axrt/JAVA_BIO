/**
 * 
 */
package rmi;

//TODO: document
import java.io.OutputStream;
import java.rmi.Remote;
import java.util.List;

import format.fasta.Fasta;

import BLAST.BLAST;

/**
 * Adds to an implementing class ability to get a remote BLAST task from a
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
	 * 
	 * @param databaseName
	 * @return
	 */
	public boolean hasNessessaryDataBase(String databaseName);

	/**
	 * 
	 * @param databaseStream
	 */
	public void deployAbsentDataBase(OutputStream databaseStream);

}
