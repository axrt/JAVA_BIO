/**
 * 
 */
package BLAST.NCBI.remote;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.xml.sax.SAXException;

import format.fasta.Fasta;

//TODO: document this class
/**
 * An abstraction of a BLATP from NCBI, which defines which parameters may be
 * used on the URlAPI. Implements a static factory for default instances, which
 * retreive results as an XML and simply print out any exceptions that may be
 * thrown in {@code run()}. However, an extending custom implementation may
 * choose a different format for saving the output as well as may use an
 * alternative way of handling exceptions.
 * 
 * @author axrt
 * 
 */
public abstract class NCBI_Q_BLASTP extends NCBI_Q_BLAST {

	/**
	 * @param {@link List<Fasta>} query - a list of query fasta records, that
	 *        shall be used as an input
	 * @param {@link List<String>} query_IDs - a list of database ID/ACs to
	 *        blast. Both parameters may be used simultaneously, making it
	 *        easier to mix fasta records with sequences, that are already in
	 *        the database
	 */
	protected NCBI_Q_BLASTP(List<Fasta> query, List<String> query_IDs) {
		super(query, query_IDs);
		// Declaring a list of allowed parameters
		this.request_parameters = new NCBI_Q_BLAST_ParameterSet() {
			private String[] allowedParametersList = {
					NCBI_Q_BLAST_Helper.QUERY, NCBI_Q_BLAST_Helper.QUERY_FROM,
					NCBI_Q_BLAST_Helper.QUERY_TO, NCBI_Q_BLAST_Helper.DATABASE,
					NCBI_Q_BLAST_Helper.DATABASE_SORT,
					NCBI_Q_BLAST_Helper.DATABASE_PREFIX,
					NCBI_Q_BLAST_Helper.BLAST_PROGRAM,
					NCBI_Q_BLAST_Helper.ALIGNMENTS,
					NCBI_Q_BLAST_Helper.CDD_SEARCH, NCBI_Q_BLAST_Helper.CMD,
					NCBI_Q_BLAST_Helper.COMPOSITION_BASED_STATISTICS,
					NCBI_Q_BLAST_Helper.COMPOSITION_BASED_STATISTICS,
					NCBI_Q_BLAST_Helper.DISPLAY_SORT,
					NCBI_Q_BLAST_Helper.ENTREZ_QUERY,
					NCBI_Q_BLAST_Helper.EXPECT, NCBI_Q_BLAST_Helper.EXPECT_LOW,
					NCBI_Q_BLAST_Helper.EXPECT_HIGH,
					NCBI_Q_BLAST_Helper.FILTER,
					NCBI_Q_BLAST_Helper.FORMAT_TYPE,
					NCBI_Q_BLAST_Helper.GAPCOSTS,
					NCBI_Q_BLAST_Helper.HITLIST_SIZE,
					NCBI_Q_BLAST_Helper.HSP_SORT,
					NCBI_Q_BLAST_Helper.I_THRESHOLD,
					NCBI_Q_BLAST_Helper.LCASE_MASK,
					NCBI_Q_BLAST_Helper.MASK_CHAR,
					NCBI_Q_BLAST_Helper.MATRIX_NAME,
					NCBI_Q_BLAST_Helper.MAX_NUM_SEQ,
					NCBI_Q_BLAST_Helper.NCBI_GI,
					NCBI_Q_BLAST_Helper.OTHER_ADVACED,
					NCBI_Q_BLAST_Helper.PHI_PATTERN,
					NCBI_Q_BLAST_Helper.PROGRAM, NCBI_Q_BLAST_Helper.PSSM,
					NCBI_Q_BLAST_Helper.QUERY_BELIEVE_DEFLINE,
					NCBI_Q_BLAST_Helper.REPEATS, NCBI_Q_BLAST_Helper.RID,
					NCBI_Q_BLAST_Helper.SEARCHSP_EFF,
					NCBI_Q_BLAST_Helper.SHORT_QUERY_ADJUST,
					NCBI_Q_BLAST_Helper.THRESHOLD };

			/**
			 * Pushes the allowed parameters from the list into a set for a fast
			 * lookup
			 */
			@Override
			protected boolean addAllowedParameters() {
				return this.allowedParameters.addAll(Arrays
						.asList(this.allowedParametersList));
			}

		};// End of implementation

		// Actually adding all of the allowed parameters on the list
		this.request_parameters.addAllowedParameters();
		// Set the PROGRAM to blastp
		this.request_parameters.add(NCBI_Q_BLAST_Parameter
				.PROGRAM(NCBI_Q_BLAST_Parameter.PROGRAM_PARAM.blastp));
	}

	/**
	 * @param {@link NCBI_Q_BLAST_Parameter} parameter that is being attempted
	 *        to add
	 * @return {@code true} is successfully added, {@link false} elsewise
	 * @throws {@link Bad_Q_BLAST_Parameter_Exception} in case a forbidden
	 *         parameters is attempted to insert
	 */
	@Override
	public boolean addRequestParameter(NCBI_Q_BLAST_Parameter parameter)
			throws Bad_Q_BLAST_Parameter_Exception {
		// In this abstraction is's all about BLASTP, so it has been set in the
		// constructor already and any change of the
		// "PROGRAM" is forbidden, though, the parameter itself is ok.
		if (parameter.getKey().equals(NCBI_Q_BLAST_Helper.PROGRAM)) {
			return false;
		} else {
			// Returns true if successfully added
			if (!this.request_parameters.add(parameter)) {
				// But throws Bad_Q_BLAST_Parameter_Exception in case a
				// forbidden parameter is attempted to add
				throw new Bad_Q_BLAST_Parameter_Exception(parameter);
			} else {
				return true;
			}
		}
	}

	/**
	 * A static factory to get a "Default" (XML output, Exception printout)
	 * instance of a BLASTP
	 * 
	 * @param {@link List<Fasta>} query - a list of query fasta records, that
	 *        shall be used as an input
	 * @param {@link List<String>} query_IDs - a list of database ID/ACs to
	 *        blast.
	 * @return a "Default" instance of a {@link NCBI_Q_BLASTP}.
	 */
	public static NCBI_Q_BLASTP newDefaultInstance(List<Fasta> query,
			List<String> query_IDs) {
		return new NCBI_Q_BLASTP(query, query_IDs) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				try {
					this.formQuery();
					this.sendBLASTRequest();
					this.extractRID();
					while (!this.resultsReady()) {
						Thread.sleep(1000);
					}
					this.retrieveResult();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			/**
			 * Retrieves the BLAST output from the NCBI server and stores it in
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
					this.blastOutput = NCBI_Q_BLAST_Helper
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
						this.blastOutput = NCBI_Q_BLAST_Helper
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
