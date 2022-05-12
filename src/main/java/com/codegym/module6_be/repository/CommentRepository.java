package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Iterable<Comment> findAllByCardId(Long id);
}
