/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.filters;

import data.Games;
import data.GamesExtendedHelper;
import java.util.Calendar;
import logsreader.misc.Filter;

/**
 *
 * @author Przemo
 */
        public class GamesDateFilter extends Filter<Games>{

        private Calendar c;
        
        public GamesDateFilter(Calendar c){
            this.c=c;
        }
        
        @Override
        public boolean passes(Games object) {
            return GamesExtendedHelper.getGameDate(object).compareTo(c)>=0;
        }
        
    }
