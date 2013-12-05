package data;

import database.DataDealer;
import errors.DataDealerReadException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.LogPc;

public class GamesExtendedHelper extends Games {

	private String playerName;
	private String oponentName;

	public String getOponentName() {
		return oponentName;
	}

	public void setOponentName(String oponentName) {
		this.oponentName = oponentName;
	}

        public static Calendar getGameDate(Games g){
            try {
                return ((Matches)(new DataDealer().readData(Matches.class, g.getIdMatches()))).getDate();
            } catch (DataDealerReadException ex) {
                Logger.getLogger(GamesExtendedHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
	public static String toString(Games g){
		StringBuilder str = new StringBuilder();
		str.append(g.getAge());
		str.append(",");
		if(g instanceof GamesExtendedHelper){
			str.append(((GamesExtendedHelper)g).getOponentName());
			str.append(",");
		}
		str.append(g.getResult());
		str.append(",");
		str.append(g.getAvgPointDiff());
		
		return str.toString();
	}
	public static double translateAverageDiffInPoints(String elementContents) {
		elementContents = elementContents.substring(2);
		double avgRes = 0.0;
		try{
			String results[] = elementContents.split(",");
		if (results.length > 0) {
			for (String res : results) {
				res = res.trim();
				String[] points = res.split("-");
                                try {
                                    if (points.length == 2) {
					avgRes += parseDouble(points[0]) - parseDouble(points[1]);
				} else {
					avgRes += parseDouble(points[0]);
				}
                                } catch(NumberFormatException ex){
                                    LogPc.Pclog.error(ex.getMessage(), ex);
                                }
				
			}
			avgRes = avgRes / results.length;
		}
		} catch(Exception ex){
			LogPc.Pclog.error(ex.getMessage(), ex);
		}
		return avgRes;
	}

	private static Double parseDouble(String dstr) {
		Double r = 0.0;
			if (dstr.contains("(")) {
				dstr = dstr.substring(0, dstr.indexOf("("));
			}
			r = Double.parseDouble(dstr);

		return r;
	}
}
