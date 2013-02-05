/**
 * 
 */
package rmi;

import format.fasta.Fasta;

import java.io.File;
import java.io.OutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import BLAST.BLAST;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_FinishedEvent;
import BLAST.NCBI.multithread.NCBI_EX_BLASTer_TaskFinished_listener;

//TODO: document
/**
 * @author axrt
 * 
 */
public abstract class RemoteBLASTSprite implements RemoteBLASTer, Runnable,
		NCBI_EX_BLASTer_TaskFinished_listener {

	protected String name;
	protected int port;
	protected String uri;
	protected NCBI_EX_BLASTer blaster;
	protected int blastBatchSize;
	protected File executable;
	protected File tmpDir;
	protected File databaseDir;

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
	protected RemoteBLASTSprite(String name, int port, String uri,
			NCBI_EX_BLASTer blaster, int blastBatchSize, File executable,
			File tmpDir, File databaseDir) {
		super();
		this.name = name;
		this.port = port;
		this.uri = uri;
		this.blaster = blaster;
		this.blastBatchSize = blastBatchSize;
		this.executable = executable;
		this.tmpDir = tmpDir;
		this.databaseDir = databaseDir;
	}

	/**
	 * 
	 */
	public void selfDeploy() throws Exception {

		System.setProperty(RemoteBLASTer_Helper.rmi_sever_hostname, this.uri);
		RemoteBLASTer thisStub = (RemoteBLASTer) UnicastRemoteObject
				.exportObject(this, this.port);
		Registry registry = LocateRegistry.createRegistry(this.port);
		registry.rebind(this.name, thisStub);
		//
		this.blaster.addListener(this);
	}

	/**
	 * 
	 */
	@Override
	public abstract boolean hasNessessaryDataBase(String databaseName);

	/**
	 * 
	 */
	@Override
	public abstract void deployAbsentDataBase(OutputStream databaseStream);

	/**
	 * 
	 */
	@Override
	public synchronized List<? extends BLAST> processDelegatedBLASTBatch(
			List<? extends Fasta> queryList) throws Exception {
		this.blaster.appendFasta(queryList);
		if (!this.blaster.isWorking()) {
			this.blaster.launch();
		}
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
