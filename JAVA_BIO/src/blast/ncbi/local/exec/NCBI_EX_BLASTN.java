package blast.ncbi.local.exec;

import format.fasta.nucleotide.NucleotideFasta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class NCBI_EX_BLASTN<T extends NucleotideFasta> extends NCBI_EX_BLAST<T> {


    /**
     * @param {@link        List<Fasta>} - a list of query fasta records
     * @param {@link        List<String>} - - a list of query fasta record IDs
     * @param tempDir       {@link java.io.File} - A temporary directory that will be used to dump
     *                      the input and output files, that are used by the ncbi+
     *                      executable
     * @param executive     {@link java.io.File} A {@link blast.ncbi.local.exec.NCBI_EX_BLAST_FileOperator} that will
     *                      allow to create an input file as well as catch the blast
     *                      output
     * @param parameterList {@link String[]} A list of parameters. Should maintain a
     *                      certain order. {"<-command>", "[value]"}, just the way if in
     *                      the blast+ executable input
     */
    protected NCBI_EX_BLASTN(List<T> query, List<String> query_IDs, File tempDir, File executive, String[] parameterList,NCBI_EX_BLAST_FileOperator<T> fileOperator) {
        super(query, query_IDs, tempDir, executive, parameterList,fileOperator);
    }
    /**
     * @param query         {@link List} a list of query
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
     * @return a new instance of {@link NCBI_EX_BLASTN} from a given set of
     *         parameters
     */
    public static <T extends NucleotideFasta>NCBI_EX_BLASTN newDefaultInstance(
            List<T> query, List<String> query_IDs,
            File tempDir, File executive, String[] parameterList,NCBI_EX_BLAST_FileOperator<T> fileOperator) {
        if (query_IDs == null) {
            query_IDs = new ArrayList<>();
        }
        if (query == null) {
            query = new ArrayList<>();
        }
        // TODO: input a check for whether both lists are empty or declared null
        return new NCBI_EX_BLASTN<T>(query, query_IDs, tempDir, executive,
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
