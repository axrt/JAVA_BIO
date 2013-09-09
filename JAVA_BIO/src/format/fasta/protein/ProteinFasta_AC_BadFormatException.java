package format.fasta.protein;

import blast.ncbi.remote.NCBI_Q_BLAST_Parameter;
import format.BadFormatException;
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
 * This exception is thrown whenever a forbidden {@link NCBI_Q_BLAST_Parameter}
 * is being attempted to set for a blast
 * 
 * @author axrt
 * 
 */
public class ProteinFasta_AC_BadFormatException extends BadFormatException {
	/**
	 * Counstructor
	 */
	public ProteinFasta_AC_BadFormatException() {

	}

	/**
	 * Constructor from message
	 * 
	 * @param message {@link String} message
	 */
	public ProteinFasta_AC_BadFormatException(String message) {
		super(message);

	}

	/**
	 * Counstructor from a {@link Throwable}
	 * 
	 * @param cause {@link Throwable} cause
	 */
	public ProteinFasta_AC_BadFormatException(Throwable cause) {
		super(cause);

	}

	/**
	 * Contructor from both message and a {@link Throwable}
	 * 
	 * @param message  {@link String} message
	 * @param cause   {@link Throwable} cause
	 */
	public ProteinFasta_AC_BadFormatException(String message, Throwable cause) {
		super(message, cause);

	}

}
