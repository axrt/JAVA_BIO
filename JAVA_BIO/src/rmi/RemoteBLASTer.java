/**
 * 
 */
package rmi;

//TODO: document
import java.rmi.Remote;
import java.util.List;

import format.fasta.Fasta;

import blast.BLAST;
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
/**
 * Adds to an implementing class ability to get a remote blast task from a
 * calling module
 * 
 * @author axrt
 * 
 */
public interface RemoteBLASTer<B extends BLAST,T extends Fasta> extends Remote {

	/**
	 * 
	 * @param queryList {@link List} a list of query records
	 * @return a {@link List} of processed BLASTs with results
	 * @throws InterruptedException 
	 */
	public List<B> processDelegatedBLASTBatch(
			List<T> queryList) throws Exception;
    /**
     * Returns a number of query fastas per one batch
     * @return {@code int} number of query records
     */
    public int preferredBatchSize();
}
