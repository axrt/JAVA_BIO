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
 * A representation of a QBLAST parameter.
 * 
 * @author axrt
 * 
 */
public class NCBI_Q_BLAST_Parameter extends Q_BLAST_Parameter {

	protected static final char equals = '=';

	/**
	 * Private constructor
	 * 
	 * @param key
	 *            {@link String} value from {@link NCBI_Q_BLAST} static
	 *            parameters
	 * @param value
	 *            {@code String} value, specific for a certain parameter
	 */
	protected NCBI_Q_BLAST_Parameter(String key, String value) {
		super(key, value);
	}

	/**
	 * Returns a {@link String} representation of the key-value pair.<br>
	 * <b>Example:</b> ALIGNMENTS=100
	 */
	@Override
	public String toString() {
		return this.key + NCBI_Q_BLAST_Parameter.equals + this.value;
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value for a parameter that will be automatically
	 *            converted to {@code String}
	 * @return a new {@code NCBI_Q_BLAST_Parameter} of
	 *         <b>"ALIGNMENTS=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter ALIGNMENTS(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.ALIGNMENTS,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>ALIGNMENT_VIEW</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum ALIGNMENT_VIEW_PARAM {
		Pairwise, PairwiseWithIdentities, QueryAnchored, QueryAnchoredNoIdentities, FlatQueryAnchored, FlatQueryAnchoredNoIdentities, Tabular
	}

	/**
	 * 
	 * @param value
	 *            {@link ALIGNMENT_VIEW_PARAM} value for a parameter that will
	 *            be automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"ALIGNMENT_VIEW=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter ALIGNMENT_VIEW(
			ALIGNMENT_VIEW_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.ALIGNMENT_VIEW,
				value.name());
	}

	/**
	 * A set of allowed parameters for the <b>BLAST_PROGRAM</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum BLAST_PROGRAM_PARAM {
		blastn, MegaBlast, discoMegablast, blastp, psiBlast, phiBlast, blastx, tblastn, tblastx
	}

	/**
	 * 
	 * @param value
	 *            {@link BLAST_PROGRAM_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"BLAST_PROGRAM=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter BLAST_PROGRAM(BLAST_PROGRAM_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.BLAST_PROGRAM,
				value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"BLAST_PROGRAM=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter CDD_SEARCH(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.CDD_SEARCH,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>CMD</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum CMD_PARAM {
		Put, Get, Web, Info, requestx
	}

	/**
	 * 
	 * @param value
	 *            {@link CMD_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"CMD=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter CMD(CMD_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.CMD, value.name());
	}

	/**
	 * A set of allowed parameters for the <b>COMPOSITION_BASED_STATISTICS</b>
	 * parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum COMPOSITION_BASED_STATISTICS_PARAM {
		zero, one, two, three
	}

	/**
	 * 
	 * @param value
	 *            {@link COMPOSITION_BASED_STATISTICS_PARAM} value for a
	 *            parameter that will be automatically converted to
	 *            {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"COMPOSITION_BASED_STATISTICS=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter COMPOSITION_BASED_STATISTICS(
			COMPOSITION_BASED_STATISTICS_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(
				NCBI_Q_BLAST_Helper.COMPOSITION_BASED_STATISTICS, value.name());
	}

	/**
	 * A set of allowed parameters for the <b>DATABASE</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum DATABASE_PARAM {
		nr, refseq_rna, refseq_genomic, chromosome, est, gss, htgs, pat, pdb, alu, dbsts, Whole_Genome_Shotgun_contigs, tsa_nt, TL, refseq_protein, swissprot, env_nr
	}

	/**
	 * 
	 * @param value
	 *            {@link DATABASE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"DATABASE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter DATABASE(DATABASE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.DATABASE,
				value.name());
	}

	/**
	 * 
	 * @param values
	 *            Overloads, making it possible to choose multiple databases.
	 *            {@link DATABASE_PARAM}[] values for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"DATABASE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter DATABASE(DATABASE_PARAM[] values) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i].name());
			if (i < values.length) {
				sb.append('+');
			}
		}
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.DATABASE,
				new String(sb));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value for a parameter that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DATABASE_SORT=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter DATABASE_SORT(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.DATABASE_SORT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value, <u>string, valid database subdirectory
	 *            name</u>.
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DATABASE_PREFIX=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter DATABASE_PREFIX(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.DATABASE_PREFIX,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>COMPOSITION_BASED_STATISTICS</b>
	 * parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum GENETIC_CODE_PARAM {
		one("1"), two("2"), three("3"), four("4"), five("5"), six("6"), seven(
				"7"), eight("8"), nine("9"), ten("10"), eleven("11"), twelve(
				"12"), thirteen("13"), fourteen("14"), fifteen("15"), sixteen(
				"16"), twentyone("21"), twentytwo("22");
		private final String value;

		/**
         * Constructor from genetic code
		 * @param value {@link String} genetic code
		 */
		private GENETIC_CODE_PARAM(final String value) {
			this.value = value;
		}

