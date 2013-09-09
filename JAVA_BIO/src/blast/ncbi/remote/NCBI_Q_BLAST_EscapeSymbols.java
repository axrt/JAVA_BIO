package blast.ncbi.remote;
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
 * @author axrt<br>
 *         This class contains static representations of commonly used
 *         parameters for the QBLAST querry. For more detailed info please
 *         consult <a href=
 *         "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node101.html#sub:Escape-of-Unsafe"
 *         >this</a> and <a
 *         href="http://www.google.ru/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&v
 *         e d =0CFYQFjAA&url
 *         =http%3A%2F%2Falextblog.blogspot.com%2F2012%2F05%2Fncbi-blast-jaxb
 *         -biojava-blasting
 *         -like.html&ei=PjrkT5WFIInm-gaMtb2OCg&usg=AFQjCNF3uh28ITE1urA7wVPYGkHRPdmO_Q
 *         &sig2=Kq_j1KT32l1ERD-NTtkOkQ">this</a>.
 */
public class NCBI_Q_BLAST_EscapeSymbols {

	private NCBI_Q_BLAST_EscapeSymbols() {
		throw new AssertionError();
	}

	/**
	 * The ' ' symbol is escaped with '+'
	 */
	public static final char whiteSpace = '+';
	/**
	 * The '>' symbol on a fasta-formatted file start is escaped with "%3E"
	 */
	public static final String fastaStart = "%3E";
	/**
	 * The '[' symbol is escaped with "%5B"
	 */
	public static final String leftSquareBracket = "%5B";
	/**
	 * The ']' symbol is escaped with "%5D"
	 */
	public static final String rightSquareBracket = "%5D";
	/**
	 * The "new line" symbol is escaped with "%0D%0A"
	 */
	public static final String newLine = "%0D%0A";
	/**
	 * The '|' symbol is escaped with "%7C"
	 */
	public static final String verticalSlash = "%7C";
	/**
	 * The '%' symbol is escaped with "%25"
	 */
	public static final String percent = "%25";
	/**
	 * The '@' symbol is escaped with "%40"
	 */
	public static final String atSign = "%40";
	/**
	 * The '#' symbol is escaped with "%23"
	 */
	public static final String numberSign = "%23";

	/**
	 * Reformats all QBLAST illegal characters for legal substitution
	 * characters. For more detail please consult <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node101.html#sub:Escape-of-Unsafe"
	 * >ncbi documentation.</a><br>
	 * 
	 * @param toReformat
	 *            a {@link String} to format
	 * @return a reformatted {@link String} ready for a QBLAST task request.
	 */
	public static String reformatForRequest(String toReformat) {
		toReformat = toReformat.replaceAll(String.valueOf('%'),
				NCBI_Q_BLAST_EscapeSymbols.percent);
		toReformat = toReformat.replaceAll("\\[",
				NCBI_Q_BLAST_EscapeSymbols.leftSquareBracket);
		toReformat = toReformat.replaceAll("\\]",
				NCBI_Q_BLAST_EscapeSymbols.rightSquareBracket);
		toReformat = toReformat.replaceAll(String.valueOf(' '),
				String.valueOf(NCBI_Q_BLAST_EscapeSymbols.whiteSpace));
		toReformat = toReformat.replaceAll(String.valueOf('\n'),
				NCBI_Q_BLAST_EscapeSymbols.newLine);
		toReformat = toReformat.replaceAll(String.valueOf('#'),
				NCBI_Q_BLAST_EscapeSymbols.numberSign);
		toReformat = toReformat.replaceAll(String.valueOf('@'),
				NCBI_Q_BLAST_EscapeSymbols.atSign);
		toReformat = toReformat.replaceAll("\\|",
				NCBI_Q_BLAST_EscapeSymbols.verticalSlash);
		toReformat = toReformat.replaceAll(String.valueOf('>'),
				NCBI_Q_BLAST_EscapeSymbols.fastaStart);
		return toReformat;
	}
}
