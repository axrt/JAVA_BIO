/**
 * 
 */
package rmi.ncbi;

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
