/**
 *
 */
package blast.ncbi.remote;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.xml.sax.SAXException;

import blast.ncbi.output.NCBI_BLAST_OutputHelper;

import format.fasta.protein.ProteinFasta;

/**
 * An abstraction of a BLATP from ncbi, which defines which parameters may be
 * used on the URlAPI. Implements a static factory for default instances, which
 * retreive results as an XML and simply print out any exceptions that may be
 * thrown in {@code run()}. However, an extending custom implementation may
 * choose a different format for saving the output as well as may use an
 * alternative way of handling exceptions.
 *
 * @author axrt
 */
public abstract class NCBI_Q_BLASTP extends NCBI_Q_BLAST {

    /**
     * @param {@link List<Fasta>} query - a list of query fasta records, that
     *               shall be used as an input
     * @param {@link List<String>} query_IDs - a list of database ID/ACs to
     *               blast. Both parameters may be used simultaneously, making it
     *               easier to mix fasta records with sequences, that are already in
     *               the database
     */
    protected NCBI_Q_BLASTP(List<? extends ProteinFasta> query,
                            List<String> query_IDs) {
        super(query, query_IDs);
        // Declaring a list of allowed parameters
        this.request_parameters = new NCBI_Q_BLAST_ParameterSet(new String[]{
                NCBI_Q_BLAST_Helper.QUERY, NCBI_Q_BLAST_Helper.QUERY_FROM,
                NCBI_Q_BLAST_Helper.QUERY_TO, NCBI_Q_BLAST_Helper.DATABASE,
                NCBI_Q_BLAST_Helper.DATABASE_SORT,
                NCBI_Q_BLAST_Helper.DATABASE_PREFIX,
                NCBI_Q_BLAST_Helper.BLAST_PROGRAM,
                NCBI_Q_BLAST_Helper.ALIGNMENTS, NCBI_Q_BLAST_Helper.CDD_SEARCH,
                NCBI_Q_BLAST_Helper.CMD,
                NCBI_Q_BLAST_Helper.COMPOSITION_BASED_STATISTICS,
                NCBI_Q_BLAST_Helper.DISPLAY_SORT,
                NCBI_Q_BLAST_Helper.ENTREZ_QUERY, NCBI_Q_BLAST_Helper.EXPECT,
                NCBI_Q_BLAST_Helper.EXPECT_LOW,
                NCBI_Q_BLAST_Helper.EXPECT_HIGH, NCBI_Q_BLAST_Helper.FILTER,
                NCBI_Q_BLAST_Helper.FORMAT_TYPE, NCBI_Q_BLAST_Helper.GAPCOSTS,
                NCBI_Q_BLAST_Helper.HITLIST_SIZE, NCBI_Q_BLAST_Helper.HSP_SORT,
                NCBI_Q_BLAST_Helper.I_THRESHOLD,
                NCBI_Q_BLAST_Helper.LCASE_MASK, NCBI_Q_BLAST_Helper.MASK_CHAR,
                NCBI_Q_BLAST_Helper.MATRIX_NAME,
                NCBI_Q_BLAST_Helper.MAX_NUM_SEQ, NCBI_Q_BLAST_Helper.NCBI_GI,
                NCBI_Q_BLAST_Helper.OTHER_ADVACED,
                NCBI_Q_BLAST_Helper.PHI_PATTERN, NCBI_Q_BLAST_Helper.PROGRAM,
                NCBI_Q_BLAST_Helper.PSSM,
                NCBI_Q_BLAST_Helper.QUERY_BELIEVE_DEFLINE,
                NCBI_Q_BLAST_Helper.REPEATS, NCBI_Q_BLAST_Helper.RID,
                NCBI_Q_BLAST_Helper.SEARCHSP_EFF,
                NCBI_Q_BLAST_Helper.SHORT_QUERY_ADJUST,
                NCBI_Q_BLAST_Helper.THRESHOLD});// End of implementation
        // Set the PROGRAM to blastp
        this.request_parameters.add(NCBI_Q_BLAST_Parameter
                .PROGRAM(NCBI_Q_BLAST_Parameter.PROGRAM_PARAM.blastp));
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
            // As long as only blastp, psiBlast, and phiBlast with
            // 'PROGRAM=blastp'
            // are allowed, check for whether any of these three are being
            // inputed
            if (parameter.getValue().equals(
                    NCBI_Q_BLAST_Parameter.BLAST_PROGRAM_PARAM.psiBlast.name())
                    || parameter.getValue().equals(
                    NCBI_Q_BLAST_Parameter.BLAST_PROGRAM_PARAM.phiBlast
                            .name())
                    || parameter.getValue().equals(
                    NCBI_Q_BLAST_Parameter.BLAST_PROGRAM_PARAM.blastp
                            .name())) {
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
    public static NCBI_Q_BLASTP newDefaultInstance(List<ProteinFasta> query,
                                                   List<String> query_IDs) {
        if (query_IDs == null) {
            query_IDs = new ArrayList<String>();
        }
        if (query == null) {
            query = new ArrayList<ProteinFasta>();
        }
        // TODO: input a check for whether both lists are empty or declared null

        return new NCBI_Q_BLASTP(query, query_IDs) {
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
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (Bad_Q_BLAST_RequestException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SAXException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (JAXBException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
