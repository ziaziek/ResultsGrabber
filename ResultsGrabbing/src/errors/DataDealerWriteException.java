package errors;

public class DataDealerWriteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1400256318769092239L;
	
	private String mess = "DataWriterError: Data could not be saved! "+ serialVersionUID;
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return mess;
	}
	
	

}
