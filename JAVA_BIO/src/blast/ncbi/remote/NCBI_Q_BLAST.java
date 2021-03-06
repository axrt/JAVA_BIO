/**
 * 
 */
package blast.ncbi.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import blast.ncbi.NCBI_BLAST;
import format.fasta.Fasta;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * A generalized abstraction of a QBLAST service at
 * https://www.ncbi.nlm.nih.gov/Web/Newsltr/Summer99/qblast.html, which provides
 * a simple query line web interface for blast task execution at the ncbi server
 * via the latest databases
 * 
 * @author axrt
 * 
 */
public abstract class NCBI_Q_BLAST<T extends Fasta> extends NCBI_BLAST<T> {

	/**
	 * The QBLAST service address
	 */
	protected static final String QBLAST_SERVICE_URL = "http://blast.ncbi.nlm.nih.gov/Blast.cgi?";
	/**
	 * RID, assigned by the ncbi server
	 */
	protected String BLAST_RID;
	/**
	 * A field that stores an HTML of the request acceptance
	 */
	protected String requestAcceptedPage;
	/**
	 * A set of parameters to generate a request with
	 */
	protected NCBI_Q_BLAST_ParameterSet request_parameters;

	/**
	 * A list of allowed parameter names
	 */
	protected String[] allowedParametersList;

	/**
	 * @return {@link String} the BLAST_RID
	 */
	protected String getBLAST_RID() {
		return BLAST_RID;
	}

	/**
	 * Constructor
	 * 
	 * @param query {@link List query} - a list of query fasta records
	 * @param query_IDs {@link List} query_IDs - - a list of query fasta record
	 *        IDs
	 */
	protected NCBI_Q_BLAST(List<T> query, List<String> query_IDs) {
		super(query, query_IDs);
	}

	/**
	 * Adds another {@link NCBI_Q_BLAST_Parameter} to a ncbi QBLAST parameter
	 * list
	 * 
	 * @param parameter {@link NCBI_Q_BLAST_Parameter} parameter that is being attempted
	 *        to add
	 * @return {@code true} is successfully added, {@code false} elsewise
	 * @throws Bad_Q_BLAST_Parameter_Exception in case a forbidden
	 *         parameters is attempted to insert
	 */
	public boolean addRequestParameter(final NCBI_Q_BLAST_Parameter parameter)
			throws Bad_Q_BLAST_Parameter_Exception {
		// In this abstraction is's all about a certain implementation, so it
		// has been set in the
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
	 * Forms a QUERY, escapes all the special symbols within it and adds it to
	 * the request parameters
	 */
	protected void formQuery() {
		// For each Fasta in the query list - add it to a single string
        final StringBuilder sb = new StringBuilder();
        for (Object aQuery : this.query) {
            sb.append(aQuery.toString());
            sb.append('\n');
        }
		// Now for every ID/AC - add to the same query string
        for (Object query_ID : this.query_IDs) {
            sb.append(query_ID);
            sb.append('\n');
        }
		// Flush the newly formed query into parameters
		this.request_parameters.add(NCBI_Q_BLAST_Parameter
				.QUERY(NCBI_Q_BLAST_EscapeSymbols
						.reformatForRequest(new String(sb))));
	}

	/**
	 * Generates and sends a blast request to the server and collects the
	 * response page.
	 * 
	 * @throws IOException
	 *             in case a connection error occurs
	 */
	protected void sendBLASTRequest() throws IOException {
		// Make the CMD=Put
		this.request_parameters.add(NCBI_Q_BLAST_Parameter
				.CMD(NCBI_Q_BLAST_Parameter.CMD_PARAM.Put));
		BufferedReader br;
		// Generates a request
        final URL request = new URL(NCBI_Q_BLAST.QBLAST_SERVICE_URL
				+ this.request_parameters.toString());
		// Opens a connection to send the request to the server
        final URLConnection connection = request.openConnection();
		// Gets the text output, which is actually an HTML page
		br = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		// Creates a StringBuilder in order to collect the output HTML
        final StringBuilder sb = new StringBuilder();
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
	 * @throws Bad_Q_BLAST_RequestException
	 */
	protected void extractRID() throws Bad_Q_BLAST_RequestException {
		// Checking whether the request has been accepted correctly
		// In case the output contains the keywords for the QBLAST comment tag,
		// it either has error message, or everything went well and the RID can
		// be extracted
        final String[] requestAcceptedPageLines = this.requestAcceptedPage
				.split(String.valueOf('\n'));
		// If an error message occurs, it is always contained by
		// the <p class="error">Message ID#....</p></li></ul> tag
		final String messageID = "Message ID#";
        final String messageID_tail = "</p></li></ul>";
        final String class_error = "class=\"error\"";
        final String RID_equals = "RID = ";
		// Browsing through the output html line by line
		for (String line : requestAcceptedPageLines) {
			// If no error has occurred
			if (!line.contains(class_error)) {
				// Normally the output contains a line with the RID
				if (line.contains(RID_equals)) {
                    final String[] RIDSetter = line.split(" = ");
					// Not sure if the trim is really needed here
					this.BLAST_RID = RIDSetter[1].trim();
					// If no such line (containing RID exists),
					// the page may have given some unexpected error
				}
				// case of an error
				// Otherwise print out the message within the exception message
			} else if (line.contains(messageID)) {
				// Extracts the message
				String message = line.substring(line.indexOf(messageID)
						+ messageID.length(), line.indexOf(messageID_tail));
				throw new Bad_Q_BLAST_RequestException(
						"Request has failed due to: " + message);
			} else {
				throw new Bad_Q_BLAST_RequestException(
						"Request failed with no specific error, please check parameters (some may be lacking).");
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
	 * Checks whether the ncbi server is ready to respond with blast results.
	 * 
	 * @return {@code true} if results are ready, {@code false} if the server is
	 *         still busy with the task
	 * @throws IOException
	 *             in case a url connection fails
	 * @throws Bad_Q_BLAST_RequestException
	 *             the ncbi server does not have a result for the requested RID
	 */
	protected boolean resultsReady() throws IOException,
			Bad_Q_BLAST_RequestException {
		BufferedReader br;
		// Generates a status request
		final String statusRequest = NCBI_Q_BLAST_Parameter.CMD(
				NCBI_Q_BLAST_Parameter.CMD_PARAM.Get).toString()
				+ NCBI_Q_BLAST_ParameterSet.ampersand
				+ NCBI_Q_BLAST_Parameter.RID(this.BLAST_RID).toString();
		final URL request = new URL(NCBI_Q_BLAST.QBLAST_SERVICE_URL + statusRequest);
		// Opens a connection
        final URLConnection connection = request.openConnection();
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

    @Override
    protected void BLAST() throws IOException,Bad_Q_BLAST_RequestException,InterruptedException {
        this.formQuery();

            this.sendBLASTRequest();
            this.extractRID();
            // System.out.println("RID: "+this.getBLAST_RID());
            while (!this.resultsReady()) {
                // System.out.println("Waiting..");
                Thread.sleep(3000);
            }

    }

	/**
	 * Should determine the way that the output must be retrieved. The default
	 * is always xml, however, html or asn.1 may be an option
	 * 
	 * @throws Exception
	 */
	protected abstract void retrieveResult() throws Exception;
}
