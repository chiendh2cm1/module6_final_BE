package com.codegym.module6_be.service.attachment;

import com.codegym.module6_be.model.Attachment;
import com.codegym.module6_be.service.GeneralService;

import java.util.Optional;

public interface IAttachmentService extends GeneralService<Attachment> {

    Iterable<Attachment> findAttachmentsByCard_Id(Long cardId);

}
