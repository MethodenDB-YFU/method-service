package de.yfu.intranet.methodendb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.MethodLevel;

public interface MethodLevelRepository extends JpaRepository<MethodLevel, Integer> {

	public List<MethodLevel> findAll();
	public MethodLevel findOne(int seminarTypeId);
	

}