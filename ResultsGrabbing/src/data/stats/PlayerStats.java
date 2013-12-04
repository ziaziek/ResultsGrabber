/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stats;

import data.Games;
import data.GamesResults;
import data.Players;
import data.PlayersHelper;
import database.DataDealer;
import java.util.Iterator;
import java.util.List;
import logsreader.misc.Filter;

/**
 *
 * @author Przemo
 */
public class PlayerStats {
    
    private Players p = null;
    
    private List<Games> games =null;
    
    public PlayerStats(Players p){
        this.p=p;
        games = buildGames();
    }
    
    public String getPlayersName(){
        return PlayersHelper.toFullName(p);
    }
    
    public int getNumberOfGames(){
        return games.size();
    }
    
    public int getNumberOfGamesWon(){
        return getNumberOfFilteredGames(GamesResults.WIN);
    }
    
    public int getNumberOfGamesLost(){
        return getNumberOfFilteredGames(GamesResults.LOOSE);
    }
    
    
    public double getPercentageOfGamesWon(){
      int ng = getNumberOfGames();
      if(ng!=0){
          return 100* getNumberOfGamesWon()/(double)ng;
      } else {
          return 0;
      }
    }
    
    public double getPercentageOfGamesLost(){
        int ng = getNumberOfGames();
      if(ng!=0){
          return 100* getNumberOfGamesLost()/(double)ng;
      } else {
          return 0;
      }
    }
    
    public double getAveragePoints(){
        if (!games.isEmpty()) {
            double savg = 0;
            for (Games g : games) {
                savg += g.getAvgPointDiff();
            }
            return savg / games.size();
        } else {
            return 0;
        }     
    }
    
    private int getNumberOfFilteredGames(GamesResults gr){
        int q = 0;
        for(Iterator<Games> it = new GamesFilter(gr).filter(games.iterator());
                it.hasNext();){
            q++;
            it.next();
        }
        return q;
    }
    
    private List<Games> buildGames(){
        List<Games> ret = new DataDealer().readConditionedData(Games.class, "idPlayers="+p.getId());
        return ret;
    } 
    
    private class GamesFilter extends Filter<Games>{

        private GamesResults result;
        
        public  GamesFilter(GamesResults gr){
            result =  gr;
        }
        
        @Override
        public boolean passes(Games g) {
            return g.getResult()==result;
        }
        
    }
}
