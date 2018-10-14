package de.yfu.intranet.methods.api.resources;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachmentResource {
	
	private UUID id;
	@NotBlank
	private String title;
	@NotNull
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

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
		return com.google.common.base.Objects.equal(id, that.id) &&
				com.google.common.base.Objects.equal(title, that.title) &&
				com.google.common.base.Objects.equal(content, that.content) &&
				com.google.common.base.Objects.equal(createdAt, that.createdAt) &&
				com.google.common.base.Objects.equal(modifiedAt, that.modifiedAt);
	}

	@Override
	public int hashCode() {
		return com.google.common.base.Objects.hashCode(id, title, content, createdAt, modifiedAt);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("title", title)
				.add("content", content)
				.add("createdAt", createdAt)
				.add("modifiedAt", modifiedAt)
				.toString();
	}
}
