/**
 *
 */
package blast.ncbi.remote;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import format.fasta.nucleotide.NucleotideFasta;
import org.xml.sax.SAXException;

import blast.ncbi.output.NCBI_BLAST_OutputHelper;

import format.fasta.protein.ProteinFasta;

/**
 * @author axrt
 */
public abstract class NCBI_Q_TBLASTN<T extends NucleotideFasta> extends NCBI_Q_BLAST<T> {

    /**
     * @param query
     * @param query_IDs
     */
    public NCBI_Q_TBLASTN(List<T> query,
                          List<String> query_IDs) {
        super(query, query_IDs);
        // Declaring a list of allowed parameters
        this.request_parameters = new NCBI_Q_BLAST_ParameterSet(new String[]{
                NCBI_Q_BLAST_Helper.ALIGNMENTS,
                NCBI_Q_BLAST_Helper.ALIGNMENT_VIEW,
                NCBI_Q_BLAST_Helper.BLAST_PROGRAM,
                NCBI_Q_BLAST_Helper.CDD_SEARCH, NCBI_Q_BLAST_Helper.DATABASE,
                NCBI_Q_BLAST_Helper.DATABASE_SORT,
                NCBI_Q_BLAST_Helper.DATABASE_PREFIX,
                NCBI_Q_BLAST_Helper.DB_GENETIC_CODE,
                NCBI_Q_BLAST_Helper.DESCRIPTIONS,
                NCBI_Q_BLAST_Helper.DISPLAY_SORT, NCBI_Q_BLAST_Helper.CMD,
                NCBI_Q_BLAST_Helper.ENTREZ_QUERY, NCBI_Q_BLAST_Helper.EXPECT,
                NCBI_Q_BLAST_Helper.EXPECT_HIGH,
                NCBI_Q_BLAST_Helper.EXPECT_LOW,
                NCBI_Q_BLAST_Helper.FIRST_QUERY_NUM,
                NCBI_Q_BLAST_Helper.FILTER,
                NCBI_Q_BLAST_Helper.FORMAT_ENTREZ_QUERY,
                NCBI_Q_BLAST_Helper.FORMAT_OBJECT,
                NCBI_Q_BLAST_Helper.FORMAT_TYPE, NCBI_Q_BLAST_Helper.GAPCOSTS,
                NCBI_Q_BLAST_Helper.GENETIC_CODE,
                NCBI_Q_BLAST_Helper.GET_SEQUENCE,
                NCBI_Q_BLAST_Helper.HITLIST_SIZE, NCBI_Q_BLAST_Helper.HSP_SORT,
                NCBI_Q_BLAST_Helper.I_THRESHOLD,
                NCBI_Q_BLAST_Helper.LCASE_MASK, NCBI_Q_BLAST_Helper.MASK_CHAR,
                NCBI_Q_BLAST_Helper.MASK_COLOR,
                NCBI_Q_BLAST_Helper.MATCH_SCORES,
                NCBI_Q_BLAST_Helper.MATRIX_NAME,
                NCBI_Q_BLAST_Helper.MAX_NUM_SEQ, NCBI_Q_BLAST_Helper.NCBI_GI,
                NCBI_Q_BLAST_Helper.NEWWIN, NCBI_Q_BLAST_Helper.NEWWINRES,
                NCBI_Q_BLAST_Helper.NOHEADER, NCBI_Q_BLAST_Helper.NUM_OVERVIEW,
                NCBI_Q_BLAST_Helper.OTHER_ADVACED,
                NCBI_Q_BLAST_Helper.PAGE_TYPE, NCBI_Q_BLAST_Helper.PHI_PATTERN,
                NCBI_Q_BLAST_Helper.PROGRAM, NCBI_Q_BLAST_Helper.PSSM,
                NCBI_Q_BLAST_Helper.QUERY,
                NCBI_Q_BLAST_Helper.QUERY_BELIEVE_DEFLINE,
                NCBI_Q_BLAST_Helper.QUERY_FROM, NCBI_Q_BLAST_Helper.QUERY_TO,
                NCBI_Q_BLAST_Helper.REPEATS, NCBI_Q_BLAST_Helper.RID,
                NCBI_Q_BLAST_Helper.SEARCHSP_EFF,
                NCBI_Q_BLAST_Helper.SHORT_QUERY_ADJUST,
                NCBI_Q_BLAST_Helper.SHOW_CDS_FEATURES,
                NCBI_Q_BLAST_Helper.SHOW_LINKOUT,
                NCBI_Q_BLAST_Helper.SHOW_OVERVIEW,
                NCBI_Q_BLAST_Helper.THRESHOLD, NCBI_Q_BLAST_Helper.WORD_SIZE,
                NCBI_Q_BLAST_Helper.WWW_BLAST_TYPE,

        });// End of implementation
        // Set the PROGRAM to blastp
        this.request_parameters.add(NCBI_Q_BLAST_Parameter
                .PROGRAM(NCBI_Q_BLAST_Parameter.PROGRAM_PARAM.tblastn));
    }

