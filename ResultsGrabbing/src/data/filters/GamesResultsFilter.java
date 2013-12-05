/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.filters;

import data.Games;
import data.GamesResults;
import logsreader.misc.Filter;

/**
 *
 * @author Przemo
 */
public class GamesResultsFilter extends Filter<Games> {
    private GamesResults result;
        
        public  GamesResultsFilter(GamesResults gr){
            result =  gr;
        }
        
        @Override
        public boolean passes(Games g) {
            return g.getResult()==result;
        }
}
