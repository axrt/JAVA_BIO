/**
 * 
 */
package rmi;

import blast.ncbi.local.exec.NCBI_EX_BLAST;
import format.fasta.Fasta;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

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
//TODO: document
/**
 * @author axrt
 * 
 */
public abstract class RemoteBLASTSprite<B extends BLAST,T extends Fasta> implements RemoteBLASTer<B,T>, Runnable {

	protected String name;
	protected int port;
	protected String uri;
	protected int blastBatchSize;


	protected RemoteBLASTSprite(String name, int port, String uri, int blastBatchSize) {
		super();
		this.name = name;
		this.port = port;
		this.uri = uri;
		this.blastBatchSize=blastBatchSize;
	}

	/**
	 * @throws RemoteException
	 * 
	 */
	public void selfDeploy() throws RemoteException {

		System.setProperty(RemoteBLASTer_Helper.rmi_sever_hostname, this.uri);
		RemoteBLASTer thisStub = (RemoteBLASTer) UnicastRemoteObject
				.exportObject(this, this.port);
		Registry registry = LocateRegistry.createRegistry(this.port);
		registry.rebind(this.name, thisStub);
		//
	}

	/**
	 * 
	 */
	@Override
	public abstract List<B> processDelegatedBLASTBatch(
			List<T> queryList) throws Exception;

}