    /**
     * @param {@link NCBI_Q_BLAST_Parameter} parameter that is being attempted
     *               to add
     * @return {@code true} is successfully added, {@link false} elsewise
     * @throws {@link Bad_Q_BLAST_Parameter_Exception} in case a forbidden
     *                parameters is attempted to insert
     */
    @Override
    public boolean addRequestParameter(NCBI_Q_BLAST_Parameter parameter)
            throws Bad_Q_BLAST_Parameter_Exception {
        // Check what parameter goes as an input
        if (parameter.key.equals(NCBI_Q_BLAST_Helper.BLAST_PROGRAM)) {
            // As long as only tblastn is allowed with
            // 'PROGRAM=tblastn'
            // are allowed, check for whether any of these three are being
            // inputed
            if (parameter.getValue().equals(
                    NCBI_Q_BLAST_Parameter.BLAST_PROGRAM_PARAM.tblastn.name())) {
                this.request_parameters.add(parameter);
                return true;
            } else {
                // Elsewise - complain
                throw new Bad_Q_BLAST_Parameter_Exception(parameter);
            }
        }
        // If parameter is not "BLAST_PROGRAM" - call super to handle it
        return super.addRequestParameter(parameter);
    }

    /**
     * A static factory to get a "Default" (XML output, Exception printout)
     * instance of a BLASTP
     *
     * @param {@link List<ProteinFasta>} query - a list of query fasta records,
     *               that shall be used as an input
     * @param {@link List<String>} query_IDs - a list of database ID/ACs to
     *               blast.
     * @return a "Default" instance of a {@link NCBI_Q_BLASTP}.
     */
    public static <T extends NucleotideFasta>NCBI_Q_TBLASTN newDefaultInstance(List<T> query,
                                                    List<String> query_IDs) {

        return new NCBI_Q_TBLASTN<T>(query, query_IDs) {
            /**
             * Combines the formQuery() sendBLASTRequest() extractRID() and
             * retrieveResult() in order to finish the Q_BLAST with ncbi
             */
            @Override
            public void run() {
                try {
                    this.BLAST();
                    this.retrieveResult();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Bad_Q_BLAST_RequestException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                this.BLASTed = true;
                this.notifyListeners();
            }

            /**
             * Retrieves the blast output from the ncbi server and stores it in
             * the blastOutput private field
             *
             * @throws JAXBException
             *             in case a JAXB parser error
             * @throws SAXException
             *             in case an XML error occurs
             * @throws IOException
             *             in case an error in connection occurs
             */
            @Override
            protected void retrieveResult() throws SAXException, JAXBException,
                    IOException {
                String retreiveRequest = NCBI_Q_BLAST.QBLAST_SERVICE_URL
                        + NCBI_Q_BLAST_Parameter.CMD(
                        NCBI_Q_BLAST_Parameter.CMD_PARAM.Get)
                        .toString()
                        + NCBI_Q_BLAST_ParameterSet.ampersand
                        + NCBI_Q_BLAST_Parameter.RID(this.BLAST_RID).toString()
                        + NCBI_Q_BLAST_ParameterSet.ampersand
                        + NCBI_Q_BLAST_Parameter.FORMAT_TYPE(
                        NCBI_Q_BLAST_Parameter.FORMAT_TYPE_PARAM.XML)
                        .toString();
                // On a laggy network might be useful to prevent looping through
                // failed
                // attempts
                // to catch the output
                int retreiveAttempts = 0;
                try {
                    // Generates a status request
                    URL request = new URL(retreiveRequest);
                    // Opens a connection
                    URLConnection connection = request.openConnection();
                    // Gets InputStream and redirects to the helper,
                    // sets blastoutPut field
                    this.blastOutput = NCBI_BLAST_OutputHelper
                            .catchBLASTOutput(connection.getInputStream());
                } catch (IOException ioe) {
                    throw new IOException("A connection error has occurred: "
                            + ioe.getMessage(), ioe);
                } catch (UnmarshalException ue) {
                    // Retry opening connection and retrieving the output
                    retreiveAttempts++;
                    if (retreiveAttempts < 4) {
                        URL request = new URL(retreiveRequest);
                        URLConnection connection = request.openConnection();
                        this.blastOutput = NCBI_BLAST_OutputHelper
                                .catchBLASTOutput(connection.getInputStream());
                    } else {
                        throw new IOException("Failed to retreive the output: "
                                + ue.getMessage(), ue);
                    }
                }
            }
        };
    }
}
