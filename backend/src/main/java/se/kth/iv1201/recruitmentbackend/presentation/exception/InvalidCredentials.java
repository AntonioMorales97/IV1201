package se.kth.iv1201.recruitmentbackend.presentation.exception;

public class InvalidCredentials extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final int code =4 ; 
	/**
	 * Creates an instance of this exception whenever a Application could not be found.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public InvalidCredentials(String msg) {
		super(msg);
		
		}
		public int getCode() {
			return this.code;
		}
}
