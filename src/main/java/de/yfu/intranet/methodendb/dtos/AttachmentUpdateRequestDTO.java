package de.yfu.intranet.methodendb.dtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.yfu.intranet.methodendb.models.Method;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachmentUpdateRequestDTO {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	
	private Method method;
	
	private String content;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	private Date createdAt;
	
	private String createdBy;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	private Date modifiedAt;
	
	private String modifiedBy;
	
	@JsonCreator
	public AttachmentUpdateRequestDTO(
			@JsonProperty(value="method", required=false) Method method, 
			@JsonProperty(value="content") String content, 
			@JsonProperty(value="createdAt", required=false) String createdAt, 
			@JsonProperty(value="createdBy") String createdBy,
			@JsonProperty(value="modifiedAt", required=false) String modifiedAt,
			@JsonProperty(value="modifiedBy") String modifiedBy) throws ParseException {
		super();
		this.method = method;
		this.content = content;
		this.createdAt = createdAt == null ? new Date() : formatter.parse(createdAt);
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt == null ? new Date() : formatter.parse(modifiedAt);
		this.modifiedBy = modifiedBy;
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

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
