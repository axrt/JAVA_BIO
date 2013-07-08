package align;

import format.clustal.AlignmentException;
import format.fasta.Fasta;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *TODO Document
 */
public abstract class Aligner<T> {

    protected  List<T> sequences;

    protected Aligner() {

    }

    protected Aligner(List<T> sequences) {
        this.sequences = sequences;
    }

    public void setSequences(List<T> sequences) {
        this.sequences = sequences;
    }

    public abstract void align() throws Exception;

}
