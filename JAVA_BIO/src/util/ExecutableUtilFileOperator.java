package util;

import java.io.*;
import java.util.List;

/**
 * TODO: document
 */
public abstract class ExecutableUtilFileOperator<T> extends UtilFileOperator {
    //TODO: all files and everything probably should be kept within the file operators
    protected ExecutableUtilFileOperator() {
        super();
    }

    /**
     * Writes a list of fata-formatted {@link format.fasta.Fasta} records to disk under a
     * given filename
     *
     * @param list  {@link java.util.List} of fasta-formatted {@link format.fasta.Fasta} records as
     *                   a query input
     * @param outputFile {@link java.io.File} A file that will be created by the blast+
     *                   executable, and the name of the file will be sent to the
     *                   executable as a parameter
     * @throws java.io.IOException
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void writeFastaListToFile(List<T> list,
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
            for (T aList : list) {
                bufferedWriter.write(aList.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } finally {
            // Close the reader
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
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
    public String readOutputFile(File outputFile) throws IOException {
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
        return outputBuilder.toString();
    }

    /**
     * Create a temporary directory for a {@link blast.ncbi.local.exec.NCBI_EX_BLAST}-extending
     * implementation to operate upon
     *
     * @throws IOException
     */
    public synchronized void createTMPFolder(File tmpFolder) {
        // Create the folder if it does not exist (essential for a multithreaded
        // environment where other blasts may want to create one as well using
        // the same name)
        if (!tmpFolder.exists()) {
            tmpFolder.mkdir();
        }
    }
}
