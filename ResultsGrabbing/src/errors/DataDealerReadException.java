package errors;

public class DataDealerReadException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4158080568393494095L;

	private String mess = "DataDealerRead Exception. Data could not be read! "+ serialVersionUID;
	
	@Override
	public String getMessage() {
		return mess;
	}
}
