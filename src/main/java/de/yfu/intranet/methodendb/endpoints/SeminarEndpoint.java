package de.yfu.intranet.methodendb.endpoints;

import de.yfu.intranet.methodendb.dtos.SeminarGoalResource;
import de.yfu.intranet.methodendb.dtos.SeminarTypeResource;
import de.yfu.intranet.methodendb.exceptions.SeminarException;
import de.yfu.intranet.methodendb.helpers.SeminarMapper;
import de.yfu.intranet.methodendb.models.SeminarGoal;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.services.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = SeminarEndpoint.SEMINAR_ENDPOINT)
public class SeminarEndpoint {

	public static final String SEMINAR_ENDPOINT = "/api/seminars";
	public static final String CONTENT_TYPE_SEMINAR_TYPE = "application/json";
	public static final String CONTENT_TYPE_SEMINAR_GOAL = "application/json";
	private final SeminarMapper seminarMapper;
	private final SeminarService seminarService;

	@Autowired
	public SeminarEndpoint(final SeminarService seminarService,
						   final SeminarMapper seminarMapper) {
		this.seminarService = seminarService;
		this.seminarMapper = seminarMapper;
	}

	@PostMapping(
			path = "/types",
			consumes = CONTENT_TYPE_SEMINAR_TYPE,
			produces = CONTENT_TYPE_SEMINAR_TYPE
	)
	public ResponseEntity<SeminarTypeResource> createSeminarType(
			@RequestBody @Valid final SeminarTypeResource seminarTypeResource) {
		final SeminarType seminarType = seminarMapper.mapToDataObject(seminarTypeResource);
		SeminarType createdSeminarType = seminarService.createSeminarType(seminarType);
		return new ResponseEntity<>(seminarMapper.mapFromDataObject(createdSeminarType), CREATED);
	}

	@PutMapping(
			path = "/types/{seminarTypeId}",
			consumes = CONTENT_TYPE_SEMINAR_TYPE,
			produces = CONTENT_TYPE_SEMINAR_TYPE
	)
	public ResponseEntity<SeminarTypeResource> updateSeminarType(
			@PathVariable("seminarTypeId") final UUID seminarTypeId,
			@RequestBody @Valid final SeminarTypeResource seminarTypeResource) throws SeminarException {
		final SeminarType seminarType = seminarMapper.mapToDataObject(seminarTypeResource);
		seminarType.setId(seminarTypeId);
		SeminarType updatedSeminarType = seminarService.updateSeminarType(seminarType);
		return new ResponseEntity<>(seminarMapper.mapFromDataObject(updatedSeminarType), HttpStatus.OK);
	}


	@GetMapping(
			path = "/types",
			consumes = CONTENT_TYPE_SEMINAR_TYPE,
			produces = CONTENT_TYPE_SEMINAR_TYPE
	)
	public Set<SeminarType> getSeminarTypes() throws SeminarException {
		return seminarService.getSeminarTypes();
	}

	@PostMapping(
			path = "/goals",
			consumes = CONTENT_TYPE_SEMINAR_GOAL,
			produces = CONTENT_TYPE_SEMINAR_GOAL
	)
	public ResponseEntity<SeminarGoalResource> createSeminarGoal(
			@RequestBody @Valid final SeminarGoalResource seminarGoalResource) {
		final SeminarGoal seminarGoal = seminarMapper.mapToDataObject(seminarGoalResource);
		SeminarGoal createdSeminarGoal = seminarService.createSeminarGoal(seminarGoal);
		return new ResponseEntity<>(seminarMapper.mapFromDataObject(createdSeminarGoal), CREATED);
	}

	@PutMapping(
			path = "/goals/{seminarGoalId}",
			consumes = CONTENT_TYPE_SEMINAR_GOAL,
			produces = CONTENT_TYPE_SEMINAR_GOAL
	)
	public ResponseEntity<SeminarGoalResource> updateSeminarGoal(
			@PathVariable("seminarGoalId") UUID seminarGoalId,
			@RequestBody @Valid final SeminarGoalResource seminarGoalResource) throws SeminarException {
		final SeminarGoal seminarGoal = seminarMapper.mapToDataObject(seminarGoalResource);
		seminarGoal.setId(seminarGoalId);
		SeminarGoal updatedSeminarGoal = seminarService.updateSeminarGoal(seminarGoal);
		return new ResponseEntity<>(seminarMapper.mapFromDataObject(updatedSeminarGoal), OK);
	}


	@GetMapping(
			path = "/goals",
			consumes = CONTENT_TYPE_SEMINAR_GOAL,
			produces = CONTENT_TYPE_SEMINAR_GOAL
	)
	public Set<SeminarGoal> getSeminarGoals(
			@RequestParam("seminarType") UUID seminarType) throws SeminarException {
		if (seminarType == null) {
			throw new SeminarException("Please provide a Seminar Type UUID as query parameter.", HttpStatus.BAD_REQUEST);
		}
		return seminarService.getSeminarGoals(seminarType);
	}
}