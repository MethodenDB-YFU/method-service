package de.yfu.intranet.methodendb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.Method;

public interface MethodRepository extends JpaRepository<Method, Integer> {

	public List<Method> findAll();
	public Method findOne(int methodId);
	

}