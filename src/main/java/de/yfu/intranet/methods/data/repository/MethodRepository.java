package de.yfu.intranet.methods.data.repository;

import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methods.data.domain.Method;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends CrudRepository<Method, UUID> {

	Set<Method> findAll();
	Method findOne(UUID methodId);
	

}