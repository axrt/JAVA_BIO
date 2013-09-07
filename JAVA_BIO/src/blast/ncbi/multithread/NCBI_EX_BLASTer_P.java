package blast.ncbi.multithread;
//TODO: document
import java.io.File;
import java.util.List;

import blast.ncbi.local.exec.NCBI_EX_BLASTP;
import format.fasta.Fasta;
import format.fasta.protein.ProteinFasta;

public abstract class NCBI_EX_BLASTer_P<N extends NCBI_EX_BLASTP<T>,T extends ProteinFasta> extends NCBI_EX_BLASTer<N,T> {

	protected NCBI_EX_BLASTer_P(List<T> queryList,
			List<String> queryListAC, int numberOfThreads, int batchSize,
			File tempDir, File executive, String[] parameterList) {
		super(queryList, queryListAC, numberOfThreads, batchSize, tempDir,
				executive, parameterList);
	}

}
