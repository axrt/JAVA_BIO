package format.fasta;

import java.io.Serializable;
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
 * An Object representation of a fasta-formatted sequence.
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
	 * A conventional number of characters within a fasta-formatted file
	 */
    protected static final int fastaLineLenght = 60;
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
     * A getter for the AC
     * @return {@link String} AC
     */
    public String getAC() {
        return AC;
    }

    /**
     * A getter for the sequence
     * @return {@link String} sequence
     */
    public String getSequence() {
        return sequence;
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
