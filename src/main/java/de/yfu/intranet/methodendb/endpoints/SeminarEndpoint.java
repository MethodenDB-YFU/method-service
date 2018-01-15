package de.yfu.intranet.methodendb.endpoints;

import de.yfu.intranet.methodendb.dtos.SeminarGoalResource;
import de.yfu.intranet.methodendb.dtos.SeminarTypeResource;
import de.yfu.intranet.methodendb.exceptions.SeminarException;
import de.yfu.intranet.methodendb.helpers.SeminarMapper;
import de.yfu.intranet.methodendb.models.SeminarGoal;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.services.SeminarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.UUID;

@Path("/seminars")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value="Seminars", description="Everything around Seminar Types and Goals.")
public class SeminarEndpoint {

	private final SeminarMapper seminarMapper;
	private final SeminarService seminarService;

	@Autowired
	public SeminarEndpoint(final SeminarService seminarService,
						   final SeminarMapper seminarMapper) {
		this.seminarService = seminarService;
		this.seminarMapper = seminarMapper;
	}
	
	@POST
	@Path("/types")
	@ApiOperation(value="Create Seminar Type.", response=SeminarTypeResource.class)
	public Response createSeminarType(@ApiParam("Seminar Type data") @Valid final SeminarTypeResource seminarTypeResource) {
		final SeminarType seminarType = seminarMapper.mapToDataObject(seminarTypeResource);
		SeminarType createdSeminarType = seminarService.createSeminarType(seminarType);
		return Response.status(Response.Status.CREATED).entity(seminarMapper.mapFromDataObject(createdSeminarType)).build();
	}
	@PUT
	@Path("/types/{seminarTypeId: [A-z0-9-]+}")
	@ApiOperation(value="Update Seminar Type", response=SeminarTypeResource.class)
	public Response updateSeminarType(@ApiParam(name="Seminar Type ID", required=true) @PathParam("seminarTypeId") UUID seminarTypeId,
									  @ApiParam("User ID") @HeaderParam("X-User-ID") UUID userId,
									  @ApiParam("Seminar Type Data") @Valid final SeminarTypeResource seminarTypeResource) throws SeminarException {
		final SeminarType seminarType = seminarMapper.mapToDataObject(seminarTypeResource);
		seminarType.setId(seminarTypeId);
		SeminarType updatedSeminarType = seminarService.updateSeminarType(seminarType);
		return Response.status(Response.Status.OK).entity(seminarMapper.mapFromDataObject(updatedSeminarType)).build();
	}

	@GET
	@Path("/types")
	@ApiOperation(value="Get all Seminar Types", response=SeminarType.class)
	public Set<SeminarType> getSeminarTypes(@ApiParam("User ID") @HeaderParam("X-User-ID") UUID userId) throws SeminarException {
		return seminarService.getSeminarTypes();
	}

	@POST
	@Path("/goals")
	public Response createSeminarGoal(@Valid final SeminarGoalResource seminarGoalResource,
									  @HeaderParam("X-User-ID") UUID userId) {
		final SeminarGoal seminarGoal = seminarMapper.mapToDataObject(seminarGoalResource);
		SeminarGoal createdSeminarGoal = seminarService.createSeminarGoal(seminarGoal);
		return Response.status(Response.Status.CREATED).entity(seminarMapper.mapFromDataObject(createdSeminarGoal)).build();
	}

	@PUT
	@Path("/goals/{seminarGoalId: [A-z0-9-]+}")
	public Response updateSeminarGoal(@PathParam("seminarGoalId") UUID seminarGoalId,
									  @HeaderParam("X-User-ID") UUID userId,
									  @Valid final SeminarGoalResource seminarGoalResource) throws SeminarException {
		final SeminarGoal seminarGoal = seminarMapper.mapToDataObject(seminarGoalResource);
		seminarGoal.setId(seminarGoalId);
		SeminarGoal updatedSeminarGoal = seminarService.updateSeminarGoal(seminarGoal);
		return Response.status(Response.Status.OK).entity(seminarMapper.mapFromDataObject(updatedSeminarGoal)).build();
	}

	@GET
	@Path("/goals")
	public Set<SeminarGoal> getSeminarGoals(@HeaderParam("X-User-ID") UUID userId,
											@QueryParam("seminarType") UUID seminarType) throws SeminarException {
		if (seminarType == null) {
			throw new SeminarException("Please provide a Seminar Type UUID as query parameter.", HttpStatus.BAD_REQUEST);
		}
		return seminarService.getSeminarGoals(seminarType);
	}
}