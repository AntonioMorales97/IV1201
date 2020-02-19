package se.kth.iv1201.recruitmentbackend.application.exception;

public class PersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int code = 8;

	/**
	 * Creates an instance of this exception whenever a Person was not found.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public PersonNotFoundException(String msg) {
		super(msg);
	}

	public int getCode() {
		return this.code;
	}
}
