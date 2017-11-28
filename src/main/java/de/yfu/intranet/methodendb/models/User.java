package de.yfu.intranet.methodendb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="mdb_user")
@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements Serializable {
	
	private static final long serialVersionUID = -561697869584626674L;

	public enum Role {
		ADMIN, EDITOR, WRITER, READER
	}
	
	@Id
	@SequenceGenerator(name="mdb_user_id_seq", sequenceName="mdb_user_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mdb_user_id_seq")
	private int id;
	
	@ColumnTransformer(read = "role::varchar", write = "?::role")
	@Enumerated(EnumType.STRING)
	private Role role;

	public User() {
		super();
	}

	@JsonCreator
	public User(@JsonProperty("role") Role role) {
		super();
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + "]";
	}
	
	
}