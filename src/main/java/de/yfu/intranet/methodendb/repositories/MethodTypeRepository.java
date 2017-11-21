package de.yfu.intranet.methodendb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.MethodType;

public interface MethodTypeRepository extends JpaRepository<MethodType, Integer> {

	public List<MethodType> findAll();
	public MethodType findOne(int seminarTypeId);
	

}