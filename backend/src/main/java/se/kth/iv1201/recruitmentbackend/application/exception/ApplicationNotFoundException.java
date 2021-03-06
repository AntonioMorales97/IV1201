package se.kth.iv1201.recruitmentbackend.application.exception;

/**
 * Class representing an exception that is thrown when an
 * <code>Application</code> was not found.
 *
 */
public class ApplicationNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int code = 5;

	/**
	 * Creates an instance of this exception whenever an <code>Application</code>
	 * could not be found.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public ApplicationNotFoundException(String msg) {
		super(msg);

	}

	/**
	 * @return the exception code.
	 */
	public int getCode() {
		return this.code;
	}
}