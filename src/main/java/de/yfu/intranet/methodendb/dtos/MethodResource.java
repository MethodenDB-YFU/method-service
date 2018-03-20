package de.yfu.intranet.methodendb.dtos;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.MoreObjects;
import de.yfu.intranet.methodendb.models.*;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.ext.JodaDeserializers;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.provider.client.JacksonArrayOrStringDeserializer;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodResource {

	private UUID id;
	@NotNull
	private String title;
	@NotNull
	private String content;
	private Set<Attachment> attachments;
	@NotNull
	private Set<MethodLevel> methodLevels;
	@NotNull
	private Set<MethodType> methodTypes;
	private Set<SeminarGoal> seminarGoals;
	@NotNull
	private SeminarType seminarType;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private User createdBy;
	private User modifiedBy;

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	public Set<SeminarGoal> getSeminarGoals() {
		return seminarGoals;
	}

	public void setSeminarGoals(Set<SeminarGoal> seminarGoals) {
		this.seminarGoals = seminarGoals;
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public SeminarType getSeminarType() {
		return seminarType;
	}

	public void setSeminarType(SeminarType seminarType) {
		this.seminarType = seminarType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, content, attachments, methodLevels, 
				methodTypes, seminarGoals, seminarType, createdAt, modifiedAt, createdBy,
				modifiedBy);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodResource other = (MethodResource) obj;
		return Objects.equals(id, other.id) 
				&& Objects.equals(title, other.title) 
				&& Objects.equals(content, other.content) 
				&& Objects.equals(attachments, other.attachments)
				&& Objects.equals(methodLevels, other.methodLevels) 
				&& Objects.equals(methodTypes, other.methodTypes)
				&& Objects.equals(seminarGoals, other.seminarGoals)
				&& Objects.equals(seminarType, other.seminarType)
				&& Objects.equals(createdAt, other.createdAt) 
				&& Objects.equals(modifiedAt, other.modifiedAt)
				&& Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(modifiedBy, other.modifiedBy);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id)
				.add("title", title).add("content", content)
				.add("attachments", attachments).add("methodLevels", methodLevels)
				.add("methodTypes", methodTypes).add("seminarGoals", seminarGoals)
				.add("seminarType", seminarType).add("createdAt", createdAt)
				.add("modifiedAt", modifiedAt).add("createdBy", createdBy)
				.add("modifiedBy", modifiedBy).toString();
	}
}