package de.yfu.intranet.methodendb.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import de.yfu.intranet.methodendb.dtos.AttachmentCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.AttachmentUpdateRequestDTO;

@Entity
@Table(name="attachment")
public class Attachment implements Serializable {

	private static final long serialVersionUID = -8552270336349098490L;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
	
	@Id
	@SequenceGenerator(name="attachment_id_seq", sequenceName="attachment_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="attachment_id_seq")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="method_id")
	@JsonIgnore
	private Method method;
	
	private String content;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	private Date createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	private Date modifiedAt;
	
	private String createdBy;
	
	private String modifiedBy;

	public Attachment() {
		super();
	}

	public Attachment(Method method, String content, Date createdAt, Date modifiedAt, String createdBy, String modifiedBy) {
		super();
		this.method = method;
		this.content = content;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	
	public Attachment(AttachmentCreateRequestDTO createRequest) {
		this.method = createRequest.getMethod();
		this.content = createRequest.getContent();
		this.createdAt = createRequest.getCreatedAt();
		this.createdBy = createRequest.getCreatedBy();
	}
	
	public Attachment(int attachmentId, AttachmentUpdateRequestDTO updateRequest) {
		this.id = attachmentId;
		this.method = updateRequest.getMethod();
		this.content = updateRequest.getContent();
		this.createdAt = updateRequest.getCreatedAt();
		this.createdBy = updateRequest.getCreatedBy();
		this.modifiedAt = updateRequest.getModifiedAt();
		this.modifiedBy = updateRequest.getModifiedBy();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}