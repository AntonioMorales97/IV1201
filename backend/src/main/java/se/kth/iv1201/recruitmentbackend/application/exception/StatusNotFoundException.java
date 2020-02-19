package se.kth.iv1201.recruitmentbackend.application.exception;

/**
 * Class representing an exception that is thrown when a <code>Status</code>
 * could not be found.
 *
 */
public class StatusNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int code = 6;

	/**
	 * Creates an instance of this exception whenever a <code>Status</code> was not
	 * found.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public StatusNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * @return the exception code.
	 */
	public int getCode() {
		return this.code;
	}
}