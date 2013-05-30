package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.text.DateFormatter;

import logging.LogPc;

public class PlayersHelper extends Players {

	public static final int YEAR_BEGINNING_OF_ALL = 1970;

	public static int calculateAge(Players p, Calendar cdate) {
		Calendar cret = Calendar.getInstance();
		if (cdate != null && p.getBirthday()!=null) {
			cret.setTimeInMillis(cdate.getTimeInMillis()
					- p.getBirthday().getTimeInMillis());
			return cret.get(Calendar.YEAR) - YEAR_BEGINNING_OF_ALL;
		} else {
			LogPc.Pclog.warn("Age could not be calculated!");
			return 0;
		}

	}

	public String toString() {
		return PlayersHelper.toString(this);

	}

	public static String toString(Players p) {
		StringBuilder retStr = new StringBuilder();
		retStr.append(p.getFirstName());
		retStr.append(",");
		retStr.append(p.getLastName());
		retStr.append(",");
		try {
			retStr.append(new SimpleDateFormat("dd-MM-yyyy").format(p.getBirthday()
				.getTime()));
		} catch(Exception ex){
			LogPc.Pclog.error(ex.getMessage(), ex);
			retStr.append("00-00-0000");
		}
		retStr.append(",");
		retStr.append(p.getCountry());
		return retStr.toString();

	}
}
