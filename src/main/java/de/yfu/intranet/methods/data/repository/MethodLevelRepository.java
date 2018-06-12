package de.yfu.intranet.methods.data.repository;

import de.yfu.intranet.methods.data.domain.MethodLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface MethodLevelRepository extends CrudRepository<MethodLevel, UUID> {

	public Set<MethodLevel> findAll();
	public MethodLevel findOne(UUID seminarTypeId);
	

}