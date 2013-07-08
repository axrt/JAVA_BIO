package align.local.clustal;

import align.local.ExecutableAligner;
import format.clustal.ClustalAln;
import format.fasta.Fasta;
import util.ExecutableUtilFileOperator;

import java.io.File;
import java.util.List;

/**
 *TODO Document
 */
public abstract class ClustalAligner<F extends Fasta> extends ExecutableAligner<F> {

    protected ClustalAln output;
    protected final ExecutableUtilFileOperator fileOperator;
    protected final File inputFile;
    protected final File outputFile;
    protected ClustalAligner(List<F> sequences, File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator) {
        super(sequences, tmpDir, executable, parameterList);
        this.fileOperator=fileOperator;
        this.inputFile=new File(this.tmpDir,"in_"+this.hashCode());
        this.outputFile=new File(this.tmpDir,"out_"+this.hashCode());
    }

    protected abstract void checkRecordsRedundancy() throws Exception;
}
