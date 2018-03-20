package de.yfu.intranet.methodendb.models;

import static de.yfu.intranet.methodendb.models.SeminarType.SEMINAR_TYPE_TABLE;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.MoreObjects;

@Entity
@Table(name=SEMINAR_TYPE_TABLE)
public class SeminarType implements Serializable {
	
	private static final long serialVersionUID = -7282902519527527587L;
	public static final String SEMINAR_TYPE_TABLE = "st_seminar_type";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="st_id")
	private UUID id;
	
	@Column(name="st_name")
	private String name;
	
	@OneToMany(mappedBy="seminarType", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<SeminarGoal> seminarGoals;

	public SeminarType() {
		super();
	}


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

	public Set<SeminarGoal> getSeminarGoals() {
		return seminarGoals;
	}

	public void setSeminarGoals(Set<SeminarGoal> seminarGoals) {
		this.seminarGoals = seminarGoals;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SeminarType that = (SeminarType) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name) &&
				Objects.equals(seminarGoals, that.seminarGoals);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, seminarGoals);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.add("seminarGoals", seminarGoals)
				.toString();
	}
}