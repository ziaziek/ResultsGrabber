package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Expression;

import database.DataDealer;
import errors.DataDealerReadException;

public class MatchesExt extends Matches{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6032721185185004576L;

	private List<Games> matchGames = new ArrayList<Games>();

	public List<Games> getMatchGames() {
		return matchGames;
	}

	public void setMatchGames(List<Games> matchGames) {
		this.matchGames = matchGames;
	}
	
	
	public static List<Matches> findByUniqueConstraints(DataDealer d, Calendar cdate, String city){
		return d.getSession().createCriteria(Matches.class).add(Expression.eq("Date", cdate)
				).add(Expression.eq("City", city)).list();
	}
	
	public static String toString(Matches m){
		StringBuilder str = new StringBuilder();
		str.append(new SimpleDateFormat("dd-MM-yyyy").format(m.getDate().getTime()));
		str.append(",");
		str.append(m.getCountry());
		str.append(",");
		str.append(m.getCity());
		str.append(",");
		return str.toString();
		
	}
        
        /**
         * Produces an array of data of games for the given match id. This function contains implementation
         * of building the data matrix
         * @param mid id of the match
         * @param columns number of columns
         * @return an array of data
         */
        public static Object[][] getGamesForMatch(int mid) throws DataDealerReadException{
            List<Games> games =new DataDealer().readConditionedData(Games.class, "idMatches="+mid);
        Object[][] data = new Object[games.size()][5];

        int i=0;
        for(Games g: games){
            data[i][0]=g.getIdMatches();
            data[i][1] = PlayersHelper.toFullName((Players)(new DataDealer().readData(Players.class, g.getIdPlayers())));
            data[i][2] = PlayersHelper.toFullName((Players)(new DataDealer().readData(Players.class, g.getIdOponents())));
            data[i][3]= GamesResults.translate(g.getResult());
            data[i][4] = String.format("%.2f", g.getAvgPointDiff());
            i++;
        }
        return data;
        }
}
