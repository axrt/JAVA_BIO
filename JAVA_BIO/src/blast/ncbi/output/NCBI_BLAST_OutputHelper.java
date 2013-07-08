/**
 * 
 */
package blast.ncbi.output;

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

/**
 * @author axrt
 * 
 */
public enum NCBI_BLAST_OutputHelper {
	// Singleton-enforcing instance
	Instance;
    /**
     * Constructor
     */
	private NCBI_BLAST_OutputHelper() {

	}

	/**
	 * Return a {@link BlastOutput} from an {@code InputStream}. Used by
	 * {@link NCBI_BLAST} to get the output. Being produced in such a form, it
	 * allows to store the schemas in the same package as the
	 * {@link BlastOutput}, thereby allowing to make it obscure from the user
	 * within the package
	 * 
	 * @param in
	 *            :{@link InputStream } from a URL or other type of connection
	 * @return {@link BlastOutput}
	 * @throws JAXBException
	 * @throws Exception
	 */
	public static BlastOutput catchBLASTOutput(InputStream in)
			throws SAXException, JAXBException {
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
