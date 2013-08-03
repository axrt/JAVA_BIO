/**
 *
 */
package blast.ncbi.local.exec;

import blast.ncbi.NCBI_BLAST;
import blast.ncbi.output.NCBI_BLAST_OutputHelper;
import format.fasta.Fasta;
import org.xml.sax.SAXException;
import util.SystemUtil;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * A generalized abstraction of an ncbi blast process that is performed locally
 * through an ncbi executable from
 * ftp://ftp.ncbi.nlm.nih.gov/blast/executables/blast+/LATEST/
 *
 * @author axrt
 */
public abstract class NCBI_EX_BLAST<T extends Fasta> extends NCBI_BLAST {
    /**
     * A {@link NCBI_EX_BLAST_FileOperator} that will allow to create an input
     * file as well as catch the blast output
     */
    protected final NCBI_EX_BLAST_FileOperator fileOperator;
    /**
     * A file that will automatically be created, fasta records from the batch
     * will then be dumped to the file, and then it will be used as an input to
     * the blast+ executive
     */
    protected File inputFile;
    /**
     * A temporary directory that will be used to dump the input and output
     * files, that are used by the ncbi+ executable
     */
    protected File tempDir;
    /**
     * A file that will be created by the blast+ executable, and the name of the
     * file will be sent to the executable as a parameter
     */
    protected File outputFile;
    /**
     * A full path (or a valid alias) to a blast+ executable
     */
    protected File executable;
    /**
     * A list of parameters. Should maintain a certain order. {"<-command>",
     * "[value]"}, just the way if in the blast+ executable input
     */
    protected String[] parameterList;

    /**
     * @param {@link        List<T>} - a list of query fasta records, where T exends {@link Fasta}
     * @param {@link        List<String>} - - a list of query fasta record IDs
     * @param tempDir       {@link File} - A temporary directory that will be used to dump
     *                      the input and output files, that are used by the ncbi+
     *                      executable
     * @param executive     {@link File} A {@link NCBI_EX_BLAST_FileOperator} that will
     *                      allow to create an input file as well as catch the blast
     *                      output
     * @param parameterList {@link String[]} A list of parameters. Should maintain a
     *                      certain order. {"<-command>", "[value]"}, just the way if in
     *                      the blast+ executable input
     * @param fileOperator  {@link NCBI_EX_BLAST_FileOperator} to allow file load/save
     */
    protected NCBI_EX_BLAST(List<T> query,
                            List<String> query_IDs, File tempDir, File executive,
                            String[] parameterList, NCBI_EX_BLAST_FileOperator fileOperator) {
        super(query, query_IDs);
        this.fileOperator = fileOperator;
        this.tempDir = tempDir;
        this.executable = executive;
        this.parameterList = parameterList;
        // Take care of the directories
        // The uniqueness of the input and output file names is maintained by
        // that the names contain the hashCode of the blast objects, thereby
        // ensuring uniqueness even in multithreaded environment
        //TODO: correct this to a non-SysFS
        this.inputFile = new File(this.tempDir.getPath() + SystemUtil.SysFS
                + "in_" + String.valueOf(this.hashCode()));
        this.outputFile = new File(this.tempDir.getPath() + SystemUtil.SysFS
                + "out_" + String.valueOf(this.hashCode()));
    }

    /**
     * Performs the blast on a local machine throught blast-executable
     * against a local database
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws JAXBException
     * @throws SAXException
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
        final String[] command = new String[this.parameterList.length + 7];
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
        try {
            // Build a process
            final Process p = Runtime.getRuntime().exec(command);
            p.waitFor();

            String s;
            try (BufferedReader stdNorm = new BufferedReader(
                    new InputStreamReader(p.getInputStream()))) {

                //TODO: For debuggin purposes only, an API should not print out anything!!!
                while ((s = stdNorm.readLine()) != null) {
                    System.out.println("STD:> " + s);
                }
            }
            // In case of an error - try to recover
            try (BufferedReader stdError = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()))) {

                while ((s = stdError.readLine()) != null) {
                    System.out.println("ERR:> " + s);
                    if (s.contains("Cannot memory map file")) {
                        this.BLAST();
                    }
                }
            }
            if (this.outputFile.exists()) {
                // Suck in the output
                this.blastOutput = NCBI_BLAST_OutputHelper
                        .catchBLASTOutput(this.fileOperator
                                .readOutputXML(this.outputFile));
            }else throw new IOException("Something might have gone wrong with the BLAST process, check logs.");
        } finally {
            // Cleanup
            if (this.inputFile.exists()) {
                this.inputFile.delete();
            }
            if (this.outputFile.exists()) {
                this.outputFile.delete();
            }
        }
    }

}
