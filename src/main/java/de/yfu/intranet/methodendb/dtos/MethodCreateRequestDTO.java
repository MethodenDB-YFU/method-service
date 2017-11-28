package de.yfu.intranet.methodendb.dtos;

import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.models.User;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodCreateRequestDTO {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	
	private String title;
	
	private String content;
	
	private Set<MethodLevel> methodLevels;
	
	private Set<MethodType> methodTypes;
	
	private SeminarType seminarType;
	
	private User createdBy;
	
	private Date createdAt;
	
	private User modifiedBy;
	
	private Date modifiedAt;
	
	@JsonCreator
	public MethodCreateRequestDTO(
			@JsonProperty(value="title") String title, 
			@JsonProperty(value="content", required=false) String content, 
			@JsonProperty(value="method_levels") Set<MethodLevel> methodLevels,
			@JsonProperty(value="method_types") Set<MethodType> methodTypes, 
			@JsonProperty(value="seminar_type") SeminarType seminarType, 
			@JsonProperty(value="created_by") User createdBy, 
			@JsonProperty(value="created_at", required=false) String createdAt, 
			@JsonProperty(value="modified_at", required=false) String modifiedAt,
			@JsonProperty(value="modified_by", required=false) User modifiedBy) throws ParseException {
		super();
		this.title = title;
		this.content = content;
		this.methodLevels = methodLevels;
		this.methodTypes = methodTypes;
		this.seminarType = seminarType;
		this.createdBy = createdBy;
		this.createdAt = createdAt == null ? new Date() : formatter.parse(createdAt);
		this.modifiedAt = modifiedAt == null ? new Date() : formatter.parse(modifiedAt);
		this.modifiedBy = modifiedBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<MethodLevel> getMethodLevels() {
		return methodLevels;
	}

	public void setMethodLevels(Set<MethodLevel> methodLevels) {
		this.methodLevels = methodLevels;
	}

	public Set<MethodType> getMethodTypes() {
		return methodTypes;
	}

	public void setMethodTypes(Set<MethodType> methodTypes) {
		this.methodTypes = methodTypes;
	}

	public SeminarType getSeminarType() {
		return seminarType;
	}

	public void setSeminarType(SeminarType seminarType) {
		this.seminarType = seminarType;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public String toString() {
		return "MethodCreateRequestDTO [title=" + title + ", content=" + content + ", methodLevels=" + methodLevels
				+ ", methodTypes=" + methodTypes + ", seminarType=" + seminarType + ", createdBy=" + createdBy
				+ ", createdAt=" + createdAt + ", modifiedBy=" + modifiedBy + ", modifiedAt=" + modifiedAt + "]";
	}
}