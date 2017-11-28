package de.yfu.intranet.methodendb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.yfu.intranet.methodendb.dtos.MethodTypeCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeUpdateRequestDTO;

@Entity
@Table(name="method_type")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodType implements Serializable {
	
	private static final long serialVersionUID = -62539155603047652L;

	@Id
	@SequenceGenerator(name="method_type_id_seq", sequenceName="method_type_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="method_type_id_seq")
	private int id;
	
	private String name;
	
	private String description;

	public MethodType() {
		super();
	}
	
	public MethodType(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public MethodType(MethodTypeCreateRequestDTO methodTypeCreateRequest) {
		this.name = methodTypeCreateRequest.getName();
		this.description = methodTypeCreateRequest.getDescription();
	}
	
	public MethodType(int methodTypeId, MethodTypeUpdateRequestDTO updateRequest) {
		this.id = methodTypeId;
		this.name = updateRequest.getName();
		this.description = updateRequest.getDescription();
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
		return "MethodType [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof MethodType)) {
			return false;
		}
		
		MethodType methodType = (MethodType) obj;
		
		return methodType.name.equals(name) &&
				methodType.description == description;
	}	
}