package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.Comment;
import com.codegym.module6_be.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @GetMapping
    public ResponseEntity<Iterable<Comment>> findAll() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/comment-card/{id}")
    public ResponseEntity<Iterable<Comment>> findAllCommentByCardId(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findAllCommentByCardId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> save(@RequestBody Comment comment) {
        Comment comment1 = commentService.save(comment);
        return new ResponseEntity<>(comment1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Iterable<Comment>> saveAll(@RequestBody Iterable<Comment> comments) {
        Iterable<Comment> comment = commentService.saveAll(comments);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setId(commentOptional.get().getId());
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
