package de.yfu.intranet.methods.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methods.data.domain.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

	public List<Attachment> findAll();
	public Attachment findOne(int attachmentId);
	

}