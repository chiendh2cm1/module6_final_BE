package com.codegym.module6_be.service.notification;

import com.codegym.module6_be.model.Notification;
import com.codegym.module6_be.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Iterable<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Iterable<Notification> findByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public Iterable<Notification> saveAll(Iterable<Notification> notifications) {
        return notificationRepository.saveAll(notifications);
    }

    @Override
    public void allRead(Long userId) {
        notificationRepository.allRead(userId);
    }
}
