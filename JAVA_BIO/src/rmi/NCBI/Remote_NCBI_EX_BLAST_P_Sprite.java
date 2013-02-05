/**
 * 
 */
package rmi.NCBI;

//TODO: document
import java.io.File;
import java.io.OutputStream;

import BLAST.NCBI.multithread.NCBI_EX_BLASTer_P;

/**
 * @author axrt
 * 
 */
public class Remote_NCBI_EX_BLAST_P_Sprite extends Remote_NCBI_EX_BLAST_Sprite {

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
	protected Remote_NCBI_EX_BLAST_P_Sprite(String name, int port, String uri,
			NCBI_EX_BLASTer_P blaster, int blastBatchSize, File executable,
			File tmpDir, File databaseDir) {
		super(name, port, uri, blaster, blastBatchSize, executable, tmpDir,
				databaseDir);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rmi.RemoteBLASTSprite#hasNessessaryDataBase(java.lang.String)
	 */
	@Override
	public boolean hasNessessaryDataBase(String databaseName) {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rmi.RemoteBLASTSprite#deployAbsentDataBase(java.io.OutputStream)
	 */
	@Override
	public void deployAbsentDataBase(OutputStream databaseStream) {

	}

}
