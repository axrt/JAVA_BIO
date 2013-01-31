/**
 * 
 */
package BLAST.NCBI.multithread;

import java.util.EventObject;

import BLAST.multithread.BLASTer;

//TODO: document
/**
 * @author axrt
 * 
 */
public class NCBI_EX_BLASTer_FinishedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 336364223009671905L;

	/**
	 * @param arg0
	 */
	public NCBI_EX_BLASTer_FinishedEvent(BLASTer blaster) {
		super(blaster);
	}

}
