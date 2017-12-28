package de.yfu.intranet.methodendb.dtos;

import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.SeminarType;
import jersey.repackaged.com.google.common.base.MoreObjects;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class SeminarGoalResource {

    private UUID id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private SeminarType seminarType;
    private Set<Method> methods;

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

    public SeminarType getSeminarType() {
        return seminarType;
    }

    public void setSeminarType(SeminarType seminarType) {
        this.seminarType = seminarType;
    }

    public Set<Method> getMethods() {
        return methods;
    }

    public void setMethods(Set<Method> methods) {
        this.methods = methods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeminarGoalResource that = (SeminarGoalResource) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(seminarType, that.seminarType) &&
                Objects.equals(methods, that.methods);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, seminarType, methods);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("seminarType", seminarType)
                .add("methods", methods)
                .toString();
    }
}
