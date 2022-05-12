package com.codegym.module6_be.service.comment;

import com.codegym.module6_be.model.Comment;
import com.codegym.module6_be.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findAllCommentByCardId(Long id) {
        return commentRepository.findAllByCardId(id);
    }

    @Override
    public Iterable<Comment> saveAll(Iterable<Comment> comments) {
        return commentRepository.saveAll(comments);
    }
}
