package align.local;

import align.Aligner;

import java.io.File;
import java.util.List;

/**
 *TODO Document
 */
public abstract class ExecutableAligner<T> extends Aligner<T>{

    protected final File tmpDir;
    protected final File executable;
    protected final String[] parameterList;

    protected ExecutableAligner(List<T> sequences,File tmpDir,File executable,String[] parameterList) {
        super(sequences);
        this.tmpDir=tmpDir;
        this.executable=executable;
        this.parameterList=parameterList;
    }

    protected ExecutableAligner(File tmpDir,File executable,String[] parameterList) {
        super();
        this.tmpDir=tmpDir;
        this.executable=executable;
        this.parameterList=parameterList;
    }
}
