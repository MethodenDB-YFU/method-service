package de.yfu.intranet.methodendb.services;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import de.yfu.intranet.methodendb.repositories.SeminarGoalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.yfu.intranet.methodendb.exceptions.SeminarException;
import de.yfu.intranet.methodendb.models.SeminarGoal;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.repositories.SeminarTypeRepository;

import static java.lang.String.format;

@Service
public class SeminarService {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private final SeminarTypeRepository seminarTypeRepository;
	private final SeminarGoalRepository seminarGoalRepository;

	@Autowired
	public SeminarService(final SeminarTypeRepository seminarTypeRepository,
						  final SeminarGoalRepository seminarGoalRepository) {
		this.seminarTypeRepository = seminarTypeRepository;
		this.seminarGoalRepository = seminarGoalRepository;
	}

	public SeminarGoal createSeminarGoal(SeminarGoal seminarGoal) {
		return seminarGoalRepository.save(seminarGoal);
	}

	public SeminarGoal updateSeminarGoal(SeminarGoal seminarGoal) throws SeminarException {
		final SeminarGoal storedSeminarGoal = seminarGoalRepository.findOne(seminarGoal.getId());
		if (storedSeminarGoal != null) {
			return seminarGoalRepository.save(seminarGoal);
		}
		throw new SeminarException(format("No Seminar Goal found for ID [%s]. Not abel to update.", seminarGoal.getId()), HttpStatus.NOT_FOUND);
	}
	
	public Set<SeminarGoal> getSeminarGoals(UUID seminarType) {
		Set<SeminarGoal> seminarGoals = seminarGoalRepository.findAllBySeminarTypeId(seminarType);
		if (seminarGoals == null) {
			LOGGER.info("No Seminar Goal found for Seminar Type ID [{}]", seminarType);
			return Collections.emptySet();
		}
		return seminarGoals;
	}

	public SeminarType createSeminarType(SeminarType seminarType) {
		return seminarTypeRepository.save(seminarType);
	}

    public SeminarType updateSeminarType(SeminarType seminarType) throws SeminarException {
    	final SeminarType storedSeminarType = seminarTypeRepository.findOne(seminarType.getId());
    	if (storedSeminarType != null) {
    		return seminarTypeRepository.save(seminarType);
		}
		throw new SeminarException(format("No Seminar Type found for ID [%s]. Not able to update.", seminarType.getId()), HttpStatus.NOT_FOUND);
	}

	public Set<SeminarType> getSeminarTypes() {
		Set<SeminarType> seminarTypes = seminarTypeRepository.findAll();
		if (seminarTypes == null) {
			LOGGER.info("No Seminar Goal found.");
			return Collections.emptySet();
		}
		return seminarTypes;
	}
}