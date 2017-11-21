package de.yfu.intranet.methodendb.endpoints;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.yfu.intranet.methodendb.dtos.MethodCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodLevelCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodLevelUpdateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeUpdateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodUpdateRequestDTO;
import de.yfu.intranet.methodendb.exceptions.MethodException;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.services.MethodService;

@Path("/methods")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MethodEndpoint {
	
	private static final String PATH_VARIABLE_METHOD_NAME = "method-name";
	private static final String PATH_VARIABLE_METHOD_TYPE_NAME = "method-type-name";
	private static final String PATH_VARIABLE_METHOD_LEVEL_NAME = "method-level-name";
	
	static final String PATH_METHOD = "/methods";
	static final String PATH_METHOD_TYPE = PATH_METHOD + "/types";
	static final String PATH_METHOD_LEVEL = PATH_METHOD + "/levels";
	static final String PATH_METHOD_NAME = PATH_METHOD + "/{" + PATH_VARIABLE_METHOD_NAME + "}";
	static final String PATH_METHOD_TYPE_NAME = PATH_METHOD_TYPE + "/{" + PATH_VARIABLE_METHOD_TYPE_NAME + "}";
	static final String PATH_METHOD_LEVEL_NAME = PATH_METHOD_LEVEL + "/{" + PATH_VARIABLE_METHOD_LEVEL_NAME + "}";
	
	@Autowired
	MethodService methodService;
	
	@GET
	public List<Method> getAllMethods() throws MethodException {
		return methodService.getAllMethods();
	}
	
	@GET
	@Path("/{methodId: [0-9]+}")
	public Method getMethod(@PathParam("methodId") int methodId) throws MethodException {
		return methodService.getMethod(methodId);
	}
	
	@POST
	public Response createMethod(MethodCreateRequestDTO methodCreateRequestDTO) throws MethodException {
		Method result = methodService.createMethod(methodCreateRequestDTO);
		final URI location = fromCurrentContextPath().path(PATH_METHOD_NAME).buildAndExpand(String.valueOf(result.getId())).toUri();
		return Response.created(location).build();
	}
	
	@PUT
	@Path("{methodId: [0-9]+}")
	public Response updateMethod(@PathParam("methodId") int methodId, MethodUpdateRequestDTO updateRequest) throws MethodException {
		methodService.updateMethod(methodId, updateRequest);
		return Response.ok().build();
	}
	
	@GET
	@Path("/types")
	public List<MethodType> getAllMethodTypes() throws MethodException {
		return methodService.getAllMethodTypes();
	}
	
	@GET
	@Path("/types/{typeId: [0-9]+}")
	public MethodType getMethodType(@PathParam("methodId") int methodId) throws MethodException {
		return methodService.getMethodType(methodId);
	}
	
	@POST
	@Path("/types")
	public Response createMethodType(MethodTypeCreateRequestDTO methodType) throws MethodException {
		MethodType result = methodService.createMethodType(methodType);
		final URI location = fromCurrentContextPath().path(PATH_METHOD_TYPE_NAME).buildAndExpand(result.getId()).toUri();
		return Response.created(location).build();
	}
	
	@PUT
	@Path("/types/{typeId: [0-9]+}")
	public Response updateMethodType(@PathParam("typeId") int typeId, MethodTypeUpdateRequestDTO updateRequest) throws MethodException {
		methodService.updateMethodType(typeId, updateRequest);
		return Response.ok().build();
	}
		
	@GET
	@Path("/levels") 
	public List<MethodLevel> getAllMethodLevels() throws MethodException {
		return methodService.getAllMethodLevels();
	}
	
	@GET
	@Path("/levels/{levelId: [0-9]+}")
	public MethodLevel getMethodLevel(@PathParam("levelId") int levelId) throws MethodException {
		return methodService.getMethodLevel(levelId);
	}
	
	@POST
	@Path("/levels")
	public Response createMethodLevel(MethodLevelCreateRequestDTO methodLevelCreateRequest) throws MethodException {
		MethodLevel result = methodService.createMethodLevel(methodLevelCreateRequest);
		final URI location = fromCurrentContextPath().path(PATH_METHOD_LEVEL_NAME).buildAndExpand(result.getId()).toUri();
		return Response.created(location).build();
	}
	
	@PUT
	@Path("/levels/{levelId: [0-9]+}")
	public Response updateMethodLevel(@PathParam("levelId") int levelId, MethodLevelUpdateRequestDTO methodLevelUpdateRequest) throws MethodException {
		methodService.updateMethodLevel(levelId, methodLevelUpdateRequest);
		return Response.ok().build();
	}
}