package format.clustal;

import java.io.*;
import java.util.*;

/**
 * A representation of an alignment, done by a ClustalW2
 */
public class ClustalW2Aln extends ClustalAln {
    /**
     * A header, can be found within the header of the file and indicated the version of the ClustalW2
     */
    protected static String CLUSTAL = "CLUSTAL";
    /**
     * In case a consensus for a column can not be defined, an N gets returned
     */
    protected static Character BLANK = 'N';
    /**
     * A ClustalW2 file format line length
     */
    protected static int FORMATTER_LINE_LENGTH = 50;
    /**
     * Formatter string that separates the name from the sequence within an alignment
     */
    protected static String FORMATTER = "      ";

    /**
     * Constructor from field parameters
     * @param header {@link String} header, most likely to be CLUSTAL [version]
     * @param alignmentLines {@link List} of {@link AlignmentLine}s that represent the alignment
     */
    protected ClustalW2Aln(String header, List<ClustalAln.AlignmentLine> alignmentLines) {
        super(header, alignmentLines);
    }

    /**
     *
     * @param cutoff {@code double}, a cutoff at which a most frequent
     *                             letter in a column gets accepted as a consensus letter
     * @return {@link String} representation of the consensus. In case a column consensus could not be reached, an N substitutes
     */
    @Override
    public String getConsensus(double cutoff) {
        //TODO implement a way to track if a consensus has been calculated at a given cutoff or not so that a redundand recalculation won't occur
        //Prepare a StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        //For every column within the alignment try finding a consensus
        for (int i = 0; i < this.alignmentLines.get(0).getSequence().length(); i++) {
            List<Character> column = new ArrayList<>(this.alignmentLines.size());
            for (AlignmentLine alignmentLine : this.alignmentLines) {
                column.add(alignmentLine.getSequence().charAt(i));
            }
            //..and appedn the consensus to the builder
            stringBuilder.append(ClustalW2Aln.consensusCharAtCutoff(column,cutoff));
        }
        this.consensus=stringBuilder.toString();
        return this.consensus;
    }

    /**
     * Calculates a consensus character out of a given column
     * @param characters {@link List} of {@link Character} that represents an alignment column
     * @param cutoff {@code double}, if the frequency of a character within a column is over the cutoff,
     *                             the character gets accepted
     * @return {@link Character} consensus of it's frequency was over or equals the cutoff, otherwise - 'N'
     */
    private static Character consensusCharAtCutoff(List<Character> characters, double cutoff) {
        //Calculates a set of characters that occur within a column
        Set<Character> characterSet = new HashSet<>(characters);
        //Assign every character its frequency with a prepared TreeMap
        Map<Double, Character> frequencies = new HashMap<>();
        //For every character calculate a frequency
        for (Character c : characterSet) {
            frequencies.put((double) Collections.frequency(characters, c)/characters.size(), c);
        }
        //Determine the highest frequency

        Double maxFrequency = Collections.max(frequencies.keySet());
        //If the maximum frequency is over or equal the cutoff return the character that occurs with that frequency
        if (maxFrequency*100 >= cutoff) {
            return frequencies.get(maxFrequency);
        } else {
            //Otherwise return an 'N'
            return ClustalW2Aln.BLANK;
        }
    }

    /**
     *
     * @return {@link String} that is a formatted representation of the alignment
     * (just as it is within the alignment file from ClustalW2)
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.header);
        stringBuilder.append("\n\n\n");
        int position = 0;
        while (position < this.alignmentLines.get(0).getSequence().length()) {
            for (int i = 0; i < this.alignmentLines.size(); i++) {
                stringBuilder.append(this.alignmentLines.get(i).getName());
                stringBuilder.append(ClustalW2Aln.FORMATTER);
                if (position + ClustalW2Aln.FORMATTER_LINE_LENGTH < this.alignmentLines.get(i).getSequence().length()) {
                    stringBuilder.append(this.alignmentLines.get(i).getSequence().substring(position, position + ClustalW2Aln.FORMATTER_LINE_LENGTH));
                    stringBuilder.append('\n');
                } else {
                    stringBuilder.append(this.alignmentLines.get(i).getSequence().substring(position, this.alignmentLines.get(i).getSequence().length()));
                    stringBuilder.append('\n');
                }
            }
            position += ClustalW2Aln.FORMATTER_LINE_LENGTH;
            stringBuilder.append('\n');
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    /**
     * Static factory to get an new instance of an alignment from a ClustalW2 .aln file
     * @param alnFile {@link File} that contains a ClustalW2 .aln file
     * @return {@link ClustalW2Aln} from a given file
     * @throws IOException in case an error occurs during the file read
     * @throws AlignmentException in case the given file is corrupt or poorly formatted
     */
    public static ClustalW2Aln newInstaceFromFile(File alnFile) throws IOException, AlignmentException {

        String header;
        String line;
        String[] split;
        List<String> names = new ArrayList<>();
        List<AlignmentLine> alignmentLineList = new ArrayList<>();
        List<StringBuilder> sequenceBuilders = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(alnFile));
        //Get the header
        if (!(header = bufferedReader.readLine()).startsWith(ClustalW2Aln.CLUSTAL)) {
            throw new AlignmentException("Bad start of aln file. Should contain "
                    + "\"" + ClustalW2Aln.CLUSTAL + "\""
                    + " Please check if the file was created with Clustal2W.");
        }
        //Scip the first two lines, which are empty anyways
        for (int i = 0; i < 2; i++) {
            if ((line = bufferedReader.readLine()).length() > 0) {
                throw new AlignmentException("Bad aln file format, please check formatting.");
            }
        }
        //Get a full list of names
        while ((line = bufferedReader.readLine()).length() > 0) {
            split = line.split(ClustalW2Aln.FORMATTER);
            if (split.length >= 1&&!split[0].equals("")) {
                names.add(split[0].trim());
                sequenceBuilders.add(new StringBuilder(split[1].trim()));
            }
        }
        //Get to the end of file assembling the sequences
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            split = line.split(ClustalW2Aln.FORMATTER);
            if (split.length >= 2&&!split[0].equals("")) {
                sequenceBuilders.get(i).append(split[split.length-1].trim());
                i++;
            } else if (split[0].length() == 0) {
                i = 0;
            }
        }
        //Convert to AlignmentLines
        for (i = 0; i < names.size(); i++) {
            alignmentLineList.add(new ClustalAln.AlignmentLine(i, names.get(i), sequenceBuilders.get(i).toString()));
        }
        return new ClustalW2Aln(header, alignmentLineList);
    }
}
