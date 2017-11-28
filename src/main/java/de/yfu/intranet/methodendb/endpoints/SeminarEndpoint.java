package de.yfu.intranet.methodendb.endpoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.yfu.intranet.methodendb.exceptions.SeminarException;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.services.SeminarService;

@Path("/seminars")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeminarEndpoint {
	
	@Autowired
	SeminarService seminarService;
	
	@GET
	@Path("/types")
	public List<SeminarType> getSeminarTypes() throws SeminarException {
		return seminarService.getAllSeminarTypes();
	}
	
	@GET
	@Path("/types/{seminarTypeId: [0-9]+}")
	public SeminarType getSeminarType(@PathParam("seminarTypeId") int seminarTypeId) throws SeminarException {
		return seminarService.getSeminarType(seminarTypeId);
	}
	
	@POST
	@Path("/types/add")
	public Response setSeminarType(SeminarType seminarType) throws SeminarException {
		seminarService.setSeminarType(seminarType);
		return Response.created(null).build();
	}
}