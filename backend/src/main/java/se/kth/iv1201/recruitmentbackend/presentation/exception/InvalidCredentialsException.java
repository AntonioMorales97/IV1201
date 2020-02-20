package se.kth.iv1201.recruitmentbackend.presentation.exception;

/**
 * Represents the exception when invalid credentials are presented.
 *
 */
public class InvalidCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int code = 4;

	/**
	 * Creates an instance of this exception whenever authentication with wrong credentials occurs.
	 * 
	 * @param msg The message holding information why the exception was thrown.
	 */
	public InvalidCredentialsException(String msg) {
		super(msg);
	}

	/**
	 * @return the code of this exception.
	 */
	public int getCode() {
		return this.code;
	}
}
