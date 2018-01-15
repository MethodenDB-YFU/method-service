package de.yfu.intranet.methodendb.models;

import static de.yfu.intranet.methodendb.models.Attachment.ATTACHMENT_TABLE;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name=ATTACHMENT_TABLE)
public class Attachment implements Serializable {

	private static final long serialVersionUID = -8552270336349098490L;
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
	public static final String ATTACHMENT_TABLE = "ma_attachment";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ma_id")
	private UUID id;
	
	@Column(name="ma_content")
	private String content;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@Column(name="ma_created_at")
	private Date createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@Column(name="ma_modified_at")
	private Date modifiedAt;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ma_created_by")
	private User createdBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ma_modified_by")
	private User modifiedBy;


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
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
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		modifiedAt = new Date();
	}
}