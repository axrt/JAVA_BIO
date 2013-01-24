package BLAST.NCBI.remote;

/**
 * A representation of a QBLAST parameter.
 * 
 * @author axrt
 * 
 */
public class NCBI_Q_BLAST_Parameter {

	private static final char equals = '=';
	private final String key;
	private final String value;

	/**
	 * Private constructor
	 * 
	 * @param key
	 *            {@link String} value from {@link NCBI_Q_BLAST} static
	 *            parameters
	 * @param value
	 *            {@code String} value, specific for a certain parameter
	 */
	private NCBI_Q_BLAST_Parameter(String key, String value) {
		this.key = key;
		this.value = value;
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
	 * @return the {@link String} key.
	 */
	protected String getKey() {
		return this.key;
	}

	/**
	 * 
	 * @param value
	 *            {@link int} value for a parameter that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"ALIGNMENTS=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter ALIGNMENTS(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.ALIGNMENTS,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>ALIGNMENT_VIEW</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum ALIGNMENT_VIEW_PARAM {
		Pairwise, PairwiseWithIdentities, QueryAnchored, QueryAnchoredNoIdentities, FlatQueryAnchored, FlatQueryAnchoredNoIdentities, Tabular;
	}

	/**
	 * 
	 * @param value
	 *            {@link ALIGNMENT_VIEW_PARAM} value for a parameter that will
	 *            be automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"ALIGNMENT_VIEW=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter ALIGNMENT_VIEW(
			ALIGNMENT_VIEW_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.ALIGNMENT_VIEW,
				value.name());
	}

	/**
	 * A set of allowed parameters for the <b>BLAST_PROGRAM</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum BLAST_PROGRAM_PARAM {
		blastn, MegaBlast, discoMegablast, blastp, psiBlast, phiBlast, blastx, tblastn, tblastx;
	}

	/**
	 * 
	 * @param value
	 *            {@link BLAST_PROGRAM_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"BLAST_PROGRAM=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter BLAST_PROGRAM(
			BLAST_PROGRAM_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.BLAST_PROGRAM,
				value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"BLAST_PROGRAM=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter CDD_SEARCH(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.CDD_SEARCH,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>CMD</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum CMD_PARAM {
		Put, Get, Web, Info, requestx;
	}

	/**
	 * 
	 * @param value
	 *            {@link CMD_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"CMD=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter CMD(CMD_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.CMD, value.name());
	}

	/**
	 * A set of allowed parameters for the <b>COMPOSITION_BASED_STATISTICS</b>
	 * parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum COMPOSITION_BASED_STATISTICS_PARAM {
		zero, one, two, three;
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
	protected static NCBI_Q_BLAST_Parameter COMPOSITION_BASED_STATISTICS(
			COMPOSITION_BASED_STATISTICS_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(
				NCBI_Q_BLAST.COMPOSITION_BASED_STATISTICS, value.name());
	}

	/**
	 * A set of allowed parameters for the <b>DATABASE</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum DATABASE_PARAM {
		nr, refseq_rna, refseq_genomic, chromosome, est, gss, htgs, pat, pdb, alu, dbsts, Whole_Genome_Shotgun_contigs, tsa_nt, TL, refseq_protein, swissprot, env_nr;
	}

	/**
	 * 
	 * @param value
	 *            {@link DATABASE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"DATABASE=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter DATABASE(DATABASE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.DATABASE, value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@link int} value for a parameter that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DATABASE_SORT=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter DATABASE_SORT(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.DATABASE_SORT,
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
	protected static NCBI_Q_BLAST_Parameter DATABASE_PREFIX(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.DATABASE_PREFIX,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>COMPOSITION_BASED_STATISTICS</b>
	 * parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum GENETIC_CODE_PARAM {
		one("1"), two("2"), three("3"), four("4"), five("5"), six("6"), seven(
				"7"), eight("8"), nine("9"), ten("10"), eleven("11"), twelve(
				"12"), thirteen("13"), fourteen("14"), fifteen("15"), sixteen(
				"16"), twentyone("21"), twentytwo("22");
		private final String value;

		/**
		 * @param text
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
	protected static NCBI_Q_BLAST_Parameter DB_GENETIC_CODE(
			GENETIC_CODE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.DB_GENETIC_CODE,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@link int} value for a parameter that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DESCRIPTIONS=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter DESCRIPTIONS(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.DESCRIPTIONS,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link int} value for a parameter that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"DISPLAY_SORT=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter DISPLAY_SORT(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.DISPLAY_SORT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid Entrez query terms
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"ENTREZ_QUERY=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter ENTREZ_QUERY(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.ENTREZ_QUERY, value);
	}

	/**
	 * 
	 * @param value
	 *            {@link double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"EXPECT=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter EXPECT(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.EXPECT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"EXPECT_HIGH=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter EXPECT_HIGH(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.EXPECT_HIGH,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"EXPECT_LOW=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter EXPECT_LOW(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.EXPECT_LOW,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link int} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"FIRST_QUERY_NUM=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter FIRST_QUERY_NUM(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.FIRST_QUERY_NUM,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>FILTER</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum FILTER_PARAM {
		T, F, m, L, R, S, D;
	}

	/**
	 * 
	 * @param value
	 *            {@link FILTER_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"FILTER=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter FILTER(FILTER_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.FILTER, value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid Entrez query terms
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"FORMAT_ENTREZ_QUERY=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter FORMAT_ENTREZ_QUERY(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.FORMAT_ENTREZ_QUERY,
				value);
	}

	/**
	 * A set of allowed parameters for the <b>FORMAT_OBJECT</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum FORMAT_OBJECT_PARAM {
		Alignment, PSSM, TaxBlast, Bioseq;
	}

	/**
	 * 
	 * @param value
	 *            {@link FORMAT_OBJECT_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"FORMAT_OBJECT=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter FORMAT_OBJECT(
			FORMAT_OBJECT_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.FORMAT_OBJECT,
				value.name());
	}

	/**
	 * A set of allowed parameters for the <b>FORMAT_TYPE</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum FORMAT_TYPE_PARAM {
		HTML("HTML"), Text("Text"), ASN("ASN.1"), XML("XML");
		private final String value;

		/**
		 * @param text
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
	protected static NCBI_Q_BLAST_Parameter FORMAT_TYPE(FORMAT_TYPE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.FORMAT_TYPE,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@code int}firstValue, {@code int} secondValue parameters,
	 *            that will be automatically converted to {@link String} in a
	 *            "value-space-value" format
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"GAPCOSTS=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter GAPCOSTS(int firstValue,
			int secondValue) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.GAPCOSTS,
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
	protected static NCBI_Q_BLAST_Parameter GENETIC_CODE(
			GENETIC_CODE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.GENETIC_CODE,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"GET_SEQUENCE=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter GET_SEQUENCE(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.GET_SEQUENCE,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link int} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"HITLIST_SIZE=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter HITLIST_SIZE(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.HITLIST_SIZE,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link int} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"HSP_SORT=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter HSP_SORT(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.HSP_SORT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link double} value, value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"I_THRESHOLD=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter I_THRESHOL(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.I_THRESHOLD,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"LCASE_MASK=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter LCASE_MASK(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.LCASE_MASK,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum MASK_CHAR_PARAM {

		X_N("1"), lowercase("2");
		private final String value;

		/**
		 * @param text
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
	protected static NCBI_Q_BLAST_Parameter MASK_CHAR(MASK_CHAR_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.MASK_CHAR,
				value.toString());
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum MASK_COLOR_PARAM {

		black("0"), grey("1"), red("2");
		private final String value;

		/**
		 * @param text
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
	protected static NCBI_Q_BLAST_Parameter MASK_COLOR(MASK_COLOR_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.MASK_COLOR,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@code int}firstValue, {@code int} secondValue parameters,
	 *            that will be automatically converted to {@link String} in a
	 *            "value-comma-value" format
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"MATCH_SCORES=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter MATCH_SCORES(int firstValue,
			int secondValue) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.MATCH_SCORES,
				String.valueOf(firstValue) + ',' + String.valueOf(secondValue));
	}

	/**
	 * A set of allowed parameters for the <b>MATRIX_NAME</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum MATRIX_NAME_PARAM {
		PAM30, PAM70, BLOSUM45, BLOSUM42, BLOSUM80;
	}

	/**
	 * 
	 * @param value
	 *            {@link MATRIX_NAME_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"MATRIX_NAME=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter MATRIX_NAME(MATRIX_NAME_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.MATRIX_NAME,
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
	protected static NCBI_Q_BLAST_Parameter MAX_NUM_SEQ(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.MAX_NUM_SEQ,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"MEGABLAST=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter MEGABLAST(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.MEGABLAST,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NCBI_GI=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter NCBI_GI(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.NCBI_GI,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NEWWIN=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter NEWWIN(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.NEWWIN,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NEWWINRES=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter NEWWINRES(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.NEWWINRES,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"NOHEADER=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter NOHEADER(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.NOHEADER,
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
	protected static NCBI_Q_BLAST_Parameter NUM_OVERVIEW(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.NUM_OVERVIEW,
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
	protected static NCBI_Q_BLAST_Parameter OTHER_ADVACED(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.OTHER_ADVACED, value);
	}

	/**
	 * A set of allowed parameters for the <b>PAGE_TYPE</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum PAGE_TYPE_PARAM {
		BlastHome, BlastDocs, BlastNews, BlastTips, BlastSearch, BlastFormatting, BlastResults;
	}

	/**
	 * 
	 * @param value
	 *            {@link PAGE_TYPE_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"PAGE_TYPE=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter PAGE_TYPE(PAGE_TYPE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.PAGE_TYPE, value.name());
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
	protected static NCBI_Q_BLAST_Parameter PERC_IDENT(int value) {
		if (value < 0) {
			return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.PERC_IDENT,
					String.valueOf(0));
		} else if (value > 100) {
			return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.PERC_IDENT,
					String.valueOf(100));
		} else {
			return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.PERC_IDENT,
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
	protected static NCBI_Q_BLAST_Parameter PHI_PATTERN(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.PHI_PATTERN, value);
	}

	/**
	 * A set of allowed parameters for the <b>MATRIX_NAME</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum PROGRAM_PARAM {
		blastn, blastp, blastx, tblastn, tblastx;
	}

	/**
	 * 
	 * @param value
	 *            {@link PROGRAM_PARAM} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"PROGRAM=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter PROGRAM(PROGRAM_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.PROGRAM, value.name());
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid pattern in ProSite syntax
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"PSSM=value"</b>
	 *         string (PSSM from previous PSI-BLAST search).<br>
	 */
	protected static NCBI_Q_BLAST_Parameter PSSM(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.PSSM, value);
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value valid pattern in ProSite syntax
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"QUERY=value"</b>
	 *         Accession, GI, or actual sequences in various formats.<br>
	 */
	protected static NCBI_Q_BLAST_Parameter QUERY(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.QUERY, value);
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"QUERY_BELIEVE_DEFLINE=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter QUERY_BELIEVE_DEFLINE(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.QUERY_BELIEVE_DEFLINE,
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
	protected static NCBI_Q_BLAST_Parameter QUERY_FROM(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.QUERY_FROM,
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
	protected static NCBI_Q_BLAST_Parameter QUERY_TO(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.QUERY_TO,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value, specifies the species-specific repeat
	 *            libraries to use
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"REPEATS=value"</b>.<br>
	 */
	protected static NCBI_Q_BLAST_Parameter REPEATS(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.REPEATS, value);
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value, specifies the RID to use
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"RID=value"</b>, valid
	 *         RID.<br>
	 */
	protected static NCBI_Q_BLAST_Parameter RID(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.RID, value);
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"RUN_PSIBLAST=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter RUN_PSIBLAST(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.RUN_PSIBLAST,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SEARCHSP_EFF=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter SEARCHSP_EFF(double value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.SEARCHSP_EFF,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHORT_QUERY_ADJUST=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter SHORT_QUERY_ADJUST(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.SHORT_QUERY_ADJUST,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHOW_CDS_FEATURE=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter SHOW_CDS_FEATURE(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.SHOW_CDS_FEATURES,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHOW_LINKOUT=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter SHOW_LINKOUT(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.SHOW_LINKOUT,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"SHOW_OVERVIEW=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter SHOW_OVERVIEW(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.SHOW_OVERVIEW,
				String.valueOf(value));
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum TEMPLATE_LENGTH_PARAM {

		sixteen("16"), eighteen("18"), twentyone("21");
		private final String value;

		/**
		 * @param text
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
	protected static NCBI_Q_BLAST_Parameter TEMPLATE_LENGTH(
			TEMPLATE_LENGTH_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.TEMPLATE_LENGTH,
				value.toString());
	}

	/**
	 * A set of allowed parameters for the <b>MASK_CHAR</b> parameter
	 * 
	 * @author axrt
	 * 
	 */
	private enum TEMPLATE_TYPE_PARAM {

		coding("0"), non_coding("1"), both("2");
		private final String value;

		/**
		 * @param text
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
	protected static NCBI_Q_BLAST_Parameter TEMPLATE_TYPE(
			TEMPLATE_TYPE_PARAM value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.TEMPLATE_TYPE,
				value.toString());
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"THRESHOLD=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter THRESHOLD(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.THRESHOLD,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link boolean} value for a parameter that will be
	 *            automatically converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"TWO_HITS=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter TWO_HITS(boolean value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.TWO_HITS,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@code int} value parameter, that will be automatically
	 *            converted to {@link String}
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of <b>"WORD_SIZE=value"</b>.
	 */
	protected static NCBI_Q_BLAST_Parameter WORD_SIZE(int value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.WORD_SIZE,
				String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            {@link String} value, specifies the RID to use
	 * @return a new {@link NCBI_Q_BLAST_Parameter} of
	 *         <b>"WWW_BLAST_TYPE=value"</b>, valid RID.<br>
	 */
	protected static NCBI_Q_BLAST_Parameter WWW_BLAST_TYPE(String value) {
		return new NCBI_Q_BLAST_Parameter(NCBI_Q_BLAST.WWW_BLAST_TYPE, value);
	}

}
