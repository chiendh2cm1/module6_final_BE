package com.codegym.module6_be.service.notification;


import com.codegym.module6_be.model.Notification;
import com.codegym.module6_be.service.GeneralService;

public interface NotificationService extends GeneralService<Notification> {

    Iterable<Notification> findByUserId(Long userId);

    Iterable<Notification> saveAll(Iterable<Notification> notifications);

    void allRead(Long userId);
}
