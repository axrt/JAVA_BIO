/**
 * 
 */
package rmi;

import java.util.ArrayList;
import java.util.List;

import format.fasta.Fasta;
//TODO: document

import blast.BLAST;
import blast.multithread.BLASTer;

/**
 * @author axrt
 * 
 */
public abstract class RemoteBLASTerCore implements Runnable {

	private RemoteBLASTer sprite;
	private BLASTer blaster;

	/**
	 * @param sprite
	 * @param blaster
	 */
	protected RemoteBLASTerCore(RemoteBLASTer sprite, BLASTer blaster) {
		super();
		this.sprite = sprite;
		this.blaster = blaster;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	// TODO: delete comments
	protected List<? extends BLAST> fillUpSprite() throws Exception {
		System.out.println("Size before fillup: "
				+ this.blaster.getQueryListSize());
		List<Fasta> fastas = new ArrayList<Fasta>();
		Fasta f = null;
		System.out.println("Preferred batchsize: "
				+ this.sprite.preferredBatchSize());
		while (fastas.size() < this.sprite.preferredBatchSize()) {
			if ((f = this.blaster.pollFasta()) != null) {
//				System.out.println(f);
				fastas.add(f);
			}
		}
		System.out.println("Size after fillup: "
				+ this.blaster.getQueryListSize());
		return this.sprite.processDelegatedBLASTBatch(fastas);
	}
}
