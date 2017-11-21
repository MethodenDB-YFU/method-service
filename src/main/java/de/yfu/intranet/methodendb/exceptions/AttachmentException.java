package de.yfu.intranet.methodendb.exceptions;

import org.springframework.http.HttpStatus;

public class AttachmentException extends Exception {

	private static final long serialVersionUID = 1651539900222924833L;

	private HttpStatus httpStatus;
	
	Integer code;
	String source;
	
	public AttachmentException(String message, HttpStatus httpStatus) {
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
