package se.kth.iv1201.recruitmentbackend.presentation.error;

public class ErrorResponse {
	private String logRef;
	private String message;

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
}
