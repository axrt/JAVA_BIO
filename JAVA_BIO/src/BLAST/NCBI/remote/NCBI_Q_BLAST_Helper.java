package BLAST.NCBI.remote;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import BLAST.NCBI.output.BlastOutput;

public enum NCBI_Q_BLAST_Helper {

	Instance;
	private NCBI_Q_BLAST_Helper() {

	}

	/**
	 * <b>Description</b> Specfies the upper limit on number alignment to
	 * display in result<br>
	 * <b>Value</b> Integer<br>
	 * <b>Default</b> 100, 50 <br>
	 * <b>CMD</b> Put, Get<br>
	 * <b>Example</b> '...&ALIGNMENTS=1000&...' sets this limit to 1000<br>
	 * <b>Note:</b> Default of 100 and 50 are used in the 'Put' and 'Get' URLs,
	 * respectively, if not specified explicitly. The default for wwwblast is 50
	 * if not specified in the URL. Commandline equivalent is '-b'.<br>
	 */
	protected static final String ALIGNMENTS = "ALIGNMENTS";
	/**
	 * <b>Description</b> Specifies the type of alignment view for the result
	 * display<br>
	 * <b>Value</b> Pairwise, PairwiseWithIdentities, QueryAnchored,
	 * QueryAnchoredNoIdentities, FlatQueryAnchored,
	 * FlatQueryAnchoredNoIdentities, Tabular<br>
	 * <b>Default</b> Pairwise<br>
	 * <b>CMD</b> Put, Get<br>
	 * <b>Example</b> '...&ALIGNMENT_VIEW=Tabular&...' sets this to Hit Table
	 * view.<br>
	 * <b>Note:</br> For 'FORMAT_OBJECT=Alignment' only. Same as in wwwblast
	 * (integer value). Commandline BLAST equivalent: '-m'.
	 * PairwiseWithIdentities has no commandline or wwwblast equivalent.<br>
	 */
	protected static final String ALIGNMENT_VIEW = "ALIGNMENT_VIEW";
	/**
	 * <b>Description</b> BLAST program to use for the search<br>
	 * <b>Value</b> blastn, MegaBlast, discoMegablast; blastp, psiBlast,
	 * phiBlast; blastx, tblastn, tblastx<br>
	 * <b>Default</b> <i>optional</i><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&BLAST_PROGRAM=blastx&...' sets this to blastx.<br>
	 */
	protected static final String BLAST_PROGRAM = "BLAST_PROGRAM";
	/**
	 * <b>Description</b> Performs simultaneous cdd search for protein BLAST<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b><i> false</i><br>
	 * <b>CMD</b> Put, Web<br>
	 * <b>Example</b> '...&CDD_SEARCH=off&...' will turn this off.<br>
	 * <b>Note:</b> Set to 'false' if not explicitly specified within the URL.
	 * CDD search result can be retrieved using a valid RID if this is set to
	 * 'true'. For details, see 6.2. This parameter is not available in
	 * wwwblast, which cannot perform simultaneous CDD search. For standalone,
	 * use rpsblast program.<br>
	 */
	protected static final String CDD_SEARCH = "CDD_SEARCH";
	/**
	 * <b>Description</b> Specifies the action requested from Blast.cgi<br>
	 * <b>Value</b> Put, Get, Web, Info, request<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> <i>self</i><br>
	 * <b>Example</b> '...&CMD=Web&...' is used by Blast.cgi to generate BLAST
	 * pages.<br>
	 * <b>Note:</b> For script assisted search request, use 'Put'. For script
	 * assisted result retrieval, use 'Get'. No wwwblast or commandline BLAST
	 * equivalent.<br>
	 */
	protected static final String CMD = "CMD";
	/**
	 * <b>Description</b> Type of composition based statistics to apply<br>
	 * <b>Value</b> 0, 1 , 2 , 3<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&COMPOSITION_BASED_STATISTICS=1&...' uses the
	 * composition based statistics described in <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node106.html#Schaffer:2001:Nucleic-Acids-Res:11452024"
	 * >[5]</a>.<br>
	 * <b>Note:</b> For 'PROGRAM=blastp' only. Use 'TWEAK_PARAMETERS=on' in
	 * wwwblast is equivalent to '1'. Commandline blastpgp equivalent is '-t'.
	 * Commandline blastp equivalent is '-C'. For details, see <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node106.html#Schaffer:2001:Nucleic-Acids-Res:11452024"
	 * >[5]</a> and <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node106.html#Yu:2005:Bioinformatics:15509610"
	 * >[7]</a>.<br>
	 */
	protected static final String COMPOSITION_BASED_STATISTICS = "COMPOSITION_BASED_STATISTICS";
	/**
	 * <b>Description</b> Specifies the target DATABASE<br>
	 * <b>Value</b> List of common set available through 'CMD=info' with no
	 * other parameters<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&DATABASE=est_human&...' sets the target database to
	 * est_human. Multiple database like '...&DATABASE=refseq_rna+patnt&...'
	 * acceptable.<br>
	 * <b>Note:</b> Must be specified explicitly for script assisted search
	 * request. Use the multiple databases option sparingly! It could result in
	 * CPU time limit related warning/errors, like those given in 8.2. For more
	 * database choices, see <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/remote_blastdblist.html"
	 * >this</a>. Same in wwwblast. Commandline blast equivalent: '-d'.<br>
	 */
	protected static final String DATABASE = "DATABASE";
	/**
	 * <b>Description</b> Specifies which type of matched subjects are placed
	 * first in Description<br>
	 * <b>Value</b> Integer<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&DATABASE_SORT=1&...' will place the matched genomic
	 * sequences first.<br>
	 * <b>Note:</b> It requires 'NEW_VIEW=true' and works only for the two
	 * special genomic plus transcript databases for human and mouse, where '0 =
	 * RNA first' and '1 = genomic first'. The molecular type of entries within
	 * these two databases are marked by special group bits in their ASN.1
	 * deflines.<br>
	 */
	protected static final String DATABASE_SORT = "DATABASE_SORT";
	/**
	 * <b>Description</b> Specifies database subdirectory name<br>
	 * <b>Value</b> string, valid database subdirectory name<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&DATABASE_PREFIX=Microbial&...' will set this
	 * subdirectory to Microbial and restricts the database lookup to that
	 * subdirectory.<br>
	 * <b>Note:</b>The directory string can be combined with database name
	 * bypassing this parameter altogether, as in
	 * '...&DATABASE=Microbial/83333&...'. <br>
	 */
	protected static final String DATABASE_PREFIX = "DATABASE_PREFIX";
	/**
	 * <b>Description</b> Specifies the codon table to use in the translation of
	 * the target database<br>
	 * <b>Value</b> 1 to 16, 21, 22<br>
	 * <b>Default</b> <b>1</b>, universal codon<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&DB_GENETIC_CODE=1&...' sets this to universal codon<br>
	 * <b>Note:</b> For 'PROGAME=tblastn' or 'PROGRAM=tblastx' only. Same as in
	 * wwwblast. Commandline BLAST equivalent '-D'. More information is <a
	 * href="http://www.ncbi.nlm.nih.gov/Taxonomy/Utils/wprintgc.cgi">here</a>.<br>
	 */
	protected static final String DB_GENETIC_CODE = "DB_GENETIC_CODE";
	/**
	 * <b>Description</b> Specifies the upper limit for number of one line
	 * description to display<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> 100<br>
	 * <b>CMD</b> Put, Get<br>
	 * <b>Example</b> '...&DESCRIPTIONS=500&...' sets the upper limit of
	 * Description to 500.<br>
	 * <b>Note:</b> If not specified in the URL, the default of 100 will be
	 * used. However, a smaller number in the 'Put' step dominates, unless a
	 * larger value is specified in HITLIST_SIZE. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node77.html#sub:Number-of-Alignments"
	 * >this</a>.<br>
	 */
	protected static final String DESCRIPTIONS = "DESCRIPTIONS";
	/**
	 * <b>Description</b> Specifies the display order of database matches<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&DISPLAY_SORT=2&...' sorts the HSPs according to total
	 * scores of the combined HSPs.<br>
	 * <b>Note:</b> It requires 'NEW_VIEW=true' setting in the 'Get' URL with
	 * '0= by Expect value', '1= by Highest score', '2= by Total combined
	 * scores', '3= by Percent identity', and '4= by Query coverage'. For mRNA
	 * vs genomic alignment, sorting by 2, 3, and 4 will bring the true hits to
	 * the top. See examples <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node85.html#sub:Display-of-Description"
	 * >this</a>.<br>
	 */
	protected static final String DISPLAY_SORT = "DISPLAY_SORT";
	/**
	 * <b>Description</b> Specifies the query terms for limiting a given BLAST
	 * search<br>
	 * <b>Value</b> valid Entrez query terms<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put, Web<br>
	 * <b>Example</b> '...&ENTREZ_QUERY=human&5Borgn%5D&...' limits the search
	 * to the human entries in the target database.<br>
	 * <b>Note:</b> The above example is for direct inclusion in a request URL
	 * with special characters escaped. A search through a browser requires the
	 * unescaped terms: human[orgn]. Same as in wwwblast. Partial equivalent in
	 * standalone commandline BLAST: '-l'. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node79.html#sub:Entrez-Query-Terms"
	 * >this</a>.<br>
	 */
	protected static final String ENTREZ_QUERY = "ENTREZ_QUERY";
	/**
	 * <b>Description</b> Specifies the Expect value (significance) cutoff<br>
	 * <b>Value</b> double precision floating point<br>
	 * <b>Default</b> 10<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&EXPECT=1e-10&...' sets this to 10<br>
	 * <b>Note:</b> Similar to p value in statistics, i.e., the smaller the
	 * EXPECT value is, the more significant an alignment will be. Setting this
	 * parameter to a lower value will make the search more stringent or less
	 * sensitive. Same as in wwwblast. Commandline BLAST equivalent: '-e'. For
	 * more information on statistical significance of BLAST alignment, see Dr.
	 * Altschul's 'The Statistics of Sequence Similarity Scores' at <a
	 * href="http://www.ncbi.nlm.nih.gov/BLAST/tutorial/Altschul-1.html">this
	 * page</a>.<br>
	 */
	protected static final String EXPECT = "EXPECT";
	/**
	 * <b>Description</b> Specifies the upper Expect value cutoff for the
	 * formatting step<br>
	 * <b>Value</b> double precision floating point<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&EXPECT_HIGH=0.01&...' will set this cutoff to 0.01<br>
	 * <b>Note:</b> The practical default is the settings in the EXPECT in the
	 * 'Put' step. Works together with EXPECT_LOW to allow the selective display
	 * of alignments with Expect values fall between the range specified by
	 * these two parameters. Irrelevant to wwwblast or commandline BLAST.<br>
	 */
	protected static final String EXPECT_HIGH = "EXPECT_HIGH";
	/**
	 * <b>Description</b> Specifies the lower Expect value cutoff for the
	 * formatting step<br>
	 * <b>Value</b> double<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&EXPECT_LOW=1e-23&...' sets this cutoff to 10 <br>
	 * <b>Note:</b> Works together with 'EXPECT_HIGH' to allow the selective
	 * display of alignments with Expect values fall between the range specified
	 * by these two parameters. Irrelevant to wwwblast or commandline BLAST.<br>
	 */
	protected static final String EXPECT_LOW = "EXPECT_LOW";
	/**
	 * <b>Description</b> Specifies with which query to start formatting results<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> 1<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&FIRST_QUERY_NUM=10&...' will set the first query to
	 * 10.<br>
	 * <b>Note:</b> It works for batch queries with a valid input between 1 up
	 * to the total number of queries in the search. If specified, BLAST will
	 * start formatting from 'input' skipping queries from '1' to 'input - 1'
	 * altogether. No wwwblast or commandline blast equivalent.<br>
	 */
	protected static final String FIRST_QUERY_NUM = "FIRST_QUERY_NUM";
	/**
	 * <b>Description</b> Specifies the filter function(s) to use during the
	 * search<br>
	 * <b>Value</b> T, F, m, L, R, S, D<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&FLITER=L&FILTER=m...' sets this to low complexity and
	 * masking lookup table.<br>
	 * <b>Note:</b> Mutilple FILTER parameter-value pairs are allowed in a
	 * single URL. For species-specific repeat filters, use the newly introduced
	 * REPEATS parameter with 'FILTER=R'. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node80.html#sub:Filter-Strings:-Functions"
	 * >this</a>.<br>
	 */
	protected static final String FILTER = "FILTER";
	/**
	 * <b>Description</b> Specifies the Entrez query term used to restrict the
	 * result displayed<br>
	 * <b>Value</b> valid Entrez query terms<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&FORMAT_ENTREZ_QUERY=biomol_mrna%5Bprop%5D&...' will
	 * selectively display alignments to database sequences that are annotated
	 * as mRNAs.<br>
	 * <b>Note:</b> Applied after search and affects only the display of BLAST
	 * result. The complete result can still be retrieved by dropping this
	 * parameter. No equivalent in wwwblast or standalone BLAST. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node79.html#sub:Entrez-Query-Terms"
	 * >this</a>.<br>
	 */
	protected static final String FORMAT_ENTREZ_QUERY = "FORMAT_ENTREZ_QUERY";
	/**
	 * <b>Description</b> Specifies the result object to format and display<br>
	 * <b>Value</b> Alignment, PSSM, TaxBlast, Bioseq<br>
	 * <b>Default</b> Alignment<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&FORMAT_OBJECT=TaxBlast&...' will format the Taxonomy
	 * Report.<br>
	 * <b>Note:</b> This parameter works in conjunction with FORMAT_TYPE. Not
	 * relevant to wwwblast or commandline BLAST. For batch query,
	 * 'FORMAT_OBJECT=TaxBlast' only works for the first query.<br>
	 */
	protected static final String FORMAT_OBJECT = "FORMAT_OBJECT";
	/**
	 * <b>Description</b> Specifies the results format<br>
	 * <b>Value</b> HTML, Text, ASN.1, XML<br>
	 * <b>Default</b> HTML<br>
	 * <b>CMD</b> Put, Get<br>
	 * <b>Example</b> '...&FORMAT_TYPE=Text&...' will format the results in
	 * plain text format.<br>
	 * <b>Note:</b> This parameter works in conjunction with FORMAT_OBJECT.
	 * Supported combinations are 'FORMAT_OBJECT=Alignment' with
	 * 'FORMAT_TYPE=HTML' (or Text, XML, ASN.1), 'FORMAT_OBJECT=Bioseq' with
	 * 'FORMAT_TYPE=ASN.1', 'FORMAT_OBJECT=PSSM' with 'FORMAT_TYPE=Text' , and
	 * 'FORMAT_OBJECT=TaxBlast' with 'FORMAT_TYPE=HTML'. Not relevant to
	 * wwwblast. Partial equivalent in comandline BLAST is '-m' and '-t'.<br>
	 */
	protected static final String FORMAT_TYPE = "FORMAT_TYPE";
/**
	 * <b>Description</b> Specifies the gap opening and gap extension costs<br>
	 * <b>Value</b> two space separated integers<br>
	 * <b>Default</b> <'11 1' or '5 2'>
	 * <b>CMD</b> Pub<br>
	 * <b>Example</b> '...&GAPCOSTS=12+2&...' will set the values to 12 and 2, respectively.<br>
	 * <b>Note:</b> Only a limited set of values are acceptable for supported scoring matrices. If not specified in the 'Put' URL, the default 'GAPCOSTS=11+1' will be used for blastp, and 'GAPCOSTS=5+2' will be used for blastn. See details on this setting <a href="http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node89.html#sub:On-Matrices-and">here</a>.<br>
	 */
	protected static final String GAPCOSTS = "GAPCOSTS";
	/**
	 * <b>Description</b> Specifies the codon table to use for query translation<br>
	 * <b>Value</b> 1 to 16, 21, 22<br>
	 * <b>Default</b> 1, universal codon<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&GENETIC_CODE=1&...' sets it to universal codon.<br>
	 * <b>Note:</b> Used by 'PROGAME=blastx' and 'PROGAME=tblastx'. Same as in
	 * wwwblast. Commandline BLAST equivalent '-Q'.<br>
	 */
	protected static final String GENETIC_CODE = "GENETIC_CODE";
	/**
	 * <b>Description</b> Generates sequence retrieval buttons and check boxes<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> true<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&GET_SEQUENCE=on&...' will turn on this feature.<br>
	 * <b>Note:</b> Set to 'true' if not explicitly specified in the 'Get' URL.
	 * Irrelevant to script assisted searches. No equivalent in standalone
	 * wwwblast and commandline BLAST.<br>
	 */
	protected static final String GET_SEQUENCE = "GET_SEQUENCE";
	/**
	 * <b>Description</b> Sets the upper limit for number of alignments and
	 * descriptions available in the formatting and reformatting step.<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> depends on the blast pages used<br>
	 * <b>CMD</b> self<br>
	 * <b>Example</b> '...&HITLIST_SIZE=1000&...' will set the upper limit to
	 * 1000.<br>
	 * <b>Note:</b> A smaller setting in HITLIST_SIZE takes priority over those
	 * in ALIGNMENTS and DESCRIPTIONS, which overlap with the newly introduced
	 * MAX_NUM_SEQ (5.1.33). Irrelevant to wwwblast and standalone commandline
	 * BLAST. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node77.html#sub:Number-of-Alignments"
	 * >this</a>.<br>
	 */
	protected static final String HITLIST_SIZE = "HITLIST_SIZE";
	/**
	 * <b>Description</b> Sorts HSPs from the same subject in various ways<br>
	 * <b>Value</b> Integer<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&HSP_SORT=2&...' will sort the HSPs by query start.<br>
	 * <b>Note:</b> It sorts HSPs from the same subject or database sequence
	 * according to user selected criteria: '0= by Expect value', '1 = by
	 * Score', '2 = by Query start', '3 = by Percent Identity', '4 = by Subject
	 * start'. Options 2 and 4 can place the HSPs from mRNA vs genomic alignment
	 * in their correct order.<br>
	 */
	protected static final String HSP_SORT = "HSP_SORT";
	/**
	 * <b>Description</b> Sets Expect value cutoff for inclusion of sequences in
	 * PSSM construction<br>
	 * <b>Value</b> double<br>
	 * <b>Default</b> 0.001<br>
	 * <b>CMD</b> Put, Get<br>
	 * <b>Example</b> '...&I_THRESH=0.005&...' will set this to 0.005.<br>
	 * <b>Note:</b> For 'RUN_PSIBLAST=true' only. Default will be used if not
	 * explicitly specified in the 'Put' or 'Get' URL. Use E_THRESH in wwwblast
	 * and '-h' in commandline BLAST, respectively.<br>
	 */
	protected static final String I_THRESHOLD = "I_THRESHOLD";
	/**
	 * <b>Description</b> Masks the lowercase region of the query sequence<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&LCASE_MASK=on&...' will turn on this masking<br>
	 * <b>Note:</b> This parameter instructs BLAST to skip the regions of the
	 * query that are in lowercase during hit table construction, so that
	 * matches to the remainder of the query can be better evaluated. Same as in
	 * wwwblast. Use '-U' in commandline BLAST.<br>
	 */
	protected static final String LCASE_MASK = "LCASE_MASK";
	/**
	 * <b>Description</b> Selects the character to use for the display of masked
	 * regions<br>
	 * <b>Value</b> 1 (X/N), 2 (lowercase)<br>
	 * <b>Default</b> 2<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&MASK_CHAR=1&...' will display masked regions using
	 * N's or X's<br>
	 * <b>Note:</b> The region masked by low complexity filter and/or repeat
	 * filter will be displayed in the type of characters specified by this
	 * parameter. Not available in standalone wwwblast or commandline BLAST.<br>
	 */
	protected static final String MASK_CHAR = "MASK_CHAR";
	/**
	 * <b>Description</b> Selects the color in which to display the masked
	 * regions<br>
	 * <b>Value</b> 0, 1, 2 (black, grey, red)<br>
	 * <b>Default</b> 1<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&MASK_COLOR=2&...' will displayed the masked region in
	 * red<br>
	 * <b>Note:</b> This specifies the font color of the filter masked region.
	 * No equivalents for standalone wwwblast and commandline BLAST. See <a
	 * href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node87.html#sub:Display-CDS-and"
	 * >this</a>.<br>
	 */
	protected static final String MASK_COLOR = "MASK_COLOR";
	/**
	 * <b>Description</b> Specifies the nucleotide match/mismatch scores<br>
	 * <b>Value</b> Comma separated integer pairs<br>
	 * <b>Default</b> 1,-3<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&MATCH_SCORES=1,-3&...' will set match to 1 and
	 * mismatch to -3<br>
	 * <b>Note:</b> The default will be used if this parameter was not
	 * explicitly specified in the put URL. It supersedes the 'NUCL_REWARD' and
	 * 'NUCL_PENALTY'. The restriction on input values to the old parameters
	 * also applies to MATCH_SCORES. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node90.html#sub:Nucleotide-Scoring-Matrices"
	 * >this</a>.<br>
	 */
	protected static final String MATCH_SCORES = "MATCH_SCORES";
	/**
	 * <b>Description</b> Determines the protein score matrix to use<br>
	 * <b>Value</b> PAM30, PAM70, BLOSUM45, BLOSUM42, BLOSUM80<br>
	 * <b>Default</b> BLOSUM62<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&MATRIX_NAME=PAM30&...' will set this to PAM30.<br>
	 * <b>Note:</b> Using input value other than the default also requires
	 * change in the 'GAPCOSTS' parameter. The allowed list of input values to
	 * 'GAPCOSTS' for supported matrces is given in 6.13.2. Use parameter
	 * 'MATRIX_PARAM' in wwwblast, which combines these two parameters together.
	 * Equivalent in standalone command BLAST '-M'.<br>
	 */
	protected static final String MATRIX_NAME = "MATRIX_NAME";
	/**
	 * <b>Description</b> Specifies the maximum number of hits returned<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> 100<br>
	 * <b>CMD</b> Put, Get<br>
	 * <b>Example</b> '...&MAX_NUM_SEQ=1000&...' sets this limit to 1000.<br>
	 * <b>Note:</b> This is the upper limit and its function overlaps with
	 * 'ALIGNMENTS' and 'DESCRIPTIONS'. The actual search may have fewer actual
	 * hits than the setting. Partial equivalent in standalone wwwblast,
	 * ALIGNMENTS and DESCRIPTIONS. Partial commandline equivalent: '-b' and
	 * '-v'.<br>
	 */
	protected static final String MAX_NUM_SEQ = "MAX_NUM_SEQ";
	/**
	 * <b>Description</b> Use the megablast algorithm in nucleotide BLAST<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&MEGABLAST=on&...' will turn on the megablast
	 * algorithm<br>
	 * <b>Note:</b> For 'PROGRAM=blastn' only. Default is 'false' if not
	 * specified. When set to 'true' WORD_SIZE setting will be adjusted to 28 if
	 * not specified explicitly. Same as in wwwblast. Use megablast in
	 * commandline BLAST. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node81.html#sub:MEGABLAST-Features"
	 * >this</a>.<br>
	 */
	protected static final String MEGABLAST = "MEGABLAST";
	/**
	 * <b>Description</b> Displays the gi numbers for the database matches<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&NCBI_GI=true&...' will turn this display on.<br>
	 * <b>Note:</b> Set to 'false' if not specified in the 'Get' URL. Same as in
	 * wwwblast. For commandline BLAST, use '-I T'<br>
	 */
	protected static final String NCBI_GI = "NCBI_GI";
	/**
	 * <b>Description</b> Display the BLAST result in new window<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&NEWWIN=true&...' will display the format/result in a
	 * newly spawned window.<br>
	 * <b>Note:</b> For searches submitted through BLAST search pages only.
	 * Irrelevant to script assisted searches. No equivalent in wwwblast or
	 * commandline BLAST.<br>
	 */
	protected static final String NEWWIN = "NEWWIN";
	/**
	 * <b>Description</b> Display the BLAST result in new window<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&NEWWINRES=true&...' will display the result in a
	 * newly spawned window.<br>
	 * <b>Note:</b> For result retrieval through the formatting page only.
	 * Irrelevant to searches submitted through scripts. No equivalent in
	 * wwwblast or commandline BLAST.<br>
	 */
	protected static final String NEWWINRES = "NEWWINRES";
	/**
	 * <b>Description</b> Displays the BLAST sans the graphic header<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> false<br>
	 * <b>Example</b> '...&NOHEADER=true&...' will turn off the header display.<br>
	 * <b>Note:</b> When set to 'true', BLAST will strip the header section from
	 * the returned result to make it smaller and more readable. No equivalent
	 * in wwwblast or commandline BLAST.<br>
	 */
	protected static final String NOHEADER = "NOHEADER";
	/**
	 * <b>Description</b> Specifies the upper limit of alignments displayed in
	 * graphic overview<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> 100<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&NUM_OVERVIEW=200&...' resets this limit to 200.<br>
	 * <b>Note:</b> This parameter affects only the graphic overview portion of
	 * the BLAST results. It requires 'SHOW_OVERVIEW=on' and is set to 100 if
	 * not explicitly specified in the 'Get' URL. Irrelevant to script assisted
	 * search. No equivalent in wwwblast and irrelevant to commandline BLAST.<br>
	 */
	protected static final String NUM_OVERVIEW = "NUM_OVERVIEW";
	/**
	 * <b>Description</b> Accepts parameters value pairs not specified by
	 * individual parameters listed here<br>
	 * <b>Value</b> strings of acceptable option value pairs<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&OTHER_ADVANCED=-A+50&...' will set the two hits
	 * window size to 50.<br>
	 * <b>Note:</b> It is a way for user to adjust search with custom
	 * parameters/values. Multiple option/value pairs acceptable. See 6.7 for
	 * details. Inputs must be properly escaped for use within the URL. Same as
	 * in wwwblast. Irrelevant to standalone commandline BLAST.<br>
	 */
	protected static final String OTHER_ADVACED = "OTHER_ADVACED";
	/**
	 * <b>Description</b> Specifies the type of BLAST template to generate<br>
	 * <b>Value</b> BlastHome, BlastDocs, BlastNews, BlastTips, BlastSearch,
	 * BlastFormatting, BlastResults<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Web<br>
	 * <b>Example</b> <b>-</b><br>
	 * <b>Note:</b> It supersedes the old PAGE parameter. Irrelevant to script
	 * assisted searches. No equivalent in wwwblast or standalone commandline
	 * BLAST.<br>
	 */
	protected static final String PAGE_TYPE = "PAGE_TYPE";
	/**
	 * <b>Description</b> Specifies the percent identity cutoff for the
	 * alignment display<br>
	 * <b>Value</b> integer between 0 to 100<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&PERC_IDENT=100&...' set this to 100%<br>
	 * <b>Note:</b> This parameter only works with 'MEGABLAST=on'. The cutoff is
	 * for HSP or aligned region only. The same is true for wwwblast.
	 * Commandline BLAST equivalent: '-p'.<br>
	 */
	protected static final String PERC_IDENT = "PERC_IDENT";
	/**
	 * <b>Description</b> Specifies the pattern for use with PHI-BLAST searches<br>
	 * <b>Value</b> pattern in ProSite syntax<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&PHI_PATTERN=%5BRK%5D-X(3)-%5BDE%5D-X(2)-Y.&...' sets
	 * the pattern to [RK]-X(3)-[DE]-X(2)-Y.<br>
	 * <b>Note:</b> The example has its special characters escaped for direct
	 * use in a URL, which can be used in conjunction with a sequence containing
	 * the pattern, such as NP_000240. The pattern will not work without a
	 * corresponding sequence! For input in the web form, the unescaped version
	 * should be used. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node92.html#sub:Pattern-Syntax-for"
	 * >this</a>. Same as in wwwblast. Commandline BLAST equivalent: '-k'.<br>
	 */
	protected static final String PHI_PATTERN = "PHI_PATTERN";
	/**
	 * <b>Description</b> Specifies the BLAST program to use (and the type of
	 * search to perform)<br>
	 * <b>Value</b> blastn, blastp, blastx, tblastn, tblastx<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&PROGRAM=blastn&...' uses blastn for nucleotide
	 * alignment<br>
	 * <b>Note:</b> This parameter must be specified. Same as in wwwblast.
	 * Commandline BLAST equivalent: '-p'<br>
	 */
	protected static final String PROGRAM = "PROGRAM";
	/**
	 * <b>Description</b> Sepecifies the input PSSM<br>
	 * <b>Value</b> string (PSSM from previous PSI-BLAST search)<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> <b>-</b><br>
	 * <b>Note:</b> The PSSM is derived from a previous PSI-BLAST iterative
	 * search with the same query. This parameter is not available in wwwblast.
	 * Standalone commandline BLAST equivalent: '-R'.<br>
	 */
	protected static final String PSSM = "PSSM";
	/**
	 * <b>Description</b> Sepecifies the input query<br>
	 * <b>Value</b> Accession, GI, or actual sequences in various formats<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&QUERY=NP_000240&...' set query to NP_000240<br>
	 * <b>Note:</b> This parameter must be specified. Batch queries with actual
	 * sequences should be in FASTA format. If batch sequence IDs or actual
	 * sequences are to be used within the URL, they must be properly escaped.
	 * See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node101.html#sub:Escape-of-Unsafe"
	 * >this</a> for details. Use 'SEQUENCE' in wwwblast. Commandline BLAST
	 * equivalent: '-i', which takes a text file name and accepts only FASTA
	 * formatted sequences.<br>
	 */
	protected static final String QUERY = "QUERY";
	/**
	 * <b>Description</b> Specified whether to believe the query FASTA defline<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&QUERY_BELIEVE_DEFLINE=true&...' sets this to true.<br>
	 * <b>Note:</b> No equivalent in wwwblast. Commandline BLAST equivalent:
	 * '-J'.<br>
	 */
	protected static final String QUERY_BELIEVE_DEFLINE = "QUERY_BELIEVE_DEFLINE";
	/**
	 * <b>Description</b> Specifies the query starting position when only a
	 * portion is to be used<br>
	 * <b>Value</b> integer, from 1 to sequence length, must be smaller than the
	 * 'QUERY_TO'<br>
	 * <b>Default</b> 1<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&QUERY_FROM=20&...' set this to 20.<br>
	 * <b>Note:</b> It works with 'QUERY_TO' to specifies the region of the
	 * query to use in the search. For batch queries, the input values are
	 * applied to all queries. Same as in wwwblast. Commandline BLAST
	 * equivalent: '-L'.<br>
	 */
	protected static final String QUERY_FROM = "QUERY_FROM";
	/**
	 * <b>Description</b> Specifies the query ending position when only a
	 * portion is to be used<br>
	 * <b>Value</b> integer, up to sequence length, must be greater than the
	 * 'QUERY_FROM'<br>
	 * <b>Default</b> sequence length<br>
	 * <b>CMD</b> Put, Web<br>
	 * <b>Example</b> '...&QUERY_TO=120&...' set this to 120.<br>
	 * <b>Note:</b> It works with 'QUERY_FROM' to specifies the region of the
	 * query to use in the search. Same as in wwwblast. Standalone commandline
	 * BLAST equivalent: '-L'.<br>
	 */
	protected static final String QUERY_TO = "QUERY_TO";
	/**
	 * <b>Description</b> Specifies the species-specific repeat libraries to use<br>
	 * <b>Value</b> string<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&REPEATS=repeat_7723&...' uses zebrafish repeat
	 * libraries<br>
	 * <b>Note:</b> Requires 'FILTER=R'. For available input values, see <a
	 * href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node80.html#Table-6.4-Other"
	 * >this</a>. No equivalent in wwwblast. Commandline BLAST equivalent: '- F
	 * R ``-d library'' ', where library is the name of the species-specific
	 * repeat library.<br>
	 */
	protected static final String REPEATS = "REPEATS";
	/**
	 * <b>Description</b> Specifies the RID to use<br>
	 * <b>Value</b> <i>valid RID</i><br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&RID=8J5TS393012&CMD=Get&...' retrieves the result for
	 * 8J5TS393012 <br>
	 * <b>Note:</b> The parameter must be specified in the 'Get' URL. The new
	 * BLAST page now uses a much shorter form of RID different from the long,
	 * mostly digits format. It is an alpha-numeric token and should be regarded
	 * as such during regular expression matching. Irrelevant to wwwblast and
	 * commandline BLAST.<br>
	 */
	protected static final String RID = "RID";
	/**
	 * <b>Description</b> Formats the protein BLAST result for PSI-BLAST
	 * iterations<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Put, Get<br>
	 * <b>Example</b> '...&RUN_PSIBLAST=true&...' turns a blastp search into a
	 * PSI-BLAST search <br>
	 * <b>Note:</b> For 'PROGRAM=blastp' only. Irrelevant to script assisted
	 * search since iterative PSI-BLAST search through BLAST URLAPI is not
	 * officially supported at this time. Use psiblast.cgi and blastpgp for
	 * wwwblast and commandline BLAST, respectively.<br>
	 */
	protected static final String RUN_PSIBLAST = "RUN_PSIBLAST";
	/**
	 * <b>Description</b> Specifies the effective search space size to use in
	 * Expect value calculation<br>
	 * <b>Value</b> real<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&SEARCHSP_EFF=1e8&...' set this parameter to 10<br>
	 * <b>Note:</b> Effective search space is the product of the effective query
	 * length and the effective database length. Use '-Y' for standalone
	 * commandline BLAST, or '-Y value' under OTHER_ADVANCED parameter for
	 * wwwblast.<br>
	 */
	protected static final String SEARCHSP_EFF = "SEARCHSP_EFF";
	/**
	 * <b>Description</b> Adjusts parameter settings automatically for queries
	 * 30 letters or shorter<br>
	 * <b>Value</b> <true, false> <b>Default</b> false<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&SHORT_QUERY_ADJUST=true&...' activates this autmatic
	 * adjustment<br>
	 * <b>Note:</b> Set to 'false' if not explicitly specified to 'true'. See <a
	 * href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node93.html#sub:Searching-with-Short"
	 * >this</a>. No equivalent in standalone wwwblast or commandline BLAST. To
	 * search with short sequences, manually set WORD_SIZE (-W), FILTER (-F),
	 * and EXPECT (-e) parameters.<br>
	 */
	protected static final String SHORT_QUERY_ADJUST = "SHORT_QUERY_ADJUST";
	/**
	 * <b>Description</b> Activates translations of CDS features found in the
	 * matches<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> false<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&SHOW_CDS_FEATURES=true&...' turns this feature on.<br>
	 * <b>Note:</b> BLAST server also translates the CDS (ORFs) features on the
	 * query using the annotated features on the database entry as a guide. Not
	 * available to standalone wwwblast or commandline BLAST. For example
	 * display, see <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node87.html#sub:Display-CDS-and"
	 * >this</a>.<br>
	 */
	protected static final String SHOW_CDS_FEATURES = "SHOW_CDS_FEATURES";
	/**
	 * <b>Description</b> Displays linkout icons for database hits with
	 * additional information in other databases<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> true<br>
	 * <b>CMD</b> <br>
	 * <b>Example</b> '...&SHOW_LINKOUT=false&...' turns off this feature<br>
	 * <b>Note:</b> Set to 'true' if not specified in the 'Get' URL. Generally
	 * it is irrelevant to script assisted searches. Sets 'true' by default
	 * under wwwblast if NCBI preformatted database is used. No equivalent in
	 * commandline BLAST. For examples, see <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node85.html#sub:Display-of-Description"
	 * >this</a> and <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node87.html#sub:Display-CDS-and"
	 * >this</a>.<br>
	 */
	protected static final String SHOW_LINKOUT = "SHOW_LINKOUT";
	/**
	 * <b>Description</b> Displays graphic overview for the BLAST result<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> true<br>
	 * <b>CMD</b> Get<br>
	 * <b>Example</b> '...&SHOW_OVERVIEW=on&...' turns on this feature<br>
	 * <b>Note:</b> Set to 'true' if not specified in the 'Get' URL. For script
	 * assisted searches, set it to 'false'. Use 'OVERVIEW' in wwwblast. Not
	 * available to commandline BLAST. For display example, see <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node84.html#sub:Show-Overview"
	 * >this</a>.<br>
	 */
	protected static final String SHOW_OVERVIEW = "SHOW_OVERVIEW";
	/**
	 * <b>Description</b> Determines the length of template within which the
	 * word match is evaluated<br>
	 * <b>Value</b> 16, 18, 21<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put, Web<br>
	 * <b>Example</b> '...&TEMPLATE_LENGTH=21&...' sets this to 21<br>
	 * <b>Note:</b> For 'MEGABLAST=on' only. It works in combination with
	 * 'TEMPLATE_TYPE' to activates discontiguous MEGABLAST. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node88.html#sub:Template-Length-and"
	 * >this</a>. Same as in wwwblast. Commandline BLAST equivalent: '-t'.<br>
	 */
	protected static final String TEMPLATE_LENGTH = "TEMPLATE_LENGTH";
	/**
	 * <b>Description</b> Determines the type of template to use in evaluating
	 * word matches<br>
	 * <b>Value</b> 0, 1, 2 (coding, non-coding, both)<br>
	 * <b>Default</b> 0<br>
	 * <b>CMD</b> Put, Web<br>
	 * <b>Example</b> '...&TEMPLATE_TYPE=0&...' sets this to coding type
	 * template<br>
	 * <b>Note:</b> For 'MEGABLAST=on' only. It works in combination with
	 * 'TEMPLATE_LENGTH' to activates discontiguous MEGABLAST. See <a href=
	 * "http://www.ncbi.nlm.nih.gov/staff/tao/URLAPI/new/node88.html#sub:Template-Length-and"
	 * >this</a>. Same as in wwwblast. Commandline BLAST equivalent: '-N'.<br>
	 */
	protected static final String TEMPLATE_TYPE = "TEMPLATE_TYPE";
	/**
	 * <b>Description</b> Determines the threshold for extending word matches<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> depends on BLAST pages used<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&THRESHOLD=13&...' sets this to 13<br>
	 * <b>Note:</b> For non-nucleotide searches. Nucleotide searches are
	 * controlled by WORD_SIZE. Not available to wwwblast. Standalone
	 * commandline equivalent: '-f'.<br>
	 */
	protected static final String THRESHOLD = "THRESHOLD";
	/**
	 * <b>Description</b> Requires two word hits within a given distance for
	 * alignment extension<br>
	 * <b>Value</b> true, false<br>
	 * <b>Default</b> true<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&TWO_HITS=false&...' deactivates this requirement<br>
	 * <b>Note:</b> Used only by discontiguous megablast. Activation of this
	 * makes the search more stringent. Same as in wwwblast. Commandline BLAST
	 * equivalent: '-A'.<br>
	 */
	protected static final String TWO_HITS = "TWO_HITS";
	/**
	 * <b>Description</b> Specifies the size of word to use<br>
	 * <b>Value</b> integer<br>
	 * <b>Default</b> 3, 11, 28<br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&WORD_SIZE=2&...' sets this to 2 for protein BLAST.<br>
	 * <b>Note:</b> For protein searches use 2 or 3, default is 3. For
	 * nucleotide searches use integers greater or equal to 7, default is 11.
	 * For nucleotide searches with 'MEGABLAST=on', use 8 or greater, default is
	 * 28. Discomegablast uses 11 or 12. Same as in wwwblast. Commandline BLAST
	 * equivalent: '-W'.<br>
	 */
	protected static final String WORD_SIZE = "WORD_SIZE";
	/**
	 * <b>Description</b> Specifies the BLAST type<br>
	 * <b>Value</b> string<br>
	 * <b>Default</b> <b>-</b><br>
	 * <b>CMD</b> Put<br>
	 * <b>Example</b> '...&WWW_BLAST_TYPE=mapview&...' turns on MapView related
	 * display links<br>
	 * <b>Note:</b> This parameter is only for organism specific databases with
	 * genome maps in MapViewer. When activated, hits are hyperlinked to graphic
	 * display in MapViewer. Mostly irrelevant to script assisted searches.<br>
	 */
	protected static final String WWW_BLAST_TYPE = "WWW_BLAST_TYPE";

