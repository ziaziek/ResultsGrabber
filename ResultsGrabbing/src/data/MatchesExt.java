package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Expression;

import database.DataDealer;

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
		return d.getSession().createCriteria(Matches.class).add(Expression.eq("match_date", cdate.getTime())
				).add(Expression.eq("city", city)).list();
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
}
