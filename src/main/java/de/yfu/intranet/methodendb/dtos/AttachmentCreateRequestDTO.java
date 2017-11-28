package de.yfu.intranet.methodendb.dtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.yfu.intranet.methodendb.models.Method;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachmentCreateRequestDTO {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	
	private Method method;
	
	private String content;
	
	private Date createdAt;
	
	private String createdBy;	
	
	@JsonCreator
	public AttachmentCreateRequestDTO(
			@JsonProperty(value="method", required=false) Method method, 
			@JsonProperty(value="content") String content, 
			@JsonProperty(value="createdAt", required=false) String createdAt, 
			@JsonProperty(value="createdBy") String createdBy) throws ParseException {
		super();
		this.method = method;
		this.content = content;
		this.createdAt = createdAt == null ? new Date() : formatter.parse(createdAt);
		this.createdBy = createdBy;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}