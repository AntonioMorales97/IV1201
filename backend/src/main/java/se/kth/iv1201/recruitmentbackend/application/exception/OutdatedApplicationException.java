package se.kth.iv1201.recruitmentbackend.application.exception;

import se.kth.iv1201.recruitmentbackend.domain.Application;

/**
 * Class representing an exception that is thrown when an
 * <code>Application</code> is outdated, i.e. is an old version.
 *
 */
public class OutdatedApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int code = 7;
	private final Application application;

	/**
	 * Creates an instance of this exception class.
	 * 
	 * @param msg Information why the exception was thrown.
	 * @param app The new updated <code>Application</code>.
	 */
	public OutdatedApplicationException(String msg, Application app) {
		super(msg);
		this.application = app;
	}

	/**
	 * @return the exception code.
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * @return the <code>Application</code> in this exception.
	 */
	public Application getApplication() {
		return this.application;
	}
}
