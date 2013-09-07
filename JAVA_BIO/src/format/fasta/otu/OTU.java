package format.fasta.otu;

import format.fasta.nucleotide.NucleotideFasta;

import java.util.ArrayList;
import java.util.Collection;

/**
 *TODO Document
 */
public abstract class OTU <N extends NucleotideFasta> extends ArrayList<N>{

    protected final String name;

    protected OTU(int initialCapacity, String name) {
        super(initialCapacity);
        this.name = name;
    }

    protected OTU(String name) {
        this.name = name;
    }

}
