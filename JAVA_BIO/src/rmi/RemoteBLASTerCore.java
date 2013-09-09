/**
 * 
 */
package rmi;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
public abstract class RemoteBLASTerCore<B extends BLAST,T extends Fasta> implements Runnable {

	private RemoteBLASTer<B,T> sprite;
	private BLASTer<B,T> blaster;

	/**
	 * @param sprite {@link RemoteBLASTer} that will be used to perform blasts
	 * @param blaster {@link BLASTer} that will be the blasting core
	 */
	protected RemoteBLASTerCore(RemoteBLASTer<B,T> sprite, BLASTer<B,T>  blaster) {
		super();
		this.sprite = sprite;
		this.blaster = blaster;
	}

	/**
	 * 
	 * @return {@link List} of {@code B}
	 * @throws Exception
	 */
	// TODO: delete comments
	protected List<B> fillUpSprite() throws Exception {
		System.out.println("Size before fillup: "
				+ this.blaster.getQueryListSize());
		List<T> fastas = new ArrayList<>();
		T f;
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
