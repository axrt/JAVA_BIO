package format.fasta;

import java.io.Serializable;
import java.util.HashSet;

public class ProteinFasta extends Fasta implements Serializable {

	static {
		// Initializing the legal character set for a protein sequence
		allowedChars = new HashSet<Character>(
				java.util.Arrays.asList(new Character[] { 'A', 'R', 'N', 'D',
						'C', 'E', 'Q', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P',
						'S', 'T', 'W', 'Y', 'V', 'U', 'O', '\n', '/' }));
	}

	/**
	 * Constructor
	 * 
	 * @param {@link String} AC
	 * @param {@link String} sequence
	 */
	protected ProteinFasta(String AC, String sequence)
			throws ProteinFasta_AC_BadFormatException,
			ProteinFasta_Sequence_BadFromatException {
		super(AC, sequence);
		// Enforce uppercase
		AC = AC.toUpperCase();
		sequence = sequence.toUpperCase();
		// First check for whether the AC does not contain an illegal '>'
		// diamond character anywhere, except for the very beginning
		if (AC.contains(Fasta.fastaStart)) {
			// If so, check whether if is in the beginning
			if (!AC.startsWith(Fasta.fastaStart)) {
				throw new ProteinFasta_AC_BadFormatException(
						"Error within the AC: \'>\' at position "
								+ String.valueOf(AC.indexOf(Fasta.fastaStart))
								+ '!');
			} else {
				AC = new String(AC.substring(1));
			}
		}
		// Ac should also contain no new line symbols
		if (AC.contains("\n")) {
			throw new ProteinFasta_AC_BadFormatException(
					"Error within the AC: \'\n\' at position "
							+ AC.indexOf("\n") + '!');
		}
		// If the sequence provided contains any illegal characters - complain
		// and point to the position of an illegal character
		int check = Fasta.checksOutForIllegalCharacters(sequence);
		if (check != 0) {
			throw new ProteinFasta_Sequence_BadFromatException(
					"Error within the Sequence: illegal character at positon: "
							+ String.valueOf(check));
		}
	}

	/**
	 * A static factory to assemble a {@link ProteinFasta} from AC and a
	 * sequence.
	 * 
	 * @param {@link String} AC
	 * @param {@link String} sequence
	 * @return a new instance of a {@link ProteinFasta}
	 * @throws ProteinFasta_AC_BadFormatException
	 *             in case smth is not all right with the AC
	 * @throws ProteinFasta_Sequence_BadFromatException
	 *             in case smth is not all right with the sequence
	 */
	public static ProteinFasta newInstanceFromParts(String AC, String sequence)
			throws ProteinFasta_AC_BadFormatException,
			ProteinFasta_Sequence_BadFromatException {

		// If all goes well, return a new ProteinFasta record object.
		return new ProteinFasta(AC, sequence);
	}

	/**
	 * A static factory to assemble a {@link ProteinFasta} from a {@link String}
	 * , that represents a fasta-fromatted record
	 * 
	 * @param {@link String} fastaRecord
	 * @return a new instance of a {@link ProteinFasta}
	 * @throws ProteinFasta_BadFromat_Exception
	 * @throws ProteinFasta_AC_BadFormatException
	 * @throws ProteinFasta_Sequence_BadFromatException
	 */
	public static ProteinFasta newInstanceFromFromattedText(String fastaRecord)
			throws ProteinFasta_BadFromat_Exception,
			ProteinFasta_AC_BadFormatException,
			ProteinFasta_Sequence_BadFromatException {
		// Get the first row and check whether it is good for an AC
		String[] splitter = fastaRecord.split("\n");
		if (splitter.length < 2) {
			throw new ProteinFasta_BadFromat_Exception(
					"Protein Fasta record: bad format; represented by a single line.");
		} else {
			// Prepare a StringBuilder of a proper (at least close to proper)
			// size
			StringBuilder sb = new StringBuilder(splitter.length
					* splitter[1].length());
			for (int i = 1; i < splitter.length; i++) {
				// Get rid of all the spaces on the fly
				// Concatenate the sequence
				sb.append(splitter[i].replaceAll(" ", ""));
			}
			return ProteinFasta.newInstanceFromParts(splitter[0],
					new String(sb));
			// TODO: this implementation may not be the fastest, see if smth
			// could be done
		}
	}
}
