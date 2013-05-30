package data.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.metamodel.domain.Attribute;

import data.Games;
import data.GamesExtendedHelper;
import data.Matches;
import data.MatchesExt;
import data.Players;
import data.PlayersHelper;
import data.interfaces.IData;
import data.interfaces.IFilter;
import data.interfaces.ISample;

public class GrabbedTextData implements IData {

	private List<ISample> samples = new ArrayList<>();

	public static final String PLAYERS_TAG = "Player:\n";
	public static final String MATCHES_TAG = "Match:\n";
	public static final String GAMES_TAG = "Game:\n";
	
	@Override
	public Iterable process(IFilter filter, Iterable it) {
		return null;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValues(Attribute attr) {
		StringBuilder str = new StringBuilder();
		for (ISample s : samples) {
			if (s instanceof Players) {
				str.append(PLAYERS_TAG);
				str.append(PlayersHelper.toString((Players) s));
				str.append("\n");
			} else if (s instanceof Matches) {
				str.append(MATCHES_TAG);
				str.append((MatchesExt.toString((Matches) s)));
				str.append("\n");
				if (s instanceof MatchesExt) {
					MatchesExt m = (MatchesExt) s;
					for (Games g : m.getMatchGames()) {
						str.append(GAMES_TAG);
						str.append(GamesExtendedHelper.toString((Games) g));
						str.append("\n");
					}
				}
			}
		}
		return str.toString();
	}

	@Override
	public void Load(Object v) {
		if (v instanceof ArrayList && ((ArrayList) v).get(0) instanceof ISample) {
			samples = (ArrayList) v;
		}

	}

}
