package se.kth.iv1201.recruitmentbackend.application.exception;

import se.kth.iv1201.recruitmentbackend.domain.Application;

public class OutdatedApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int code =7;
	private final Application application; 
	
	
	public OutdatedApplicationException(String msg, Application app) {
		super(msg);
		this.application=app;
		
		}
		public int getCode() {
			return this.code;
		}
		public Application getApplication() {
			return this.application;
		}
}
