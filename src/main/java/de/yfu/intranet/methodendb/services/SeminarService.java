package de.yfu.intranet.methodendb.services;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.yfu.intranet.methodendb.exceptions.SeminarException;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.repositories.SeminarTypeRepository;

@Service
public class SeminarService {
	
	protected final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	SeminarTypeRepository seminarTypeRepo;
	
	public boolean setSeminarType(SeminarType seminarType) throws SeminarException {
		seminarTypeRepo.save(seminarType);
		return true;
	}
	
	public SeminarType getSeminarType(int seminarTypeId) throws SeminarException {
		SeminarType seminarType = seminarTypeRepo.findOne(seminarTypeId);
		if (seminarType == null) {
			throw new SeminarException("Couldn't load Seminar Type.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return seminarType;
	}
	
	public List<SeminarType> getAllSeminarTypes() throws SeminarException {
		List<SeminarType> seminarTypes = seminarTypeRepo.findAll();
		
		if (seminarTypes == null) {
			throw new SeminarException("Couldn't load Seminar Types.", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		return seminarTypes;
	}
}