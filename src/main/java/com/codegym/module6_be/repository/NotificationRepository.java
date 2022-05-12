package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query( value = "select * from notification join notification_receiver nr on notification.id = nr.notification_id " +
            "where nr.receiver_id = ?1 order by id desc limit 99",nativeQuery = true)
    Iterable<Notification> findByUserId(Long userId);

    @Modifying(clearAutomatically = true)
    @Query( value = "update notification join notification_receiver nr on notification.id = nr.notification_id " +
            "set notification.status = true where nr.receiver_id = ?1",nativeQuery = true)
    void allRead(Long userId);
}
