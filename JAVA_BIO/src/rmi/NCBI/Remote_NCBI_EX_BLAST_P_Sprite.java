/**
 * 
 */
package rmi.NCBI;

//TODO: document
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
			NCBI_EX_BLASTer_P blaster, int blastBatchSize) {
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

	public static Remote_NCBI_EX_BLAST_P_Sprite newInstance(String name,
			int port, String uri, NCBI_EX_BLASTer_P blaster, int blastBatchSize) {
		return new Remote_NCBI_EX_BLAST_P_Sprite(name, port, uri, blaster,
				blastBatchSize);
	}

}
