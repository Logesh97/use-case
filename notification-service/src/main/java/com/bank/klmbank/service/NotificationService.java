package com.bank.klmbank.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bank.klmbank.model.Notification;
import com.bank.klmbank.model.Transaction;
import com.bank.klmbank.repository.NotificationRepository;

@Service
public class NotificationService {
	
	Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	NotificationRepository notificationRepository;

	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}
	
	@KafkaListener(topics = "klm-bankdata", groupId="group_id", containerFactory = "userKafkaListenerFactory")
	public void consumeTransaction(Transaction transaction) {
		logger.info("Transaction received : [transaction type]"+transaction.getTransactionType()+" | [transaction id] "+ transaction.getTransactionId());
		Notification notification = new Notification();
		notification.setTransactionId(transaction.getTransactionId());
		notification.setTransactionType(transaction.getTransactionType());
		notification.setTransactionStatus(transaction.getTransactionStatus());
		notification.setNotificationMessage(transaction.getTransactionType().toString() + "processed for "+transaction.getAccountNumber()+ ".");
		
		save(notification);
	}
	
	public Notification save(Notification notification) {
		return notificationRepository.save(notification);
	}

}
