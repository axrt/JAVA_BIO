package BLAST.NCBI.local.exec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import BLAST.NCBI.output.BlastOutput;

import format.fasta.Fasta;

import util.UtilFileOperator;

/**
 * An implementation of a {@link UtilFileOperator} that is used specifically by
 * {@link NCBI_EX_BLAST}-extending implementations to output to disk and input
 * the results from the process
 *
 * @author axrt
 */
public class NCBI_EX_BLAST_FileOperator extends UtilFileOperator {
    /**
     * Default constructor
     */
    protected NCBI_EX_BLAST_FileOperator() {
        super();
    }

    /**
     * Writes a list of fata-formatted {@link Fasta} records to disk under a
     * given filename
     *
     * @param fastaList  {@link List<Fasta>} of fata-formatted {@link Fasta} records as
     *                   a query input
     * @param outputFile {@link File} A file that will be created by the blast+
     *                   executable, and the name of the file will be sent to the
     *                   executable as a parameter
     * @throws IOException
     */
    protected void writeFastaListToFile(List<? extends Fasta> fastaList,
                                        File outputFile) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            // If the file does not exist yet - create one
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            // Write to the file line by line
            bufferedWriter = new BufferedWriter(new FileWriter(
                    outputFile, true));
            for (int i = 0; i < fastaList.size(); i++) {
                bufferedWriter.write(fastaList.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } finally {
            // Close the reader
            bufferedWriter.close();
        }

    }

    /**
     * Reads an output file from the blast+ executable as a text file (actually,
     * always is one)
     *
     * @param outputFile {@link File} A file that will automatically be created, fasta
     *                   records from the batch will then be dumped to the file, and
     *                   then it will be used as an input to the blast+ executive
     * @return {@link String} that contains the file content
     * @throws IOException
     */
    protected String readOutputFile(File outputFile) throws IOException {
        // Create a new stringbuilder to hold the content of the file
        StringBuilder outputBuilder = new StringBuilder();
        // Read the file line by line
        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                outputFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            outputBuilder.append(line);
            outputBuilder.append('\n');
        }
        // Close the reader and return a new immutable copy of the content
        bufferedReader.close();
        return new String(outputBuilder);
    }

    /**
     * Opens an input stream that is further used by the {@link NCBI_EX_BLAST}
     * -extending implementations to suck in their output and unmarshall it from
     * XML to ab object representation of a JAXB-pre-compiled
     * {@link BlastOutput}
     *
     * @param outputFile {@link File} An XML file that will be created by the blast+
     *                   executable as an output
     * @return {@link InputStream} opened from the file
     * @throws IOException
     */
    protected InputStream readOutputXML(File outputFile) throws IOException {
        // Another funny way would be to return like this:
        // return new URL("file://"+outputFile.getPath()).openStream();
        return new FileInputStream(outputFile);
    }

    /**
     * Create a temporary directory for a {@link NCBI_EX_BLAST}-extending
     * implementation to operate upon
     *
     * @param tempDir {@link File} - A temporary directory that will be used to dump
     *                the input and output files, that are used by the ncbi+
     *                executable
     * @throws IOException
     */
    protected void createTMPFolder(File tmpFolder) throws IOException {
        // Create the folder if it does not exist (essential for a multithreaded
        // environment where other blasts may want to create one as well using
        // the same name)
        if (!tmpFolder.exists()) {
            tmpFolder.mkdir();
        }
    }
}
