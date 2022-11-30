package com.bank.klmbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bank.klmbank.model.Transaction.Status;
import com.bank.klmbank.model.Transaction.Type;

@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;
	
	private Long transactionId;
	private String NotificationMessage;
	private Type transactionType;
	private Status transactionStatus;
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getNotificationMessage() {
		return NotificationMessage;
	}
	public void setNotificationMessage(String notificationMessage) {
		NotificationMessage = notificationMessage;
	}
	public Type getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(Type transactionType) {
		this.transactionType = transactionType;
	}
	public Status getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(Status transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	

}
