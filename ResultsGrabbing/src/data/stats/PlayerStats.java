/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stats;

import data.Games;
import data.GamesExtendedHelper;
import data.GamesResults;
import data.Players;
import data.PlayersHelper;
import data.filters.GamesFilters;
import database.DataDealer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class PlayerStats {
    
    private Players p = null;
    
    private List<Games> games =null;
    
    private Calendar fromDate = null;

    public Calendar getFromDate() {
        return fromDate;
    }

    /**
     * Sets Date to which the results should be filtered. This method changes the inner games list on which all other operations are done.
     * To set it back to original list of all games, call @see reset
     * @param toDate 
     */
    public void setFromDate(Calendar fromDate) {
        this.fromDate = fromDate;
        List<Games> tempG = new ArrayList<>();
        for(Iterator<Games> it =  GamesFilters.filterFromDate(fromDate).filter(games.iterator());
                it.hasNext();){
            tempG.add(it.next());
        }
        games=tempG;
    }
    
    
    public Calendar getFirstDate(){
        if(games!=null && !games.isEmpty()){
            return GamesExtendedHelper.getGameDate(games.get(0));
        } else {
            return null;
        }
    }
    
    public Calendar getLastDate(){
        if(games!=null && !games.isEmpty()){
            return GamesExtendedHelper.getGameDate(games.get(games.size()-1));
        } else {
            return null;
        }
    }
   
    public void reset(){
       buildGames();
    }
    
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
        for(Iterator<Games> it =  GamesFilters.filterByResults(gr).filter(games.iterator());
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

}
