package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.Board;
import com.codegym.module6_be.model.MemberWorkspace;
import com.codegym.module6_be.model.Notification;
import com.codegym.module6_be.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Iterable<Notification>> findAll() {
        return new ResponseEntity<>(notificationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Iterable<Notification>> findByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(notificationService.findByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Notification notification1 = notificationService.save(notification);
        return new ResponseEntity<>(notification1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(@PathVariable Long id, @RequestBody Notification notification) {
        Optional<Notification> notificationOptional = notificationService.findById(id);
        if (!notificationOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notification.setId(notificationOptional.get().getId());
        return new ResponseEntity<>(notificationService.save(notification), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> delete(@PathVariable Long id) {
        Optional<Notification> notificationOptional = notificationService.findById(id);
        if (!notificationOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        notificationService.deleteById(notificationOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save-all")
    public ResponseEntity<Iterable<Notification>> saveAll(@RequestBody Iterable<Notification> notifications) {
        Iterable<Notification> notification = notificationService.saveAll(notifications);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/read-all")
    public ResponseEntity<Iterable<Notification>> allRead(@RequestBody Long userId) {
        notificationService.allRead(userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<Notification> deleteAllById(@RequestBody Iterable<Notification> notificationss) {
        for (Notification notification : notificationss) {
            notificationService.deleteById(notification.getId());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
