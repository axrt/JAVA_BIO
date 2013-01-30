package BLAST.NCBI.local.exec;

//TODO: document
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import format.fasta.Fasta;

import util.UtilFileOperator;

public class NCBI_EX_BLAST_FileOperator extends UtilFileOperator {
	
	protected NCBI_EX_BLAST_FileOperator(){
		super();
	}

	protected void writeFastaListToFile(List<? extends Fasta> fastaList, File outputFile)
			throws IOException {

		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
				outputFile, true));
		for (int i = 0; i < fastaList.size(); i++) {
			bufferedWriter.write(fastaList.get(i).toString());
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		bufferedWriter.close();
	}

	protected String readOutputFile(File outputFile) throws IOException {
		StringBuilder outputBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				outputFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			outputBuilder.append(line);
			outputBuilder.append('\n');
		}
		bufferedReader.close();
		return new String(outputBuilder);
	}

	protected InputStream readOutputXML(File outputFile) throws IOException {
		//return new URL("file://"+outputFile.getPath()).openStream();
		return new FileInputStream(outputFile);
	}

	protected void createTMPFolder(File tmpFolder) throws IOException {
		if (!tmpFolder.exists()) {
			tmpFolder.mkdir();
		}
	}

}
