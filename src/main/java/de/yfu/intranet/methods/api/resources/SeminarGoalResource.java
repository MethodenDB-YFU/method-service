package de.yfu.intranet.methods.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeminarGoalResource {

    private UUID id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private UUID seminarType;

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

    public UUID getSeminarType() {
        return seminarType;
    }

    public void setSeminarType(UUID seminarType) {
        this.seminarType = seminarType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeminarGoalResource that = (SeminarGoalResource) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(seminarType, that.seminarType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, seminarType);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("seminarType", seminarType)
                .toString();
    }
}
