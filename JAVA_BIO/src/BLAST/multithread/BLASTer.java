/**
 * 
 */
package BLAST.multithread;

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
public abstract class BLASTer implements BLAST_TaskFinished_listener{
     
	private ExecutorService executorService;
	
	protected int numberOfThereds;
	
	protected int batchSize;
	
	protected Queue<? extends Fasta> queryList;
	
	/**
	 * Constructor
	 */
	public BLASTer(List<? extends Fasta> queryList, int numberOfThreads, int batchSize) {
		this.queryList=new ConcurrentLinkedQueue(queryList);
		this.numberOfThereds=numberOfThreads;
		this.batchSize=batchSize;
		this.executorService=Executors.newFixedThreadPool(this.numberOfThereds);
	}
	
    protected synchronized Future<?> submitBLAST(BLAST blast){
    	return this.executorService.submit(blast);
    }
}
