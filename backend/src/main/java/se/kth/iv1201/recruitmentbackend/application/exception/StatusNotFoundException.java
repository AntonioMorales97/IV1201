package se.kth.iv1201.recruitmentbackend.application.exception;

public class StatusNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final int code = 5; 
	/**
	 * Creates an instance of this exception whenever a Status was not found.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public StatusNotFoundException(String msg) {
		super(msg);
		
		}
		public int getCode() {
			return this.code;
		}
}