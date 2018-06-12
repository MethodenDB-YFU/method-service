package de.yfu.intranet.methods.data.repository;

import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methods.data.domain.SeminarGoal;

import org.springframework.data.repository.CrudRepository;

public interface SeminarGoalRepository extends CrudRepository<SeminarGoal, UUID> {

    Set<SeminarGoal> findAll();
    SeminarGoal findOne(UUID seminarGoalId);
    Set<SeminarGoal> findAllBySeminarTypeId(UUID seminarTypId);


}