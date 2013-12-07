/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stats;

import data.Games;
import data.GamesExtendedHelper;
import data.GamesResults;
import data.Matches;
import data.Players;
import data.PlayersHelper;
import data.filters.GamesFilters;
import database.DataDealer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.ComparatorUtils;

/**
 *
 * @author Przemo
 */
public class PlayerStats {
    
    private Players p = null;
    
    private List<Games> games =null, calcGamesList=null;
    
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
        calcGamesList=tempG;
    }
    
    
    public Calendar getFirstDate(){ //TODO: sort the collection of games as these functions work wrongly
        if(calcGamesList!=null && !calcGamesList.isEmpty()){
            return GamesExtendedHelper.getGameDate(calcGamesList.get(0));
        } else {
            return null;
        }
    }
    
    public Calendar getLastDate(){
        if(calcGamesList!=null && !calcGamesList.isEmpty()){
            return GamesExtendedHelper.getGameDate(calcGamesList.get(calcGamesList.size()-1));
        } else {
            return null;
        }
    }
   
    public void reset(){
       calcGamesList=games;
    }
    
    public PlayerStats(Players p){
        this.p=p;
        games = buildGames();
        calcGamesList=games;
    }
    
    public String getPlayersName(){
        return PlayersHelper.toFullName(p);
    }
    
    public int getNumberOfGames(){
        return calcGamesList.size();
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
        if (!calcGamesList.isEmpty()) {
            double savg = 0;
            for (Games g : calcGamesList) {
                savg += g.getAvgPointDiff();
            }
            return savg / calcGamesList.size();
        } else {
            return 0;
        }     
    }
    
    private int getNumberOfFilteredGames(GamesResults gr){
        int q = 0;
        for(Iterator<Games> it =  GamesFilters.filterByResults(gr).filter(calcGamesList.iterator());
                it.hasNext();){
            q++;
            it.next();
        }
        return q;
    }
    
    private List<Games> buildGames(){
        DataDealer d = new DataDealer();
        List<Matches> matches = d.readConditionedData(Matches.class, "id>0");
        List<Games> ret = d.readConditionedData(Games.class, "idPlayers="+p.getId());
        Collections.sort(ret, new GamesByIdMatchesComparator(matches));
        return ret;
    } 

    private Matches findMatchById(List<Matches> MatchList, int id){
        int i = 0;
        while(i<MatchList.size() && MatchList.get(i).getId()!=id){
            i++;
        }
        if(i>=MatchList.size()){
            return null;
        } else {
            return MatchList.get(i);
        }
    }
    
    private class GamesByIdMatchesComparator implements Comparator<Games>{

        private List<Matches> matches = null;
        
        public GamesByIdMatchesComparator(List<Matches> m){
            matches = m;
        }
        
        @Override
        public int compare(Games t, Games t1) {
            if(matches!=null){
                return findMatchById(matches, t.getIdMatches()).getDate().compareTo(findMatchById(matches, t1.getIdMatches()).getDate());
            }
            return 0;
        }
    }
    
    private class MatchesByDateComparator implements Comparator<Matches> {

        public MatchesByDateComparator() {
        }

        @Override
        public int compare(Matches t, Matches t1) {
            return t.getDate().compareTo(t1.getDate());
        }
    }

}
