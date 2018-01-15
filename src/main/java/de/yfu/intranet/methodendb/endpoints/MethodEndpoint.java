package de.yfu.intranet.methodendb.endpoints;

import de.yfu.intranet.methodendb.dtos.MethodLevelResource;
import de.yfu.intranet.methodendb.dtos.MethodResource;
import de.yfu.intranet.methodendb.dtos.MethodTypeResource;
import de.yfu.intranet.methodendb.exceptions.MethodException;
import de.yfu.intranet.methodendb.exceptions.UserException;
import de.yfu.intranet.methodendb.helpers.MethodMapper;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.models.User;
import de.yfu.intranet.methodendb.services.MethodService;
import de.yfu.intranet.methodendb.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Path("/methods")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MethodEndpoint {
	
	private final MethodService methodService;
	private final MethodMapper methodMapper;
	private final UserService userService;
	
	@Autowired
	public MethodEndpoint(final MethodService methodService, 
			final MethodMapper methodMapper,
			final UserService userService) {
		this.methodService = methodService;
		this.methodMapper = methodMapper;
		this.userService = userService;
	}

	@GET
	@Path("/types")
	@ApiOperation(value = "Get all Method Types.", response = MethodType[].class)
	public Set<MethodType> getAllMethodTypes() {
		return methodService.getAllMethodTypes();
	}

	@POST
	@Path("/types")
	@ApiOperation(value = "Create a new Method Type.", response = MethodTypeResource.class)
	public Response createMethodType(@Valid final MethodTypeResource methodTypeResource,
									 @HeaderParam("X-User-ID") UUID userId) {
		final MethodType methodType = methodMapper.mapToDataObject(methodTypeResource);
		MethodType createdMethodType = methodService.createMethodType(methodType);
		return Response.status(Response.Status.CREATED).entity(methodMapper.mapFromDataObject(createdMethodType)).build();
	}

	@PUT
	@Path("/types/{methodTypeId: [A-z0-9-]+}")
	@ApiOperation(value = "Update an existing Method Type.", response = MethodTypeResource.class)
	public Response updateMethodType(@PathParam("methodTypeId") UUID methodTypeId,
									 @HeaderParam("X-User-ID") UUID userID,
									 @Valid final MethodTypeResource methodTypeResource) throws MethodException {
		final MethodType methodType = methodMapper.mapToDataObject(methodTypeResource);
		methodType.setId(methodTypeId);
		MethodType updatedMethodType = methodService.updateMethodType(methodType);
		return Response.status(Response.Status.OK).entity(methodMapper.mapFromDataObject(updatedMethodType)).build();
	}

	@GET
	@Path("/levels")
	@ApiOperation(value = "Get all Method Levels.", response = MethodLevel[].class)
	public Set<MethodLevel> getAllMethodLevels() {
		return methodService.getAllMethodLevels();
	}

	@POST
	@Path("/levels")
	@ApiOperation(value = "Create a new Method Level.", response = MethodLevelResource.class)
	public Response createMethodLevel(@Valid final MethodLevelResource methodLevelResource,
									  @HeaderParam("X-User-ID") UUID userId) {
		final MethodLevel methodLevel = methodMapper.mapToDataObject(methodLevelResource);
		MethodLevel createdMethodLevel = methodService.createMethodLevel(methodLevel);
		return Response.status(Response.Status.CREATED).entity(methodMapper.mapFromDataObject(createdMethodLevel)).build();
	}

	@PUT
	@Path("/levels/{methodLevelId: [A-z0-9-]+}")
	@ApiOperation(value = "Update an existing Method Level.", response = MethodLevelResource.class)
	public Response updateMethodLevel(@PathParam("methodLevelId") UUID methodLevelId,
									  @HeaderParam("X-User-ID") UUID userID,
									  @Valid final MethodLevelResource methodLevelResource) throws MethodException {
		final MethodLevel methodLevel = methodMapper.mapToDataObject(methodLevelResource);
		methodLevel.setId(methodLevelId);
		MethodLevel updatedMethodLevel = methodService.updateMethodLevel(methodLevel);
		return Response.status(Response.Status.OK).entity(methodMapper.mapFromDataObject(updatedMethodLevel)).build();
	}


	@GET
	@ApiOperation(value = "Get all Methods.", response = Method[].class)
	public Set<Method> getAllMethods(@HeaderParam("X-User-ID") UUID userId) throws MethodException {
		return methodService.getAllMethods(userId);
	}
	
	@GET
	@Path("/{methodId: [A-z0-9-]+}")
	@ApiOperation(value = "Get one Method by ID.", response = MethodResource[].class)
	public MethodResource getMethod(@PathParam("methodId") UUID methodId,
			@HeaderParam("X-User-ID") UUID userId) throws MethodException {
		return methodMapper.mapFromDataObject(methodService.getMethod(userId, methodId));
	}
	
	@POST
	@ApiOperation(value = "Create a new Method.", response = MethodResource.class)
	public Response createMethod(@HeaderParam("X-User-ID") UUID userId,
								 @Valid final MethodResource methodResource) throws UserException {
		final User createdBy = userService.findById(userId);
		final Method method = methodMapper.mapToDataObject(methodResource);
		method.setCreatedBy(createdBy);
		method.getAttachments().forEach(m -> m.setCreatedBy(createdBy));

		final Method createdMethod = methodService.createMethod(method);
		return Response.status(Response.Status.CREATED).entity(createdMethod).build();
	}
	
	@PUT
	@Path("{methodId: [A-z0-9-]+}")
	public Response updateMethod(@PathParam("methodId") UUID methodId,
								 @HeaderParam("X-User-ID") UUID userId,
								 @Valid final MethodResource methodResource) throws MethodException, UserException {
		
		final User modifiedBy = userService.findById(userId);
		final Method method = methodMapper.mapToDataObject(methodResource);
		method.setModifiedBy(modifiedBy);
		method.setId(methodId);
		
		Method updatedMethod = methodService.updateMethod(method);
		return Response.status(Response.Status.OK).entity(updatedMethod).build();
	}

	@DELETE
	@Path("{methodId: [A-z0-9-]+}")
	public Response deleteMethod(@PathParam("methodId") UUID methodId,
								 @HeaderParam("X-User-ID") UUID userId) throws MethodException {
		methodService.deleteMethod(userId, methodId);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}