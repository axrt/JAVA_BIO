package format.fasta.otu;

import format.fasta.nucleotide.NucleotideFasta;

/**
 *TODO Document
 */
public class EncodedNucleotideFasta extends NucleotideFasta{

    protected final String barcode;

    protected EncodedNucleotideFasta(String AC,String barcode, String sequence) {
        super(AC, sequence);
        this.barcode=barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public static EncodedNucleotideFasta convertFromNucleotideFasta (NucleotideFasta nucleotideFasta) throws EncodedNucleotideFastaFormatException {

        String [] split = nucleotideFasta.getAC().split("\t");
        if(split.length<2){
            throw new EncodedNucleotideFastaFormatException("AC does not seem to contain a barcode, please check formatting.");
        }

        return new EncodedNucleotideFasta(split[0].trim(),split[1].trim(),nucleotideFasta.getSequence());
    }
}
