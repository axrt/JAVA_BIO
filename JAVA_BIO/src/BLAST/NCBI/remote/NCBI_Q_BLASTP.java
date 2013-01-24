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

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.xml.sax.SAXException;

import format.fasta.Fasta;

/**
 * @author axrt
 * 
 */
public class NCBI_Q_BLASTP extends NCBI_Q_BLAST {

	/**
	 * @param query
	 * @param query_IDs
	 */
	public NCBI_Q_BLASTP(ArrayList<Fasta> query, ArrayList<String> query_IDs) {
		super(query, query_IDs);
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
					NCBI_Q_BLAST_Helper.THRESHOLD, };

			@Override
			protected boolean addAllowedParameters() {
				return this.allowedParameters.addAll(Arrays
						.asList(this.allowedParametersList));
			}

		};
		this.request_parameters.addAllowedParameters();
		// Set the PROGRAM to blastp
		this.request_parameters.add(NCBI_Q_BLAST_Parameter
				.PROGRAM(NCBI_Q_BLAST_Parameter.PROGRAM_PARAM.blastp));
	}

	public boolean addRequestParameter(NCBI_Q_BLAST_Parameter parameter) {
		if (parameter.getKey().equals(NCBI_Q_BLAST_Helper.PROGRAM)) {
			return false;
		} else {
			return this.request_parameters.add(parameter);
		}
	}

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
			// TODO: delete the outprint
			System.out.println(this.BLAST_RID);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the BLAST output from the NCBI server and stores it in the
	 * blastOutput private field
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
		String retreiveRequest = NCBI_Q_BLAST_Parameter.CMD(
				NCBI_Q_BLAST_Parameter.CMD_PARAM.Get).toString()
				+ NCBI_Q_BLAST_ParameterSet.ampersand
				+ NCBI_Q_BLAST_Parameter.RID(this.BLAST_RID).toString()
				+ NCBI_Q_BLAST_ParameterSet.ampersand
				+ NCBI_Q_BLAST_Parameter.FORMAT_TYPE(
						NCBI_Q_BLAST_Parameter.FORMAT_TYPE_PARAM.XML)
						.toString();
		// On a laggy network might be useful to prevent looping through failed
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
			this.blastOutput = NCBI_Q_BLAST_Helper.catchBLASTOutput(connection
					.getInputStream());
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

}
