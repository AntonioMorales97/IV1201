package se.kth.iv1201.recruitmentbackend.application.exception;

/**
 * Class representing an exception that is thrown when a <code>Person</code>
 * could not be found.
 *
 */
public class PersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int code = 8;

	/**
	 * Creates an instance of this exception whenever a <code>Person</code> was not
	 * found.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public PersonNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * @return the exception code.
	 */
	public int getCode() {
		return this.code;
	}
}
