package de.yfu.intranet.methodendb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import de.yfu.intranet.methodendb.dtos.AttachmentCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.AttachmentUpdateRequestDTO;
import de.yfu.intranet.methodendb.exceptions.AttachmentException;
import de.yfu.intranet.methodendb.models.Attachment;
import de.yfu.intranet.methodendb.repositories.AttachmentRepository;

public class AttachmentService {
	
	@Autowired
	AttachmentRepository attachmentRepo;

	public List<Attachment> getAllAttachments() throws AttachmentException {
		List<Attachment> attachments = attachmentRepo.findAll();
		if (attachments == null) {
			throw new AttachmentException("Couldn't find any attachments.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return attachments;
	}

	public Attachment getAttachment(int attachmentId) throws AttachmentException {
		Attachment attachment = attachmentRepo.findOne(attachmentId);
		if(attachment == null) {
			throw new AttachmentException(String.format("Couldn't find attachment with id [%s].", attachmentId), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return attachment;
	}

	public Attachment createAttachment(AttachmentCreateRequestDTO createRequest) {
		Attachment attachment = new Attachment(createRequest);
		return attachmentRepo.save(attachment);
	}

	public void updateAttachment(int attachmentId, AttachmentUpdateRequestDTO updateRequest) {
		Attachment attachment = new Attachment(attachmentId, updateRequest);
		attachmentRepo.save(attachment);
	}
}
