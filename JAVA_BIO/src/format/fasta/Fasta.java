package format.fasta;

import java.io.Serializable;
import java.util.Set;

/**
 * An Object representation of a fasta-fromatted sequence.
 * 
 * @author axrt
 */
public abstract class Fasta implements Serializable{
	/**
	 * A {@link String} representation of an abstract fasta-formatted sequence.
	 */
	protected final String sequence;
	/**
	 * A {@link String} representation of an abstract fasta record <u>A</u>ccession <u>N</u>umber.
	 */
	protected final String AC;
	/**
	 * Represents a set of valid characters
	 */
	protected static Set<Character> allowedChars;
	/**
	 * Fasta format starter character
	 */
	public static final String fastaStart = ">";
	/**
	 * A conventional number of characters within a fasta-fromatted file
	 */
	private static final int fastaLineLenght = 60;
	/**
	 * A special string to form a BadFastaFileFormatException message in case
	 * the first line of a given fasta does not contain a starter character
	 */
	private static final String badStartFormat = "Bad start of Fasta: ";
	/**
	 * A special string to form a BadFastaFileFormatException message in case
	 * the the sequence of a given fasta contains illegal characters
	 */
	private static final String illegalSymbolFound = "Sequence contains illegal characters!";

	public Fasta(String AC,String sequence) {
		this.AC=AC;
		this.sequence = sequence;
	}

	/**
	 * Checks for whether a given String contains illegal characters (the ones
	 * that do not correspond to any conventional abbreviation and some special
	 * characters).
	 * 
	 * @param stringToCheck
	 *            is a {@link String} that contains a fasta record sequence
	 * @return {@code true} if stringToCheck contains illegal characters,
	 *         {@code false} - if not
	 */
	protected static int checksOutForIllegalCharacters(String stringToCheck) {
		for (int i = 0; i < stringToCheck.length(); i++) {
			if (!Fasta.allowedChars.contains(stringToCheck.charAt(i))) {
				return i;
			}
		}
		return 0;
	}
	/**
     * Allows to extract a fasta-formatted sequence from the Fasta object.
     * The sequence contains 60 characters per row.
     * @return a {@link String} with the text representation of the AC and sequence.
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(Fasta.fastaStart);
        sb.append(this.AC);
        sb.append('\n');
        int line = 0;
        for (int i = 0; i < this.sequence.length(); i++) {
            sb.append(this.sequence.charAt(i));
            line++;
            if (line % Fasta.fastaLineLenght == 0) {
                sb.append('\n');
            }
        }
        return new String(sb);
	}

}
