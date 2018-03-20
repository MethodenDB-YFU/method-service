package de.yfu.intranet.methodendb.models;

import static de.yfu.intranet.methodendb.models.User.USER_TABLE;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name=USER_TABLE)
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
	
	public static final String USER_TABLE = "mu_user";

	public static enum Role {
		ADMIN, EDITOR, WRITER, READER
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="mu_id")
	private UUID id;
	
	@ColumnTransformer(read = "mu_role::varchar", write = "?::mdb_data.mr_role")
	@Enumerated(EnumType.STRING)
	@Column(name="mu_role")
	private Role role;

	public User() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("role", role).toString();
	}
}