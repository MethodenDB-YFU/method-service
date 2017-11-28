package de.yfu.intranet.methodendb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.yfu.intranet.methodendb.dtos.MethodLevelCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodLevelUpdateRequestDTO;

@Entity
@Table(name="method_level")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodLevel implements Serializable {

	private static final long serialVersionUID = -5685842420041777746L;

	@Id
	@SequenceGenerator(name="method_level_id_seq", sequenceName="method_level_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="method_level_id_seq")
	private int id;
	
	private String name;
	
	private String description;

	public MethodLevel() {
		super();
	}
	
	@JsonCreator
	public MethodLevel(
			@JsonProperty("name") String name, 
			@JsonProperty(value="description", required=false) String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public MethodLevel(MethodLevelCreateRequestDTO methodLevelCreateRequest) {
		this.name = methodLevelCreateRequest.getName();
		this.description = methodLevelCreateRequest.getDescription();
	}
	
	public MethodLevel(int id, MethodLevelUpdateRequestDTO methodLevelUpdateRequestDTO) {
		this.id = id;
		this.name = methodLevelUpdateRequestDTO.getName();
		this.description = methodLevelUpdateRequestDTO.getDescription();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "MethodLevel [id=" + id + ", name=" + name + ", description=" + description + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof MethodLevel)) {
			return false;
		}
		
		MethodLevel methodLevel = (MethodLevel) obj;
		
		return methodLevel.name.equals(name) &&
				methodLevel.description == description;
	}	
}