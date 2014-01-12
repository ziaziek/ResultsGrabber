/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stats;

/**
 *
 * @author Przemo
 */
public class OddsAssecor {
    
    /**
     * Calculates odds based on the probability of winning.
     * @param winProbability
     * @return odds, in decimal form, based on the probability of winning
     */
    public static double getProperOdds(double winProbability){
        if(winProbability>0){
            return 1/winProbability;
        } else {
            return 0;
        }
    }
    
    /**
     * Calculates the deviation of the given odds to the probability of winning
     * @param givenOdds
     * @param winProbability
     * @return deviation in percentage
     */
    public static double getOddsDeviation(double givenOdds, double winProbability){
        if (givenOdds!=0){
            return 100 *( 1-(getProperOdds(winProbability)/givenOdds));
        } else {
            return 0;
        }
    }
    
    /**
     * Formats the calculated deviation to percentage string.
     * @param givenOdds
     * @param winProbability
     * @return Formatted string for percentage
     */
    public static String getOddsDeviationAsString(double givenOdds, double winProbability){
        double r = getOddsDeviation(givenOdds, winProbability);
        return  String.format("%.2f", r) + " %";
    }
}
