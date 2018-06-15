package de.yfu.intranet.methods.data.domain;

import static de.yfu.intranet.methods.data.domain.Method.METHOD_TABLE;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name=METHOD_TABLE)
public class Method {

	public static final String METHOD_TABLE = "mm_method";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="mm_id")
	private UUID id;
	
	@Column(name="mm_title")
	private String title;
	
	@Column(name="mm_content")
	private String content;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ma_method_id", referencedColumnName = "mm_id")
	private Set<Attachment> attachments;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(
			name="mm_method_method_level",
			joinColumns=@JoinColumn(name="mm_method_id", referencedColumnName="mm_id"),
			inverseJoinColumns=@JoinColumn(name="mm_method_level_id", referencedColumnName="ml_id"))
	private Set<MethodLevel> methodLevels;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(
			name="mm_method_method_type",
			joinColumns=@JoinColumn(name="mm_method_id", referencedColumnName="mm_id"),
			inverseJoinColumns=@JoinColumn(name="mm_method_type_id", referencedColumnName="mt_id"))
	private Set<MethodType> methodTypes;
	/*
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "sg_method_id", referencedColumnName = "mm_id")
	*/
	@ElementCollection
	@CollectionTable(
			name = "sg_seminar_goal",
			joinColumns = @JoinColumn(name = "sg_method_id")
	)
	private Set<UUID> seminarGoals;

	@Column(name = "mm_seminar_type_id")
	private UUID seminarType;

	@Column(name="mm_created_at")
	private LocalDateTime createdAt;
	
	@Column(name="mm_modified_at")
	private LocalDateTime modifiedAt;
	
	@ManyToOne
	@JoinColumn(name="mm_created_by")
	private User createdBy;
	
	@ManyToOne
	@JoinColumn(name="mm_modified_by")
	private User modifiedBy;

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public Set<UUID> getSeminarGoals() {
		return seminarGoals;
	}

	public void setSeminarGoals(Set<UUID> seminarGoals) {
		this.seminarGoals = seminarGoals;
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

	public UUID getSeminarType() {
		return seminarType;
	}

	public void setSeminarType(UUID seminarType) {
		this.seminarType = seminarType;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		modifiedAt =  LocalDateTime.now();
	}
}