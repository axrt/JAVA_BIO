/**
 * 
 */
package BLAST.event;

import java.util.EventListener;

import BLAST.BLAST;

/**
 * Must be implemented in order to listen to {@link BLAST_FinishedEvent}
 * 
 * @author axrt
 */
public interface BLAST_TaskFinished_listener extends EventListener {
	/**
	 * Determines how to handle the event of a finished {@link BLAST}
	 */
	public void handleAFinishedBLAST(BLAST_FinishedEvent event);

}
