package BLAST.event;

import java.util.EventObject;

/**
 * An event wrapper class that allows to notify a listening object (presumably
 * {@link BLAST_TaskFinished_listener} by passing a pointer
 * 
 * @author axrt
 * 
 */
public class BLAST_FinishedEvent extends EventObject {
	/**
	 * A constructor that wraps the source object into {@link Object}
	 * 
	 * @param source
	 */
	public BLAST_FinishedEvent(Object source) {
		super(source);
	}

}
