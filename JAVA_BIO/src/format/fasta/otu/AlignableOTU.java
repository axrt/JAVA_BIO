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
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
