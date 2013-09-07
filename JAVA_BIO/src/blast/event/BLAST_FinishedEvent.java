package blast.event;

import java.util.EventObject;

import blast.BLAST;

/**
 * An event wrapper class that allows to notify a listening object (presumably
 * {@link BLAST_TaskFinished_listener} by passing a pointer
 * 
 * @author axrt
 * 
 */
public class BLAST_FinishedEvent<B extends BLAST> extends EventObject {
	/**
	 * A constructor that wraps the source object into {@link Object}
	 * 
	 * @param source {@code B} that finished
	 */
	public BLAST_FinishedEvent(B source) {
		super(source);
	}

}
