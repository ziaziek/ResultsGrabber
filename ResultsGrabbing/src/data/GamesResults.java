package data;

public enum GamesResults {
	WIN, LOOSE, DRAW;
	
	public static  GamesResults translate(String inStr){
		switch(inStr.substring(0, 1)){
		case "W":
			return WIN;
		case "L":
			return LOOSE;
		case "D":
			return DRAW;
			default:
				return null;
		}
	}
}
