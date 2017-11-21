package de.yfu.intranet.methodendb.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodLevelUpdateRequestDTO {
	
	private String name;
	
	private String description;
	
	@JsonCreator
	public MethodLevelUpdateRequestDTO(
			@JsonProperty(value="name") String name,
			@JsonProperty(value="description", required=false) String description) {
		this.name = name;
		this.description = description;
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
		return "MethodLevelUpdateRequestDTO [name=" + name + ", description=" + description + "]";
	}
}
