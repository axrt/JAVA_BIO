package blast.ncbi.local.exec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import blast.ncbi.output.BlastOutput;

import format.fasta.Fasta;

import util.ExecutableUtilFileOperator;
import util.UtilFileOperator;

/**
 * An implementation of a {@link UtilFileOperator} that is used specifically by
 * {@link NCBI_EX_BLAST}-extending implementations to output to disk and input
 * the results from the process
 *
 * @author axrt
 */
public class NCBI_EX_BLAST_FileOperator<F extends Fasta> extends ExecutableUtilFileOperator<F> {
    /**
     * Default constructor
     */
    protected NCBI_EX_BLAST_FileOperator() {
        super();
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

}
