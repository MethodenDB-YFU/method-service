package de.yfu.intranet.methodendb.exceptions;

import org.springframework.http.HttpStatus;

public class SeminarException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	Integer code;
	String source;
	
	public SeminarException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public String getSource() {
		return this.source;
	}
}