	/**
	 * Return a {@code BlastOutput} from an {@code InputStream}. Used by: 1.
	 * {@code NCBI_BLAST} to get the output
	 * 
	 * @param in
	 *            :{@code InputStream } from a URL or other type of connecton
	 * @return {@code BlastOutput}
	 * @throws JAXBException 
	 * @throws Exception
	 */
	public static BlastOutput catchBLASTOutput(InputStream in) throws SAXException, JAXBException {
		JAXBContext jc = JAXBContext.newInstance(BlastOutput.class);
		Unmarshaller u = jc.createUnmarshaller();
		XMLReader xmlreader = XMLReaderFactory.createXMLReader();
		xmlreader.setFeature("http://xml.org/sax/features/namespaces", true);
		xmlreader.setFeature("http://xml.org/sax/features/namespace-prefixes",
				true);
		xmlreader.setEntityResolver(new EntityResolver() {

			@Override
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				String file = null;
				if (systemId.contains("NCBI_BlastOutput.dtd")) {
					file = "NCBI_BlastOutput.dtd";
				}
				if (systemId.contains("NCBI_Entity.mod.dtd")) {
					file = "NCBI_Entity.mod.dtd";
				}
				if (systemId.contains("NCBI_BlastOutput.mod.dtd")) {
					file = "NCBI_BlastOutput.mod.dtd";
				}
				return new InputSource(BlastOutput.class
						.getResourceAsStream(file));
			}
		});
		InputSource input = new InputSource(in);
		Source source = new SAXSource(xmlreader, input);
		return (BlastOutput) u.unmarshal(source);
	}
}
