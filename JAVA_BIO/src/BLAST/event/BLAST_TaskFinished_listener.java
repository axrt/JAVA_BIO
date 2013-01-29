/**
 * 
 */
package BLAST.event;

//TODO: document

import java.util.EventListener;

import BLAST.BLAST;

/**
 * @author axrt
 *
 */
public interface BLAST_TaskFinished_listener extends EventListener {
    
	public void handleAFinishedBLAST(BLAST_FinishedEvent event) throws Exception;
	
}
