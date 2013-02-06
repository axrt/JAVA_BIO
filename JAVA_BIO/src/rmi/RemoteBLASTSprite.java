/**
 * 
 */
package rmi;

import format.fasta.Fasta;

import java.io.File;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import BLAST.BLAST;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_FinishedEvent;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_TaskFinished_listener;
import BLAST.multithread.BLASTer;

//TODO: document
/**
 * @author axrt
 * 
 */
public abstract class RemoteBLASTSprite implements RemoteBLASTer, Runnable {

	protected String name;
	protected int port;
	protected String uri;
	protected int blastBatchSize;

	/**
	 * @param name
	 * @param port
	 * @param uri
	 * @param blaster
	 * @param blastBatchSize
	 * @param executable
	 * @param tmpDir
	 * @param databaseDir
	 */
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
	public abstract List<? extends BLAST> processDelegatedBLASTBatch(
			List<? extends Fasta> queryList) throws Exception;

}
