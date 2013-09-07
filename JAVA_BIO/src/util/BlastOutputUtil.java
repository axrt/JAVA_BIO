package util;

import blast.ncbi.output.Hit;
import format.BadFormatException;

/**
 * Contains utility methods for the {@link blast.ncbi.output.BlastOutput} processing
 */
public class BlastOutputUtil {
    /**
     * Constructor grants non-instantiability
     */
    private BlastOutputUtil() {
        throw new AssertionError();
    }

    /**
     * Helps extract the GI from the Hit ID string, where it is mixed with
     * AC.revision ad gi/gb makers
     *
     * @param hitID {@link String} The id of the hit that may be formatted
     *              like this, gi|385760777|gb|JQ426063.1|,
     * @return {@link String} extracted GI of the hit
     */
    public static String extractGIFromHitID(String hitID) throws BadFormatException {
        //The IF from a Hit looks smth like this, gi|385760777|gb|JQ426063.1|,
        //so it nedds to get splitted by "\\|"
        String[] splitter = hitID.split("\\|");
        if (splitter.length > 1) {
            return splitter[1];
        } else {
            throw new BadFormatException("The hitID provided is corrupt..");
        }
    }

    /**
     * Converts the textual representation of the E-value of a given hit to double value
     *
     * @param h {@code Hit} that contains the desired E-value parameter
     * @return {@code double} representation of the E-value
     */
    public static double getEvalueFromHit(Hit h) {
        return Double.parseDouble(h.getHitHsps().getHsp().get(0).getHspEvalue());
    }

    /**
     * Calculates pIdent for a given {@link Hit}
     *
     * @param h {@code Hit} that contains the desired pIdent
     * @return {@code double} representation of the pIdent, if {@code null} instead of a {@link Hit} - returns 0
     */
    public static double calculatePIdent(Hit h) {
        if(h==null||h.getHitHsps().getHsp().get(0).getHspIdentity()==null||h.getHitHsps().getHsp().get(0).getHspAlignLen()==null){
            return 0;
        }
        double identities = Double.parseDouble(h.getHitHsps().getHsp().get(0).getHspIdentity());
        double alingnmentLenght = Double.parseDouble(h.getHitHsps().getHsp().get(0).getHspAlignLen());
        return Math.ceil(identities * 100 / alingnmentLenght);
    }

    /**
     * Calculates the query coverage for a given {@code Hit}
     *
     * @param queryLength {@code int} of the querry sequence
     * @param h          {@link Hit} that contains the desired query coverage parameter
     * @return {@code double} representation of the query coverage parameter
     */
    public static double calculateQueryCoverage(int queryLength, Hit h) {
        return (1 + Integer.parseInt(h.getHitHsps().getHsp().get(0).getHspQueryTo())
                - Integer.parseInt(h.getHitHsps().getHsp().get(0).getHspQueryFrom())) * 100 / queryLength;
    }
}