		/**
		 * Enum to {@link String} conversion
		 */
		@Override
		public String toString() {
			return this.value;
		}
	}

	/**
	 * 
	 * @param value
	 *            {@link GENETIC_CODE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DB_GENETIC_CODE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter DB_GENETIC_CODE(
			GENETIC_CODE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.DB_GENETIC_CODE,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value for a parameter that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DESCRIPTIONS=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter DESCRIPTIONS(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.DESCRIPTIONS,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value for a parameter that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DISPLAY_SORT=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter DISPLAY_SORT(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.DISPLAY_SORT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid Entrez query terms
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"ENTREZ_QUERY=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter ENTREZ_QUERY(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.ENTREZ_QUERY,
				value);
	}

	/**
	 * 
	 * @param value
	 *            {@code double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"EXPECT=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter EXPECT(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.EXPECT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"EXPECT_HIGH=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter EXPECT_HIGH(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.EXPECT_HIGH,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"EXPECT_LOW=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter EXPECT_LOW(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.EXPECT_LOW,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"FIRST_QUERY_NUM=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter FIRST_QUERY_NUM(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.FIRST_QUERY_NUM,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>FILTER</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum FILTER_PARAM {
		T, F, m, L, R, S, D
	}

	/**
	 * 
	 * @param value
	 *            {@link FILTER_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"FILTER=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter FILTER(FILTER_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.FILTER,
				value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid Entrez query terms
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"FORMAT_ENTREZ_QUERY=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter FORMAT_ENTREZ_QUERY(String value) {
		return new NCBI_Q_BLAST_Parameter(
				NCBI_Q_BLAST_Helper.FORMAT_ENTREZ_QUERY, value);
	}

	/**
	 * A set of allowed parameters for the <b>FORMAT_OBJECT</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum FORMAT_OBJECT_PARAM {
		Alignment, PSSM, TaxBlast, Bioseq
	}

	/**
	 * 
	 * @param value
	 *            {@link FORMAT_OBJECT_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"FORMAT_OBJECT=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter FORMAT_OBJECT(FORMAT_OBJECT_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.FORMAT_OBJECT,
				value.name());
	}

	/**
	 * A set of allowed parameters for the <b>FORMAT_TYPE</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum FORMAT_TYPE_PARAM {
		HTML("HTML"), Text("Text"), ASN("ASN.1"), XML("XML");
		private final String value;

		/**
         * Constructor from format type
		 * @param value {@link String}
		 */
		private FORMAT_TYPE_PARAM(final String value) {
			this.value = value;
		}

		/**
		 * Enum to {@link String} conversion
		 */
		@Override
		public String toString() {
			return this.value;
		}
	}

	/**
	 * 
	 * @param value
	 *            {@link FORMAT_TYPE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"FORMAT_TYPE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter FORMAT_TYPE(FORMAT_TYPE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.FORMAT_TYPE,
				value.toString());
	}

	/**
     * Static factory from values
	 * @param firstValue {@code int}firstValue
	 * @param secondValue {@code int} secondValue
	 *            parameters,
	 *            that will be automatically converted to {@link String} in a
	 *            "value-space-value" format
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"GAPCOSTS=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter GAPCOSTS(int firstValue,
			int secondValue) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.GAPCOSTS,
				String.valueOf(firstValue) + ' ' + String.valueOf(secondValue));
	}

	/**
	 * 
	 * @param value
	 *            {@link GENETIC_CODE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"GENETIC_CODE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter GENETIC_CODE(GENETIC_CODE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.GENETIC_CODE,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"GET_SEQUENCE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter GET_SEQUENCE(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.GET_SEQUENCE,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"HITLIST_SIZE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter HITLIST_SIZE(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.HITLIST_SIZE,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"HSP_SORT=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter HSP_SORT(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.HSP_SORT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"I_THRESHOLD=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter I_THRESHOL(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.I_THRESHOLD,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"LCASE_MASK=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter LCASE_MASK(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.LCASE_MASK,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum MASK_CHAR_PARAM {

		X_N("1"), lowercase("2");
		private final String value;

		/**
         * Constructor from mask character
		 * @param value {@link String} masking character
		 */
		private MASK_CHAR_PARAM(final String value) {
			this.value = value;
		}

		/**
		 * Enum to {@link String} conversion
		 */
		@Override
		public String toString() {
			return this.value;
		}
	}

	/**
	 * 
	 * @param value
	 *            {@link MASK_CHAR_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"MASK_CHAR=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter MASK_CHAR(MASK_CHAR_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.MASK_CHAR,
				value.toString());
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum MASK_COLOR_PARAM {

		black("0"), grey("1"), red("2");
		private final String value;

		/**
         * Constructor from masking color parameter
		 * @param value {@link String} masking color
		 */
		private MASK_COLOR_PARAM(final String value) {
			this.value = value;
		}

		/**
		 * Enum to {@link String} conversion
		 */
		@Override
		public String toString() {
			return this.value;
		}
	}

	/**
	 * 
	 * @param value
	 *            {@link MASK_COLOR_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"MASK_COLOR=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter MASK_COLOR(MASK_COLOR_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.MASK_COLOR,
				value.toString());
	}

	/**
     * Static factory from values
	 * @param firstValue {@code int} firstValue
	 * @param secondValue  {@code int} secondValue
	 *            parameters,
	 *            that will be automatically converted to {@link String} in a
	 *            "value-comma-value" format
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"MATCH_SCORES=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter MATCH_SCORES(int firstValue,
			int secondValue) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.MATCH_SCORES,
				String.valueOf(firstValue) + ',' + String.valueOf(secondValue));
	}

	/**
	 * A set of allowed parameters for the <b>MATRIX_NAME</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum MATRIX_NAME_PARAM {
		PAM30, PAM70, BLOSUM45, BLOSUM42, BLOSUM80
	}

	/**
	 * 
	 * @param value
	 *            {@link MATRIX_NAME_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"MATRIX_NAME=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter MATRIX_NAME(MATRIX_NAME_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.MATRIX_NAME,
				value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"MAX_NUM_SEQ=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter MAX_NUM_SEQ(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.MAX_NUM_SEQ,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"MEGABLAST=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter MEGABLAST(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.MEGABLAST,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NCBI_GI=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter NCBI_GI(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.NCBI_GI,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NEWWIN=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter NEWWIN(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.NEWWIN,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NEWWINRES=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter NEWWINRES(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.NEWWINRES,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NOHEADER=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter NOHEADER(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.NOHEADER,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"NUM_OVERVIEW=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter NUM_OVERVIEW(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.NUM_OVERVIEW,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid Entrez query terms
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"OTHER_ADVACED=value"</b>.<br>
	 *         <b>Example:</b> '...&OTHER_ADVANCED=<b><u>-A+50</b></u>&...' will
	 *         set the two hits window size to 50.
	 */
	public static NCBI_Q_BLAST_Parameter OTHER_ADVACED(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.OTHER_ADVACED,
				value);
	}

	/**
	 * A set of allowed parameters for the <b>PAGE_TYPE</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum PAGE_TYPE_PARAM {
		BlastHome, BlastDocs, BlastNews, BlastTips, BlastSearch, BlastFormatting, BlastResults
	}

	/**
	 * 
	 * @param value
	 *            {@link PAGE_TYPE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"PAGE_TYPE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter PAGE_TYPE(PAGE_TYPE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.PAGE_TYPE,
				value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"PERC_IDENT=value"</b>. As the percent value should stay
	 *         between 0 and 100, in case a negative value or a value>100
	 *         provided, returns either{@link NCBI_Q_BLAST_Parameter}
	 *         <b>"PERC_IDENT=0"</b> or <b>"PERC_IDENT=100"</b> respectively.
	 */
	public static NCBI_Q_BLAST_Parameter PERC_IDENT(int value) {
		if (value < 0) {
			return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.PERC_IDENT,
					String.valueOf(0));
		} else if (value > 100) {
			return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.PERC_IDENT,
					String.valueOf(100));
		} else {
			return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.PERC_IDENT,
					String.valueOf(value));
		}
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid pattern in ProSite syntax
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"PHI_PATTERN=value"</b>.<br>
	 *         <b>Example:</b>
	 *         '...&PHI_PATTERN=%5BRK%5D-X(3)-%5BDE%5D-X(2)-Y.&...' sets the
	 *         pattern to [RK]-X(3)-[DE]-X(2)-Y.
	 */
	public static NCBI_Q_BLAST_Parameter PHI_PATTERN(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.PHI_PATTERN,
				value);
	}

	/**
	 * A set of allowed parameters for the <b>MATRIX_NAME</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum PROGRAM_PARAM {
		blastn, blastp, blastx, tblastn, tblastx
	}

	/**
	 * 
	 * @param value
	 *            {@link PROGRAM_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"PROGRAM=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter PROGRAM(PROGRAM_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.PROGRAM,
				value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid pattern in ProSite syntax
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"PSSM=value"</b>
	 *         string (PSSM from previous PSI-blast search).<br>
	 */
	public static NCBI_Q_BLAST_Parameter PSSM(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.PSSM, value);
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid pattern in ProSite syntax
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"QUERY=value"</b>
	 *         Accession, GI, or actual sequences in various formats.<br>
	 */
	public static NCBI_Q_BLAST_Parameter QUERY(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.QUERY, value);
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"QUERY_BELIEVE_DEFLINE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter QUERY_BELIEVE_DEFLINE(boolean value) {
		return new NCBI_Q_BLAST_Parameter(
				NCBI_Q_BLAST_Helper.QUERY_BELIEVE_DEFLINE,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"QUERY_FROM=value"</b>
	 *         integer, from 1 to sequence length, must be smaller than the
	 *         'QUERY_TO'.
	 */
	public static NCBI_Q_BLAST_Parameter QUERY_FROM(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.QUERY_FROM,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"QUERY_TO=value"</b>
	 *         integer, up to sequence length, must be greater than the
	 *         'QUERY_FROM'.
	 */
	public static NCBI_Q_BLAST_Parameter QUERY_TO(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.QUERY_TO,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value, specifies the species-specific repeat
	 *            libraries to use
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"REPEATS=value"</b>.<br>
	 */
	public static NCBI_Q_BLAST_Parameter REPEATS(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.REPEATS, value);
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value, specifies the RID to use
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"RID=value"</b>, valid
	 *         RID.<br>
	 */
	public static NCBI_Q_BLAST_Parameter RID(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.RID, value);
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"RUN_PSIBLAST=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter RUN_PSIBLAST(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.RUN_PSIBLAST,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SEARCHSP_EFF=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter SEARCHSP_EFF(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.SEARCHSP_EFF,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHORT_QUERY_ADJUST=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter SHORT_QUERY_ADJUST(boolean value) {
		return new NCBI_Q_BLAST_Parameter(
				NCBI_Q_BLAST_Helper.SHORT_QUERY_ADJUST, String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHOW_CDS_FEATURE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter SHOW_CDS_FEATURE(boolean value) {
		return new NCBI_Q_BLAST_Parameter(
				NCBI_Q_BLAST_Helper.SHOW_CDS_FEATURES, String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHOW_LINKOUT=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter SHOW_LINKOUT(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.SHOW_LINKOUT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHOW_OVERVIEW=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter SHOW_OVERVIEW(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.SHOW_OVERVIEW,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum TEMPLATE_LENGTH_PARAM {

		sixteen("16"), eighteen("18"), twentyone("21");
		private final String value;

		/**
         * Constructor from template length
		 * @param value {@link String} template length
		 */
		private TEMPLATE_LENGTH_PARAM(final String value) {
			this.value = value;
		}

		/**
		 * Enum to {@link String} conversion
		 */
		@Override
		public String toString() {
			return this.value;
		}
	}

	/**
	 * 
	 * @param value
	 *            {@link TEMPLATE_LENGTH_PARAM} value for a parameter that will
	 *            be automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"TEMPLATE_LENGTH=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter TEMPLATE_LENGTH(
			TEMPLATE_LENGTH_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.TEMPLATE_LENGTH,
				value.toString());
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	public enum TEMPLATE_TYPE_PARAM {

		coding("0"), non_coding("1"), both("2");
		private final String value;

		/**
         * Constructor from template type parameter
         *
		 * @param value {@link String} template type parameter
		 */
		private TEMPLATE_TYPE_PARAM(final String value) {
			this.value = value;
		}

		/**
		 * Enum to {@link String} conversion
		 */
		@Override
		public String toString() {
			return this.value;
		}
	}

	/**
	 * 
	 * @param value
	 *            {@link TEMPLATE_TYPE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"TEMPLATE_TYPE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter TEMPLATE_TYPE(TEMPLATE_TYPE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.TEMPLATE_TYPE,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"THRESHOLD=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter THRESHOLD(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.THRESHOLD,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"TWO_HITS=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter TWO_HITS(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.TWO_HITS,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"WORD_SIZE=value"</b>.
	 */
	public static NCBI_Q_BLAST_Parameter WORD_SIZE(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.WORD_SIZE,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value, specifies the RID to use
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"WWW_BLAST_TYPE=value"</b>, valid RID.<br>
	 */
	public static NCBI_Q_BLAST_Parameter WWW_BLAST_TYPE(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST_Helper.WWW_BLAST_TYPE,
				value);
	}

}
