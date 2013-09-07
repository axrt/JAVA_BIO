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
