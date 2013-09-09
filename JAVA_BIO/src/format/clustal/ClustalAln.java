package format.clustal;

import java.util.List;
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
 * An abstract alignment, done by the clustal algorithm implementing program
 */
public abstract class ClustalAln extends Aln{

    /**
     * File header, may be null if the extending type of alignment does not provide one
     */
    final String header;
    /**
     * A list of lines in the alignment
     */
    final List<AlignmentLine> alignmentLines;
    /**
     * Consensus line; supposed to be created by the abstract String getConsensus(int cutoff) method
     */
    String consensus;

    /**
     * Constructor from field parameters
     * @param header {@link String}; may be null if the extending type of alignment does not provide one
     * @param alignmentLines {@link List} of {@link AlignmentLine}, that is the core of the alignment
     */
    protected ClustalAln(String header, List<AlignmentLine> alignmentLines) {
        this.header = header;
        this.alignmentLines = alignmentLines;
    }

    /**
     * Should return a {@link String} representation of the consensus sequence at a given cutoff
     * @param cutoff {@code double}, a cutoff at which a most frequent
     *                             letter in a column gets accepted as a consensus letter
     * @return {@link String} that represent a consensus of the alignment at a given cutoff
     */
    public abstract String getConsensus(double cutoff);

    /**
     * A static class that represents a names and numbered line within the alignment
     */
    public static class AlignmentLine {

        /**
         * Position within the alignment
         */
        final int position;
        /**
         * The name of the sequence
         */
        final String name;
        /**
         * The actual sequence
         */
        final String sequence;

        /**
         * Constructor form field parameters
         * @param position {@code int} position within the alignment
         * @param name {@link String} name of the sequence (most likely to be derived from the fasta AC)
         * @param sequence {@link String} sequence
         */
        public AlignmentLine(int position, String name, String sequence) {
            this.position = position;
            this.name = name;
            this.sequence = sequence;
        }

        /**
         * A getter for the position
         * @return {@code int} position in the alignment
         */
        public int getPosition() {
            return position;
        }

        /**
         * A getter for the name
         * @return {@link String} name
         */
        public String getName() {
            return name;
        }

        /**
         * A getter for the sequence
         * @return {@link String} sequnce
         */
        public String getSequence() {
            return sequence;
        }
    }
}
