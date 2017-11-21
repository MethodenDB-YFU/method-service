package de.yfu.intranet.methodendb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.SeminarType;

public interface SeminarTypeRepository extends JpaRepository<SeminarType, Integer> {

	public List<SeminarType> findAll();
	public SeminarType findOne(int seminarTypeId);
	

}