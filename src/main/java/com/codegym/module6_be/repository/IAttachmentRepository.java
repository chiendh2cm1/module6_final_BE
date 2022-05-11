package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {
    Iterable<Attachment> findAttachmentsByCard_Id(Long cardId);

}
