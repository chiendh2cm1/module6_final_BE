package com.codegym.module6_be.service.comment;


import com.codegym.module6_be.model.Comment;
import com.codegym.module6_be.service.GeneralService;

public interface CommentService extends GeneralService<Comment> {

    Iterable<Comment> findAllCommentByCardId(Long id);

    Iterable<Comment> saveAll(Iterable<Comment> comments);
}
