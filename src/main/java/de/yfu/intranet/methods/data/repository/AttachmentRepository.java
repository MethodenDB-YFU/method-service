package de.yfu.intranet.methods.data.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methods.data.domain.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

	List<Attachment> findAll();
	Optional<Attachment> findById(UUID attachmentId);
	

}