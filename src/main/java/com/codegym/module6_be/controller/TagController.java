package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.Tag;
import com.codegym.module6_be.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tags")
@CrossOrigin("*")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping
    public ResponseEntity<Iterable<Tag>> findAll() {
        Iterable<Tag> tags = tagService.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tag>> findById(@PathVariable Long id) {
        Optional<Tag> tagOptional = tagService.findById(id);
        if (!tagOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tagOptional, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tag> save(@RequestBody Tag tag) {
        Tag tags = tagService.save(tag);
        return new ResponseEntity<>(tags, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody Tag tag) {
        Optional<Tag> tagOptional = tagService.findById(id);
        if (!tagOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tag.setId(tagOptional.get().getId());
        tagService.save(tag);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tag> delete(@PathVariable Long id) {
        Optional<Tag> tagOptional = tagService.findById(id);
        if (!tagOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tagService.deleteById(tagOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
