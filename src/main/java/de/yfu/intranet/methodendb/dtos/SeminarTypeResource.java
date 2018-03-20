package de.yfu.intranet.methodendb.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import de.yfu.intranet.methodendb.models.SeminarGoal;


import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeminarTypeResource {

    private UUID id;
    @NotNull
    private String name;
    private Set<SeminarGoal> seminarGoals;

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
        SeminarTypeResource that = (SeminarTypeResource) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(seminarGoals, that.seminarGoals);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("seminarGoals", seminarGoals)
                .toString();
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, seminarGoals);
    }
}
