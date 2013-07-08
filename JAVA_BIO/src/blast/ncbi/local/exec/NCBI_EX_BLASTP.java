package blast.ncbi.local.exec;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import format.fasta.protein.ProteinFasta;

/**
 * An abstraction, that describes a set of general features, that are specific
 * to a blastp executable from the ncbi server
 *
 * @author axrt
 */
public abstract class NCBI_EX_BLASTP<T extends ProteinFasta> extends NCBI_EX_BLAST {

    // TODO: think of smth what to do about the ids

    /**
     * @param query         {@link List<? extends ProteinFasta>} a list of query
     *                      fasta-formatted records
     * @param query_IDs     {@link List<String>} a list of AC numbers of sequences in a
     *                      database
     * @param tempDir       {@link File} - A temporary directory that will be used to dump
     *                      the input and output files, that are used by the ncbi+
     *                      executable
     * @param executable    {@link File} A {@link NCBI_EX_BLAST_FileOperator} that will
     *                      allow to create an input file as well as catch the blast
     *                      output
     * @param parameterList {@link String[]} A list of parameters. Should maintain a
     *                      certain order. {"<-command>", "[value]"}, just the way if in
     *                      the blast+ executable input
     */
    protected NCBI_EX_BLASTP(List<T> query,
                             List<String> query_IDs, File tempDir, File executable,
                             String[] parameterList,NCBI_EX_BLAST_FileOperator fileOperator) {
        super(query, query_IDs, tempDir, executable, parameterList,fileOperator);
    }

    /**
     * @param query         {@link List<T extends ProteinFasta>} a list of query
     *                      fasta-formatted records
     * @param query_IDs     {@link List<String>} a list of AC numbers of sequences in a
     *                      database
     * @param tempDir       {@link File} - A temporary directory that will be used to dump
     *                      the input and output files, that are used by the ncbi+
     *                      executable
     * @param executive     {@link File} A {@link NCBI_EX_BLAST_FileOperator} that will
     *                      allow to create an input file as well as catch the blast
     *                      output
     * @param parameterList {@link String[]} A list of parameters. Should maintain a
     *                      certain order. {"<-command>", "[value]"}, just the way if in
     *                      the blast+ executable input
     * @return a new instance of {@link NCBI_EX_BLASTP} from a given set of
     *         parameters
     */
    public static <T extends ProteinFasta>NCBI_EX_BLASTP newDefaultInstance(
            List<T> query, List<String> query_IDs,
            File tempDir, File executive, String[] parameterList,NCBI_EX_BLAST_FileOperator fileOperator) {
        if (query_IDs == null) {
            query_IDs = new ArrayList<String>();
        }
        if (query == null) {
            query = new ArrayList<T>();
        }
        // TODO: input a check for whether both lists are empty or declared null
        return new NCBI_EX_BLASTP(query, query_IDs, tempDir, executive,
                parameterList,fileOperator) {

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
        };
    }

}
