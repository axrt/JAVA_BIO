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
//TODO: document
import blast.ncbi.local.exec.NCBI_EX_BLAST;
import blast.ncbi.local.exec.NCBI_EX_BLASTP;
import blast.ncbi.multithread.NCBI_EX_BLASTer_P;
import format.fasta.protein.ProteinFasta;

/**
 * @author axrt
 * 
 */
public class Remote_NCBI_EX_BLAST_P_Sprite <B extends NCBI_EX_BLASTP<T>,T extends ProteinFasta> extends Remote_NCBI_EX_BLAST_Sprite<B,T> {


	protected Remote_NCBI_EX_BLAST_P_Sprite(String name, int port, String uri,
			NCBI_EX_BLASTer_P<B,T> blaster, int blastBatchSize) {
		super(name, port, uri, blaster, blastBatchSize);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

	}

	@Override
	public int preferredBatchSize() {

		return this.blastBatchSize;
	}

	public static <B extends NCBI_EX_BLASTP<T>,T extends ProteinFasta> Remote_NCBI_EX_BLAST_P_Sprite newInstance(String name,
			int port, String uri, NCBI_EX_BLASTer_P<B,T> blaster, int blastBatchSize) {
		return new Remote_NCBI_EX_BLAST_P_Sprite<B,T>(name, port, uri, blaster,
				blastBatchSize);
	}

}
