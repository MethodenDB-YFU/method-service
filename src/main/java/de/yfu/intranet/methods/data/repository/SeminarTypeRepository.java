package de.yfu.intranet.methods.data.repository;

import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methods.data.domain.SeminarType;
import org.springframework.data.repository.CrudRepository;

public interface SeminarTypeRepository extends CrudRepository<SeminarType, UUID> {

	Set<SeminarType> findAll();
	SeminarType findOne(UUID seminarTypeId);
	

}