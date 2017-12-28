package de.yfu.intranet.methodendb.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.Method;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends JpaRepository<Method, UUID> {

	public List<Method> findAll();
	public Method findOne(UUID methodId);
	

}