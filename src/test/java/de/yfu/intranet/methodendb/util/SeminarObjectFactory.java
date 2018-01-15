package de.yfu.intranet.methodendb.util;

import de.yfu.intranet.methodendb.dtos.SeminarGoalResource;
import de.yfu.intranet.methodendb.dtos.SeminarTypeResource;
import de.yfu.intranet.methodendb.models.SeminarGoal;
import de.yfu.intranet.methodendb.models.SeminarType;

import java.util.Collections;
import java.util.UUID;

public class SeminarObjectFactory {

    public static SeminarType anySeminarType() {
        final SeminarType seminarType = new SeminarType();
        seminarType.setId(UUID.randomUUID());
        seminarType.setName("Any Seminar Type");
        seminarType.setSeminarGoals(Collections.emptySet());
        return seminarType;
    }

    public static SeminarGoal anySeminarGoal() {
        final SeminarGoal seminarGoal = new SeminarGoal();
        seminarGoal.setId(UUID.randomUUID());
        seminarGoal.setName("Any Seminar Goal Name");
        seminarGoal.setDescription("Any Seminar Goal Description");
        seminarGoal.setSeminarType(anySeminarType());
        return seminarGoal;
    }

    public static SeminarTypeResource anySeminarTypeResource() {
        final SeminarTypeResource anySeminarTypeResource = new SeminarTypeResource();
        anySeminarTypeResource.setId(UUID.randomUUID());
        anySeminarTypeResource.setName("Any Seminar Type Resource");
        return anySeminarTypeResource;
    }

    public static SeminarGoalResource anySeminarGoalResource() {
        final SeminarGoalResource anySeminarGoalResource = new SeminarGoalResource();
        anySeminarGoalResource.setId(UUID.randomUUID());
        anySeminarGoalResource.setName("Any Seminar Goal Resource");
        anySeminarGoalResource.setSeminarType(anySeminarType());
        return anySeminarGoalResource;
    }
}
