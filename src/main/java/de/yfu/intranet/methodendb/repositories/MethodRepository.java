package de.yfu.intranet.methodendb.repositories;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.Method;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends CrudRepository<Method, UUID> {

	Set<Method> findAll();
	Method findOne(UUID methodId);
	

}