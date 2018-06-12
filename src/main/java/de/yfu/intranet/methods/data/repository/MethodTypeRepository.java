package de.yfu.intranet.methods.data.repository;

import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methods.data.domain.MethodType;
import org.springframework.data.repository.CrudRepository;

public interface MethodTypeRepository extends CrudRepository<MethodType, UUID> {

	public Set<MethodType> findAll();
	public MethodType findOne(UUID seminarTypeId);
	

}