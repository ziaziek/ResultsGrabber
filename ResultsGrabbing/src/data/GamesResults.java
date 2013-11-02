package data;

import java.util.HashMap;
import java.util.Map;

public enum GamesResults {
	WIN, LOOSE, DRAW;
	
        protected static Map<GamesResults, String> resultsMap = new HashMap<GamesResults, String>(){
            {put(WIN, "WIN");}
            {put(LOOSE, "LOOSE");}
            {put(DRAW, "DRAW");}
        };
        
	public static  GamesResults parse(String inStr){
            if(resultsMap.containsValue(inStr)){
                for(Map.Entry<GamesResults, String> en: resultsMap.entrySet()){
                    if(en.getValue().equals(inStr)){
                        return en.getKey();
                    }
                }
                return null;
            } else {
                return null;
            }
	}
        
        public static String translate(GamesResults gr){
            return resultsMap.get(gr);
        }
}
