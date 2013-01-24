/**
 * 
 */
package BLAST.NCBI.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import format.fasta.Fasta;
import BLAST.NCBI.NCBI_BLAST;

/**
 * @author axrt <br>
 * <br>
 *         A generalized abstraction of a QBLAST service at
 *         https://www.ncbi.nlm.nih.gov/Web/Newsltr/Summer99/qblast.html, which
 *         provides a simple query line web interface for BLAST task execution
 *         at the NCBI server via the latest databases
 */
public abstract class NCBI_Q_BLAST extends NCBI_BLAST {

	/**
	 * The QBLAST service address
	 */
	protected static final String QBLAST_SERVICE_URL = "http://blast.ncbi.nlm.nih.gov/Blast.cgi?";
	/**
	 * RID, assigned by the NCBI server
	 */
	protected String BLAST_RID;
	/**
	 * A flag that indicates whether this instance has already been BLASTed or
	 * not
	 */
	protected boolean BLASTed;
	/**
	 * A Fasta record, that contains the query
	 */
	protected final ArrayList<Fasta> query;
	/**
	 * A list of genbak/EMBL ids that shall be used for a BLAST search
	 */
	protected final ArrayList<String> query_IDs;
	/**
	 * A field that stores an HTML of the request acceptance
	 */
	protected String requestAcceptedPage;
	/**
	 * A set of parameters to generate a request with
	 */
	protected NCBI_Q_BLAST_ParameterSet request_parameters;

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query.toString();
	}

	/**
	 * @return the BLASTed
	 */
	protected boolean isBLASTed() {
		return BLASTed;
	}

	/**
	 * @return {@link String} the BLAST_RID
	 */
	protected String getBLAST_RID() {
		return BLAST_RID;
	}

	/**
	 * @param {@link Fasta} - a query record
	 */
	protected NCBI_Q_BLAST(ArrayList<Fasta> query, ArrayList<String> query_IDs) {
		super();
		this.query = query;
		this.query_IDs = query_IDs;
		this.BLASTed = false;
	}

	/**
	 * Adds a parameter to the {@link NCBI_Q_BLAST}, that is being prepared to
	 * launch
	 * 
	 * @param {@link NCBI_Q_BLAST_Parameter}parameter
	 * @return {@code true} if success, else {@code false}
	 */
	public boolean addRequestParameter(NCBI_Q_BLAST_Parameter parameter) {
		return this.request_parameters.add(parameter);
	}

	/**
	 * Forms a QUERY, escapes all the special symbols within it and adds it to
	 * the request parameters
	 */
	protected void formQuery() {
		// For each Fasta in the query list - add it to a single string
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.query.size(); i++) {
			sb.append(this.query.get(i).toString());
			sb.append('\n');
		}
		// Now for every ID/AC - add to the same query string
		for (int i = 0; i < this.query_IDs.size(); i++) {
			sb.append(this.query_IDs.get(i));
			sb.append('\n');
		}
		// Flush the newly formed query into parameters
		this.request_parameters.add(NCBI_Q_BLAST_Parameter
				.QUERY(NCBI_Q_BLAST_EscapeSymbols
						.reformatForRequest(new String(sb))));
	}

	/**
	 * Generates and sends a BLAST request to the server and collects the
	 * response page.
	 * 
	 * @throws IOException
	 *             in case a connection error occurs
	 */
	protected void sendBLASTRequest() throws IOException {
		BufferedReader br = null;
		// Generates a request
		URL request = new URL(NCBI_Q_BLAST.QBLAST_SERVICE_URL
				+ this.request_parameters.toString());
		// Opens a connection to send the request to the server
		URLConnection connection = request.openConnection();
		// Gets the text output, which is actually an HTML page
		br = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		// Creates a StringBuilder in order to collect the output HTML
		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
			sb.append(String.valueOf('\n'));
		}
		// Sets the requestAcceptedPage field
		this.requestAcceptedPage = new String(sb);
		// Finally closes the BufferedReader
		br.close();
	}

	/**
	 * Extracts RID from the output. Helps determine whether the submission has
	 * been successful, otherwise throws BadQBLASTRequestException, which
	 * contains the error/reason in its message.
	 * 
	 * @throws BadQBLASTRequestException
	 */
	protected void extractRID() throws Bad_Q_BLAST_RequestException {
		// Checking whether the request has been accepted correctly
		// In case the output contains the keywords for the QBLAST comment tag,
		// it either has error message, or everything went well and the RID can
		// be extracted
		String[] requestAcceptedPageLines = this.requestAcceptedPage
				.split(String.valueOf('\n'));
		// If an error message occurs, it is always contained by
		// the <p class="error">Message ID#....</p></li></ul> tag
		String messageID = "Message ID#";
		String messageID_tail = "</p></li></ul>";
		String class_error = "class=\"error\"";
		String RID_equals = "RID = ";
		// Browsing through the output html line by line
		for (String line : requestAcceptedPageLines) {
			// If no error has occurred
			if (!line.contains(class_error)) {
				// Normally the output contains a line with the RID
				if (line.contains(RID_equals)) {
					String[] RIDSetter = line.split(" = ");
					// Not sure if the trim is really needed here
					this.BLAST_RID = RIDSetter[1].trim();
					// If no such line (containing RID exists),
					// the page may have given some unexpected error
				}
				// Otherwise print out the message within the exception message
			} else if (line.contains(messageID)) {
				// Extracts the message
				String message = line.substring(line.indexOf(messageID)
						+ messageID.length(), line.indexOf(messageID_tail));
				throw new Bad_Q_BLAST_RequestException(
						"Request has failed due to: " + message);
			} else {
				throw new Bad_Q_BLAST_RequestException(
						"Request failed with no specific error, please check parameters.");
			}
		}
		// Checking for whether the RID has been extracted normally
		if (this.BLAST_RID == null) {
			throw new Bad_Q_BLAST_RequestException(
					"Request failed with no specific error. "
							+ "No RID has been returned, please check parameters.");
		}
	}

	/**
	 * Checks whether the NCBI server is ready to respond with BLAST results.
	 * 
	 * @return {@code true} if results are ready, {@code false} if the server is
	 *         still busy with the task
	 * @throws IOException
	 *             in case a url connection fails
	 * @throws Bad_Q_BLAST_RequestException
	 *             the NCBI server does not have a result for the requested RID
	 */
	protected boolean resultsReady() throws IOException,
			Bad_Q_BLAST_RequestException {
		BufferedReader br = null;
		// Generates a status request
		String statusRequest = NCBI_Q_BLAST_Parameter.CMD(
				NCBI_Q_BLAST_Parameter.CMD_PARAM.Get).toString()
				+ '&' + NCBI_Q_BLAST_Helper.RID + '=' + this.BLAST_RID;
		URL request = new URL(NCBI_Q_BLAST.QBLAST_SERVICE_URL + statusRequest);
		// Opens a connection
		URLConnection connection = request.openConnection();
		// Gets the text output
		br = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String inputLine;
		// Reading the output line by line
		while ((inputLine = br.readLine()) != null) {
			// If the status message is "READY"-returns true
			if (inputLine.contains("READY")) {
				return true;
				// If the status message is "WAITING" - returns false
			} else if (inputLine.contains("WAITING")) {
				return false;
				// If the status message is "UNKNOWN" - throws
				// BadQBLASTRequestException
				// but this should not happen naturally
			} else if (inputLine.contains("UNKNOWN")) {
				throw new Bad_Q_BLAST_RequestException("No record for RID "
						+ this.BLAST_RID + " exists on the server..");
			}
		}
		br.close();
		return false;
	}

}
