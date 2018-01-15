package de.yfu.intranet.methodendb.repositories;

import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methodendb.models.SeminarType;
import org.springframework.data.repository.CrudRepository;

public interface SeminarTypeRepository extends CrudRepository<SeminarType, UUID> {

	Set<SeminarType> findAll();
	SeminarType findOne(UUID seminarTypeId);
	

}