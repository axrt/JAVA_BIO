package format.fasta.otu;

import format.fasta.nucleotide.NucleotideFasta;

import java.util.ArrayList;
import java.util.Collection;

/**
 *TODO Document
 */
public class OTU <N extends NucleotideFasta> extends ArrayList<N>{

    protected final String name;

    public OTU(int initialCapacity, String name) {
        super(initialCapacity);
        this.name = name;
    }

    public OTU(String name) {
        this.name = name;
    }

    public OTU(Collection<N> c, String name) {
        super(c);
        this.name = name;
    }
}
