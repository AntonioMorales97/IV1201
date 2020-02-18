package se.kth.iv1201.recruitmentbackend.presentation.error;

import se.kth.iv1201.recruitmentbackend.domain.Application;

/**
 * Returns an error response to the client. 
 *
 */
public class ErrorResponse {
	private String logRef;
	private String message;
	private int code;
	private Application application;

	/**
	 * Creates an instance of <code>ErrorResponse</code>.
	 * 
	 * @param logRef The error.
	 * @param message The error message.
	 */
	ErrorResponse(String logRef, String message) {
		this.logRef = logRef;
		this.message = message;
	}
	ErrorResponse(String logRef, String message, int code) {
		this.logRef = logRef;
		this.message = message;
		this.code = code;
	}
	ErrorResponse(String logRef, String message, int code, Application application) {
		this.logRef = logRef;
		this.message = message;
		this.code = code;
		this.application = application;
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
	public int getCode() {
		return this.code;
	}
	public Application getApplication() {
		return this.application;
	}
}
