package format.fasta.otu;

import align.local.clustal.ClustalW2Aligner;
import format.clustal.AlignmentException;
import format.fasta.nucleotide.NucleotideFasta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO Document
 */
public class AlignableOTU<N extends NucleotideFasta> extends OTU<N> {

    protected final ClustalW2Aligner<N> aligner;
    protected String otuConsensus;

    protected AlignableOTU(String name, ClustalW2Aligner<N> aligner) {
        super(name);
        this.aligner = aligner;
    }

    protected AlignableOTU(int initialCapacity, String name, ClustalW2Aligner<N> aligner) {
        super(initialCapacity, name);
        this.aligner = aligner;
    }

    public String getOtuConsensus(double cutoff) throws InterruptedException, AlignmentException, IOException {
        if (this.size()==1){
            return this.get(0).getSequence();
        }
        if (this.otuConsensus == null) {
            List<N> redundancyReducedSequences= AlignableOTU.reduceRedundantSequences(this);
            if(redundancyReducedSequences.size()==1){
                this.otuConsensus=redundancyReducedSequences.get(0).getSequence();
            }else{
                this.aligner.setSequences(redundancyReducedSequences);
                this.aligner.align();
                this.otuConsensus=this.aligner.getOutput().getConsensus(cutoff);
            }
        }
        return otuConsensus;
    }

    public static <N extends NucleotideFasta> List<N> reduceRedundantSequences(List<N> sequences) {

        Set<String> nonredundantSequencesSet = new HashSet<>(sequences.size());
        List<N> nonredundantSequencesList = new ArrayList<>(sequences.size());
        for (N n : sequences) {
            if (nonredundantSequencesSet.add(n.getSequence())) {
                nonredundantSequencesList.add(n);
            }
        }

        return nonredundantSequencesList;
    }
}
