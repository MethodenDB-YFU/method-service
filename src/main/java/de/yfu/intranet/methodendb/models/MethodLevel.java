package de.yfu.intranet.methodendb.models;

import static de.yfu.intranet.methodendb.models.MethodLevel.METHOD_LEVEL_TABLE;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name=METHOD_LEVEL_TABLE)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodLevel implements Serializable {

	private static final long serialVersionUID = -5685842420041777746L;
	public static final String METHOD_LEVEL_TABLE = "ml_method_level";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ml_id")
	private UUID id;
	
	@Column(name="ml_name")
	private String name;
	
	@Column(name="ml_description")
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