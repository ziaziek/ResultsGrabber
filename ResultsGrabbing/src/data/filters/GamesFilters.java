/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.filters;

import data.Games;
import data.GamesExtendedHelper;
import data.GamesResults;
import java.util.Calendar;
import logsreader.misc.Filter;

/**
 *
 * @author Przemo
 */
public class GamesFilters {
    
    
    public static Filter<Games> filterByResults(GamesResults gr){
        return new GamesResultsFilter(gr);
    }
    
   public static Filter<Games> filterFromDate(Calendar fromWhen){
       return new GamesDateFilter(fromWhen);
   }
   
   public static Filter<Games> filterByDateAndResults(Calendar toWhen, GamesResults gr){
       return new GamesDateResultsFilter(toWhen, gr);
   }
  
}
