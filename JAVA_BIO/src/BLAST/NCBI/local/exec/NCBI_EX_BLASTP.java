package BLAST.NCBI.local.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import BLAST.NCBI.output.NCBI_BLAST_OutputHelper;
import format.fasta.Fasta;
import format.fasta.ProteinFasta;

/**
 * An abstraction, that describes a set of general features, that are specific
 * to a blastp executable from the NCBI server
 * 
 * @author axrt
 * 
 */
public abstract class NCBI_EX_BLASTP extends NCBI_EX_BLAST {

	// TODO: think of smth what to do about the ids
	/**
	 * 
	 * @param query
	 *            {@link List<? extends ProteinFasta>} a list of query
	 *            fasta-formatted records
	 * @param query_IDs
	 *            {@link List<String>} a list of AC numbers of sequences in a
	 *            database
	 * @param tempDir
	 *            {@link File} - A temporary directory that will be used to dump
	 *            the input and output files, that are used by the ncbi+
	 *            executable
	 * @param executive
	 *            {@link File} A {@link NCBI_EX_BLAST_FileOperator} that will
	 *            allow to create an input file as well as catch the blast
	 *            output
	 * @param parameterList
	 *            {@link String[]} A list of parameters. Should maintain a
	 *            certain order. {"<-command>", "[value]"}, just the way if in
	 *            the blast+ executable input
	 */
	protected NCBI_EX_BLASTP(List<? extends ProteinFasta> query,
			List<String> query_IDs, File tempDir, File executive,
			String[] parameterList) {
		super(query, query_IDs, tempDir, executive, parameterList);
	}

	/**
	 * 
	 * 
	 * @param query
	 *            {@link List<? extends ProteinFasta>} a list of query
	 *            fasta-formatted records
	 * @param query_IDs
	 *            {@link List<String>} a list of AC numbers of sequences in a
	 *            database
	 * @param tempDir
	 *            {@link File} - A temporary directory that will be used to dump
	 *            the input and output files, that are used by the ncbi+
	 *            executable
	 * @param executive
	 *            {@link File} A {@link NCBI_EX_BLAST_FileOperator} that will
	 *            allow to create an input file as well as catch the blast
	 *            output
	 * @param parameterList
	 *            {@link String[]} A list of parameters. Should maintain a
	 *            certain order. {"<-command>", "[value]"}, just the way if in
	 *            the blast+ executable input
	 * @return a new instance of {@link NCBI_EX_BLASTP} from a given set of
	 *         parameters
	 */
	public static NCBI_EX_BLASTP newDefaultInstance(
			List<? extends ProteinFasta> query, List<String> query_IDs,
			File tempDir, File executive, String[] parameterList) {
		if (query_IDs == null) {
			query_IDs = new ArrayList<String>();
		}
		if (query == null) {
			query = new ArrayList<ProteinFasta>();
		}
		// TODO: input a check for whether both lists are empty or declared null
		return new NCBI_EX_BLASTP(query, query_IDs, tempDir, executive,
				parameterList) {

			@Override
			public void run() {
				try {
					// This default instance must first blast the query,
					this.BLAST();
					// Indicate that it has finished
					this.BLASTed = true;
					// Notify all of the listening modules of that it has
					// finished
					this.notifyListeners();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			/**
			 * Performs the BLASTP on a local machine throught blastp executable
			 * against a local database
			 */
			@Override
			public void BLAST() throws IOException, InterruptedException,
					JAXBException, SAXException {
				// Create the tmp folder
				this.fileOperator.createTMPFolder(this.tempDir);
				// Flush down the query
				this.fileOperator.writeFastaListToFile(this.query,
						this.inputFile);
				// Crate the operating command string
				String[] command = new String[this.parameterList.length + 7];
				command[0] = this.executable.getPath();
				command[1] = "-query";
				command[2] = this.inputFile.getPath();
				command[3] = "-out";
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
				BufferedReader stdNorm = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				//TODO: For debuggin purposes only, an API should not print out anything!!!
				while ((s = stdNorm.readLine()) != null) {
					System.out.println("STD:> " + s);
				}
				// In case of an error - try to recover
				BufferedReader stdError = new BufferedReader(
						new InputStreamReader(p.getErrorStream()));
				while ((s = stdError.readLine()) != null) {
					System.out.println("ERR:> " + s);
					if (s.contains("Cannot memory map file")) {
						this.BLAST();
					}
				}
				// Suck in the output
				this.blastOutput = NCBI_BLAST_OutputHelper
						.catchBLASTOutput(this.fileOperator
								.readOutputXML(this.outputFile));
				// Cleanup
				this.inputFile.delete();
				this.outputFile.delete();
				// Note: temp directory will be removed only if not used by any
				// other parallel process
				this.tempDir.delete();
			}
		};
	}

}
