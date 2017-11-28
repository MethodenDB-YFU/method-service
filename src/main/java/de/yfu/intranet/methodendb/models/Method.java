package de.yfu.intranet.methodendb.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import de.yfu.intranet.methodendb.dtos.MethodCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodUpdateRequestDTO;

@Entity
@Table(name="method")
public class Method implements Serializable {

	private static final long serialVersionUID = 1104549652651553182L;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";

	@Id
	@SequenceGenerator(name="method_id_seq", sequenceName="method_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="method_id_seq")
	private int id;
	
	private String title;
	
	private String content;
	
	@OneToMany(mappedBy="method", fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST})
	private Set<Attachment> attachments;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE})
	@JoinTable(
			name="method_method_level",
			joinColumns=@JoinColumn(name="method_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="method_level_id", referencedColumnName="id"))
	private Set<MethodLevel> methodLevels;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade= {CascadeType.MERGE})
	@JoinTable(
			name="method_method_type",
			joinColumns=@JoinColumn(name="method_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="method_type_id", referencedColumnName="id"))
	private Set<MethodType> methodTypes;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="seminar_type_id")
	private SeminarType seminarType;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	private Date createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	private Date modifiedAt;
	
	private int createdBy;

	public Method() {
		super();
	}

	public Method(int id, String title, String content, Set<MethodLevel> methodLevels, Set<MethodType> methodTypes,
			SeminarType seminarType, Date createdAt, Date modifiedAt, int createdBy) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.methodLevels = methodLevels;
		this.methodTypes = methodTypes;
		this.seminarType = seminarType;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.createdBy = createdBy;
	}
	
	public Method(MethodCreateRequestDTO methodCreateRequestDTO) {
		this.title = methodCreateRequestDTO.getTitle();
		this.content = methodCreateRequestDTO.getContent();
		this.methodLevels = methodCreateRequestDTO.getMethodLevels();
		this.methodTypes = methodCreateRequestDTO.getMethodTypes();
		this.seminarType = methodCreateRequestDTO.getSeminarType();
		this.createdAt = methodCreateRequestDTO.getCreatedAt();
		this.modifiedAt = methodCreateRequestDTO.getModifiedAt();
		this.createdBy = methodCreateRequestDTO.getCreatedBy();
		//this.modifiedBy = methodCreateRequestDTO.getModifiedBy();
	}
	
	public Method(int methodId, MethodUpdateRequestDTO updateRequest) {
		this.id = methodId;
		this.title = updateRequest.getTitle();
		this.content = updateRequest.getContent();
		this.methodLevels = updateRequest.getMethodLevels();
		this.methodTypes = updateRequest.getMethodTypes();
		this.seminarType = updateRequest.getSeminarType();
		this.createdAt = updateRequest.getCreatedAt();
		this.modifiedAt = updateRequest.getModifiedAt();
		this.createdBy = updateRequest.getCreatedBy();
//		this.modifiedBy = updateRequest.getModifiedBy();
		
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
}