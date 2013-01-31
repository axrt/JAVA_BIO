/**
 * 
 */
package BLAST.NCBI.multithread;

/**
 * @author axrt
 * 
 */
public interface NCBI_EX_BLASTer_TaskFinished_listener {
	/**
	 * Determines how to handle the event of a finished {@link NCBI_EX_BLASTer}
	 */
	public void handleAFinishedBLASTer(NCBI_EX_BLASTer_FinishedEvent event);
}
