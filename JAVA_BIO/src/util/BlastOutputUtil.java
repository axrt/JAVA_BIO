package util;

import blast.ncbi.output.Hit;
import blast.ncbi.output.HitHsps;
import blast.ncbi.output.Hsp;
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
        if(h==null){
            return 0;
        }
        if(h.getHitHsps()==null){
            return 0;
        }
        for(Hsp hsp:h.getHitHsps().getHsp()){
            if(hsp.getHspIdentity()==null){
                return 0;
            }
            if(hsp.getHspAlignLen()==null){
                return 0;
            }
        }

        double identities = 0;
        for(Hsp hsp:h.getHitHsps().getHsp()){
            identities+=Double.parseDouble(hsp.getHspIdentity());
        }
        double alingnmentLenght = 0;
        for(Hsp hsp:h.getHitHsps().getHsp()){
            alingnmentLenght+=Double.parseDouble(hsp.getHspAlignLen());
        }
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
        if(h==null){
            return 0;
        }
        if(h.getHitHsps()==null){
            return 0;
        }
        for(Hsp hsp:h.getHitHsps().getHsp()){
            if(hsp.getHspQueryTo()==null){
                return 0;
            }
            if(hsp.getHspQueryFrom()==null){
                return 0;
            }
        }
        int partLength=0;
        for(Hsp hsp:h.getHitHsps().getHsp()){
            partLength+=1+ Integer.parseInt(hsp.getHspQueryTo())-Integer.parseInt(hsp.getHspQueryFrom());
        }
        return partLength * 100 / queryLength;
    }
}
