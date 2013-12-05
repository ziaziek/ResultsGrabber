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
        public class GamesDateResultsFilter extends Filter<Games>{

        private Calendar c;
        private GamesResults gr;
        
        public GamesDateResultsFilter(Calendar c, GamesResults gr){
            this.c=c;
            this.gr=gr;
        }
        
        @Override
        public boolean passes(Games object) {
            return object.getResult()==gr &&  GamesExtendedHelper.getGameDate(object).compareTo(c)>=0;
        }
    
}
