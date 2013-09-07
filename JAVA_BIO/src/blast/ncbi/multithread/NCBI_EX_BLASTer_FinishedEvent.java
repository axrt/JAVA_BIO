/**
 * 
 */
package blast.ncbi.multithread;

import java.util.EventObject;

import blast.multithread.BLASTer;

//TODO: document
/**
 * @author axrt
 * 
 */
public class NCBI_EX_BLASTer_FinishedEvent<B extends BLASTer> extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 336364223009671905L;

	/**
	 * @param blaster {@code B} that has finished execution
	 */
	public NCBI_EX_BLASTer_FinishedEvent(B blaster) {
		super(blaster);
	}

}
