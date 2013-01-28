/**
 * 
 */
package BLAST.NCBI.local.exec;

import java.io.File;
import java.util.List;

import util.SystemUtil;

import format.fasta.Fasta;

import BLAST.NCBI.NCBI_BLAST;

//TODO: document
/**
 * @author axrt A generalized abstraction of an NCBI BLAST process that is
 *         performed locally through an NCBI executable from
 *         ftp://ftp.ncbi.nlm.nih.gov/blast/executables/blast+/LATEST/
 */
public abstract class NCBI_EX_BLAST extends NCBI_BLAST {

	protected File inputFile;
	protected File tempDir;
	protected File outputFile;
	protected File executive;
	protected NCBI_EX_BLAST_FileOperator fileOperator;
	protected String[] parameterList;

	/**
	 * @param query
	 * @param query_IDs
	 */
	protected NCBI_EX_BLAST(List<Fasta> query, List<String> query_IDs,
			File tempDir, File executive,String[] parameterList) {
		super(query, query_IDs);
		this.fileOperator = new NCBI_EX_BLAST_FileOperator();
		this.tempDir = tempDir;
		this.executive=executive;
		this.parameterList=parameterList;
		this.inputFile = new File(this.tempDir.getPath() + SystemUtil.SysFS
				+ "in_" + String.valueOf(this.hashCode()));
		this.outputFile = new File(this.tempDir.getPath() + SystemUtil.SysFS
				+ "out_" + String.valueOf(this.hashCode()));
	}

	/**
	 * @param query
	 */
	protected NCBI_EX_BLAST(List<Fasta> query, File tempDir,File executive,String[] parameterList) {
		super(query);
		this.fileOperator = new NCBI_EX_BLAST_FileOperator();
		this.tempDir = tempDir;
		this.executive=executive;
		this.parameterList=parameterList;
		this.inputFile = new File(this.tempDir.getPath() + SystemUtil.SysFS
				+ String.valueOf(this.hashCode()));
		this.outputFile = new File(this.tempDir.getPath() + SystemUtil.SysFS
				+ "out_" + String.valueOf(this.hashCode()));
	}

	public abstract void BLAST() throws Exception;
    
}
