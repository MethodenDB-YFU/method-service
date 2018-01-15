package de.yfu.intranet.methodendb.models;

import static de.yfu.intranet.methodendb.models.MethodType.METHOD_TYPE_TABLE;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name=METHOD_TYPE_TABLE)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodType {
	
	public static final String METHOD_TYPE_TABLE = "mt_method_type";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="mt_id")
	private UUID id;
	
	@Column(name="mt_name")
	private String name;
	
	@Column(name="mt_description")
	private String description;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}