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

import de.yfu.intranet.methodendb.dtos.AttachmentCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.AttachmentUpdateRequestDTO;
import de.yfu.intranet.methodendb.exceptions.AttachmentException;
import de.yfu.intranet.methodendb.models.Attachment;
import de.yfu.intranet.methodendb.services.AttachmentService;

@Path("/attachments")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AttachmentEndpoint {
	
	private static final String PATH_VARIABLE_ATTACHMENT_NAME = "attachment-name";
	
	static final String PATH_ATTACHMENT = "/attachments";
	static final String PATH_ATTACHMENT_NAME = PATH_ATTACHMENT + "/{" + PATH_VARIABLE_ATTACHMENT_NAME + "}";
	
	@Autowired
	AttachmentService attachmentService;
	
	
	@GET
	public List<Attachment> getAllAttachments() throws AttachmentException {
		return attachmentService.getAllAttachments();
	}
	
	@GET
	@Path("/{attachmentId: [0-9]+}")
	public Attachment getAttachment(@PathParam("attachmentId") int attachmentId) throws AttachmentException {
		return attachmentService.getAttachment(attachmentId);
	}
	
	@POST
	public Response createAttachment(AttachmentCreateRequestDTO createRequest) {
		Attachment result = attachmentService.createAttachment(createRequest);
		final URI location = fromCurrentContextPath().path(PATH_ATTACHMENT_NAME).buildAndExpand(String.valueOf(result.getId())).toUri();
		return Response.created(location).build();
	}
	
	@PUT
	@Path("/{attachmentId: [0-9]+}")
	public Response updateAttachment(@PathParam("attachmentId") int attachmentId, AttachmentUpdateRequestDTO updateRequest) {
		attachmentService.updateAttachment(attachmentId, updateRequest);
		return Response.ok().build();
	}
}