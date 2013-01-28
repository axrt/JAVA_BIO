package BLAST.NCBI.local.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.derby.impl.io.CPStorageFactory;
import org.apache.derby.impl.services.bytecode.BCJava;
import org.xml.sax.SAXException;

import BLAST.NCBI.remote.NCBI_Q_BLAST_Helper;

import format.fasta.Fasta;

public abstract class NCBI_EX_BLASTP extends NCBI_EX_BLAST {

	// TODO: think of smth what to do about the ids

	protected NCBI_EX_BLASTP(List<Fasta> query, List<String> query_IDs,
			File tempDir, File executive, String[] parameterList) {
		super(query, query_IDs, tempDir, executive, parameterList);
	}

	protected NCBI_EX_BLASTP(List<Fasta> query, File tempDir, File executive,
			String[] parameterList) {
		super(query, tempDir, executive, parameterList);
	}

	public static NCBI_EX_BLASTP newDefaultInstance(List<Fasta> query,
			List<String> query_IDs, File tempDir, File executive,
			String[] parameterList) {
		if (query_IDs == null) {
			query_IDs = new ArrayList<String>();
		}
		if (query == null) {
			query = new ArrayList<Fasta>();
		}
		// TODO: input a check for whether both lists are empty or declared null
		List<Fasta> upCast = new ArrayList<Fasta>(query.size());
		for (int i = 0; i < query.size(); i++) {
			upCast.add((Fasta) query.get(i));
		}
		return new NCBI_EX_BLASTP(query, query_IDs, tempDir, executive,
				parameterList) {

			@Override
			public void run() {
				try {
					this.BLAST();

					this.BLASTed = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void BLAST() throws IOException, InterruptedException,
					JAXBException, SAXException {
				// Create the tmp folder
				this.fileOperator.createTMPFolder(tempDir);
				// Flush down the query
				this.fileOperator.writeFastaListToFile(query, this.inputFile);
				// Crate the operating command string
				String[] command = new String[parameterList.length + 7];
				command[0] = this.executive.getPath();
				command[1] = "-i";
				command[2] = this.inputFile.getPath();
				command[3] = "-o";
				command[4] = this.outputFile.getPath();
				command[5] = "-outfmt";
				command[6] = "5";
				// Copy all the parameters
				for (int i = 7; i < command.length; i++) {
					command[i] = parameterList[i - 7];
				}
				// Build a process
				Process p = Runtime.getRuntime().exec(command);
				p.waitFor();
				String s;
				// In case of an error - try to recover
				BufferedReader stdError = new BufferedReader(
						new InputStreamReader(p.getErrorStream()));
				while ((s = stdError.readLine()) != null) {
					if (s.contains("Cannot memory map file")) {
						this.BLAST();
					}
				}
				// Suck in the output
				this.blastOutput = NCBI_Q_BLAST_Helper
						.catchBLASTOutput(this.fileOperator
								.readOutputXML(this.outputFile));
				// Cleanup
				this.inputFile.delete();
                this.outputFile.delete();
                //Note: temp directory will be removed only if not used by any other parallel process
                this.tempDir.delete();
			}
		};
	}

}
