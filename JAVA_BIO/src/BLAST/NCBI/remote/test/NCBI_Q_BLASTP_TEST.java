/**
 * 
 */
package BLAST.NCBI.remote.test;

import java.util.ArrayList;
import java.util.List;

import format.fasta.protein.ProteinFasta;
import BLAST.NCBI.remote.NCBI_Q_BLAST_Parameter;
import BLAST.NCBI.remote.NCBI_Q_TBLASTN;

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

			List<ProteinFasta> query = new ArrayList<ProteinFasta>();
			query.add(proteinFasta);
			List<String> query_IDs = new ArrayList<String>();

//			NCBI_Q_BLASTP blastp = NCBI_Q_BLASTP.newDefaultInstance(query,
//			query_IDs);
//
//			blastp.addRequestParameter(NCBI_Q_BLAST_Parameter
//			.DATABASE(NCBI_Q_BLAST_Parameter.DATABASE_PARAM.nr));
//			blastp.addRequestParameter(NCBI_Q_BLAST_Parameter.ALIGNMENTS(100));
//		   Thread thread = new Thread(blastp);
			NCBI_Q_TBLASTN tblastn = NCBI_Q_TBLASTN.newDefaultInstance(query,
					query_IDs);
			tblastn.addRequestParameter(NCBI_Q_BLAST_Parameter
					.DATABASE(NCBI_Q_BLAST_Parameter.DATABASE_PARAM.nr));
			tblastn.addRequestParameter(NCBI_Q_BLAST_Parameter.ALIGNMENTS(100));
			Thread thread = new Thread(tblastn);
           thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
