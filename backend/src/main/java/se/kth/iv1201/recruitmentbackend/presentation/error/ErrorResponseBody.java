package se.kth.iv1201.recruitmentbackend.presentation.error;

import se.kth.iv1201.recruitmentbackend.domain.Application;

public class ErrorResponseBody {
	private String logRef;
	private String message;
	private int code;
	private Object body;

	/**
	 * Creates an instance of <code>ErrorResponse</code>.
	 * 
	 * @param logRef      The error.
	 * @param message     The error message.
	 * @param application the body.
	 */
	ErrorResponseBody(String logRef, String message, int code, Application application) {
		this.logRef = logRef;
		this.message = message;
		this.code = code;
		this.body = application;
	}

	/**
	 * @return the error.
	 */
	public String getLogRef() {
		return this.logRef;
	}

	/**
	 * @return the error message.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @return the error code.
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * @return the body, which should be an {@link Application}.
	 */
	public Object getApplication() {
		return this.body;
	}
}
