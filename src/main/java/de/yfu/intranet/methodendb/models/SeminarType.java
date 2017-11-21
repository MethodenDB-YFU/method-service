package de.yfu.intranet.methodendb.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="seminar_type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeminarType implements Serializable {
	
	private static final long serialVersionUID = -7282902519527527587L;
	
	@Id
	@SequenceGenerator(name="seminar_type_id_seq", sequenceName="seminar_type_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seminar_type_id_seq")
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="seminarType")
	private List<Method> methods;

	public SeminarType() {
		super();
	}

	public SeminarType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@JsonCreator
	public SeminarType(@JsonProperty("name") String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "SeminarType [id=" + id + ", name=" + name + "]";
	}
	
}