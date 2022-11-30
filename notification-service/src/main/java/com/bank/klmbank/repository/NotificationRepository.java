package com.bank.klmbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.klmbank.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
