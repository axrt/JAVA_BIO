package BLAST.NCBI.multithread;
//TODO: document
import java.io.File;
import java.util.List;

import BLAST.event.BLAST_FinishedEvent;

import format.fasta.Fasta;
import format.fasta.ProteinFasta;

public abstract class NCBI_EX_BLASTer_P extends NCBI_EX_BLASTer {

	protected NCBI_EX_BLASTer_P(List<? extends ProteinFasta> queryList,
			List<String> queryListAC, int numberOfThreads, int batchSize,
			File tempDir, File executive, String[] parameterList) {
		super(queryList, queryListAC, numberOfThreads, batchSize, tempDir,
				executive, parameterList);
	}

}