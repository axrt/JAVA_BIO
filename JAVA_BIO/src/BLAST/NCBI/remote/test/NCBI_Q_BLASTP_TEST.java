/**
 * 
 */
package BLAST.NCBI.remote.test;

import java.util.ArrayList;
import java.util.List;

import format.fasta.Fasta;
import format.fasta.ProteinFasta;
import BLAST.NCBI.remote.NCBI_Q_BLASTP;
import BLAST.NCBI.remote.NCBI_Q_BLAST_Parameter;
import BLAST.NCBI.remote.NCBI_Q_BLAST_Parameter.DATABASE_PARAM;

/**
 * @author axrt
 * 
 */
public class NCBI_Q_BLASTP_TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProteinFasta proteinFasta = ProteinFasta.newInstanceFromParts(
					"Test", "SSWWAHVEMGPPDPILGVTEAYKRDTNSKK");
			System.out.println(proteinFasta.toString());

			List<Fasta> query = new ArrayList<Fasta>();
			query.add(proteinFasta);
			List<String> query_IDs = new ArrayList<String>();

			NCBI_Q_BLASTP blastp = NCBI_Q_BLASTP.newDefaultInstance(query,
					query_IDs);
			blastp.addRequestParameter(NCBI_Q_BLAST_Parameter
					.DATABASE(NCBI_Q_BLAST_Parameter.DATABASE_PARAM.nr));
            blastp.addRequestParameter(NCBI_Q_BLAST_Parameter.ALIGNMENTS(100));
			Thread thread = new Thread(blastp);
            thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
