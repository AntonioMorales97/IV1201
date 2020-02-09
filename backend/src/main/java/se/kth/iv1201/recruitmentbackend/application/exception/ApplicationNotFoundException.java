package se.kth.iv1201.recruitmentbackend.application.exception;

public class ApplicationNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final int code; 
	/**
	 * Creates an instance of this exception whenever a <code>Customer</code> was not found.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public ApplicationNotFoundException(String msg, int code) {
		super(msg);
		this.code = code;
		}
		public int getCode() {
			return this.code;
		}
}