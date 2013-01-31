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
	public void selfDeploy() {
		try {
			System.setProperty("java.rmi.server.hostname", this.uri);
			RemoteBLASTer thisStub = (RemoteBLASTer) UnicastRemoteObject
					.exportObject(this, 9531);
			Registry registry = LocateRegistry.createRegistry(this.port);
			registry.rebind(this.name, thisStub);
			//
			this.blaster.addListener(this);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * 
	 */
	@Override
	public boolean hasNessessaryDataBase(String databaseName) {

		return false;
	}

	/**
	 * 
	 */
	@Override
	public void deployAbsentDataBase(OutputStream databaseStream) {

	}

	/**
	 * 
	 */
	@Override
	public synchronized List<? extends NCBI_EX_BLASTer> processDelegatedBLASTBatch(
			List<? extends Fasta> queryList) throws InterruptedException {
		//
		wait();

		return null;
	}

	@Override
	public synchronized void handleAFinishedBLASTer(
			NCBI_EX_BLASTer_FinishedEvent event) {

		notify();

	}
}
