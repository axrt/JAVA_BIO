package blast.ncbi.multithread;
//TODO: document
import java.io.File;
import java.util.List;

import blast.ncbi.local.exec.NCBI_EX_BLASTP;
import format.fasta.Fasta;
import format.fasta.protein.ProteinFasta;
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
public abstract class NCBI_EX_BLASTer_P<N extends NCBI_EX_BLASTP<T>,T extends ProteinFasta> extends NCBI_EX_BLASTer<N,T> {

	protected NCBI_EX_BLASTer_P(List<T> queryList,
			List<String> queryListAC, int numberOfThreads, int batchSize,
			File tempDir, File executive, String[] parameterList) {
		super(queryList, queryListAC, numberOfThreads, batchSize, tempDir,
				executive, parameterList);
	}

}
