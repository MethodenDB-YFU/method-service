package de.yfu.intranet.methodendb.repositories;

import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methodendb.models.MethodType;
import org.springframework.data.repository.CrudRepository;

public interface MethodTypeRepository extends CrudRepository<MethodType, UUID> {

	public Set<MethodType> findAll();
	public MethodType findOne(UUID seminarTypeId);
	

}