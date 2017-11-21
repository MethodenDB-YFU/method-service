package de.yfu.intranet.methodendb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

	public List<Attachment> findAll();
	public Attachment findOne(int attachmentId);
	

}