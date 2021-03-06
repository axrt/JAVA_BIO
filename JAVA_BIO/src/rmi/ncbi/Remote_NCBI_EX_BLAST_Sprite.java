/**
 * 
 */
package rmi.ncbi;
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
import java.rmi.RemoteException;
import java.util.List;

import blast.ncbi.local.exec.NCBI_EX_BLAST;
import rmi.RemoteBLASTSprite;
import blast.BLAST;
import blast.ncbi.multithread.NCBI_EX_BLASTer;
import blast.ncbi.multithread.NCBI_EX_BLASTer_FinishedEvent;
import blast.ncbi.multithread.NCBI_EX_BLASTer_TaskFinished_listener;
import format.fasta.Fasta;

/**
 *
 */
public abstract class Remote_NCBI_EX_BLAST_Sprite<B extends NCBI_EX_BLAST<T>,T extends Fasta> extends RemoteBLASTSprite<B,T>
		implements NCBI_EX_BLASTer_TaskFinished_listener {
	private NCBI_EX_BLASTer<B,T> blaster;


	protected Remote_NCBI_EX_BLAST_Sprite(String name, int port, String uri,
			NCBI_EX_BLASTer<B,T> blaster,int blastBatchSize) {
		super(name, port, uri,blastBatchSize);
		this.blaster = blaster;
	}

	@Override
	public void selfDeploy() throws RemoteException {
		super.selfDeploy();
		this.blaster.addListener(this);
	}

	@Override
	public synchronized List<B> processDelegatedBLASTBatch(
			List<T> queryList) throws InterruptedException {
			
		    this.blaster.appendFasta(queryList);

			this.blaster.launch();

			//
			wait();
			return this.blaster.getFinishedBLASTs();
	}

	@Override
	public synchronized void handleAFinishedBLASTer(
			NCBI_EX_BLASTer_FinishedEvent event) {
		notify();
	}
}
