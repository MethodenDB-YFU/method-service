package de.yfu.intranet.methodendb.dtos;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.google.common.base.MoreObjects;
import de.yfu.intranet.methodendb.models.User;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachmentResource {
	
	private UUID id;
	@NotNull
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private User createdBy;	
	private User modifiedBy;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttachmentResource that = (AttachmentResource) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(content, that.content) &&
				Objects.equals(createdAt, that.createdAt) &&
				Objects.equals(modifiedAt, that.modifiedAt) &&
				Objects.equals(createdBy, that.createdBy) &&
				Objects.equals(modifiedBy, that.modifiedBy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, createdAt, modifiedAt, createdBy, modifiedBy);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("content", content)
				.add("createdAt", createdAt)
				.add("modifiedAt", modifiedAt)
				.add("createdBy", createdBy)
				.add("modifiedBy", modifiedBy)
				.toString();
	}
}
