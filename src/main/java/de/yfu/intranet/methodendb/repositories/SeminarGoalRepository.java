package de.yfu.intranet.methodendb.repositories;

import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methodendb.models.SeminarGoal;

import de.yfu.intranet.methodendb.models.SeminarType;
import org.springframework.data.repository.CrudRepository;

public interface SeminarGoalRepository extends CrudRepository<SeminarGoal, UUID> {

    Set<SeminarGoal> findAll();
    SeminarGoal findOne(UUID seminarGoalId);
    Set<SeminarGoal> findAllBySeminarTypeId(UUID seminarTypId);


}