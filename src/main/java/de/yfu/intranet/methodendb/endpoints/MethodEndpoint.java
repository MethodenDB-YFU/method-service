package de.yfu.intranet.methodendb.endpoints;

import de.yfu.intranet.methodendb.dtos.MethodLevelResource;
import de.yfu.intranet.methodendb.dtos.MethodResource;
import de.yfu.intranet.methodendb.dtos.MethodTypeResource;
import de.yfu.intranet.methodendb.exceptions.MethodException;
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
	public Set<MethodType> getAllMethodTypes() throws MethodException {
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
	public Set<MethodLevel> getAllMethodLevels() throws MethodException {
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
	public List<Method> getAllMethods(@HeaderParam("X-User-ID") UUID userId) throws MethodException {
		return methodService.getAllMethods(userId);
	}
	
	@GET
	@Path("/{methodId: [0-9]+}")
	public Method getMethod(@PathParam("methodId") UUID methodId,
			@HeaderParam("X-User-ID") UUID userId) throws MethodException {
		return methodService.getMethod(userId, methodId);
	}
	
	@POST
	public Response createMethod(@HeaderParam("X-User-ID") UUID userId,
			MethodResource methodResource) throws MethodException {

		final User createdBy = userService.findById(userId);
		final Method method = methodMapper.mapToDataObject(methodResource);
		method.setCreatedBy(createdBy);
		method.getAttachments().forEach(m -> m.setCreatedBy(createdBy));

		final Method createdMethod = methodService.createMethod(method);
		return Response.status(Response.Status.CREATED).entity(createdMethod).build();
	}
	
	@PUT
	@Path("{methodId: [0-9]+}")
	public Response updateMethod(@PathParam("methodId") UUID methodId, 
			@HeaderParam("X-User-ID") UUID userId, 
			MethodResource methodResource) throws MethodException {
		
		final User modifiedBy = userService.findById(userId);
		final Method method = methodMapper.mapToDataObject(methodResource);
		method.setModifiedBy(modifiedBy);
		method.setId(methodId);
		
		Method updatedMethod = methodService.updateMethod(method);
		return Response.status(Response.Status.OK).entity(updatedMethod).build();
	}
}