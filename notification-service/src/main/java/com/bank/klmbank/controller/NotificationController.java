package com.bank.klmbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.klmbank.model.Notification;
import com.bank.klmbank.service.NotificationService;

@RestController
@RequestMapping("/api/v1/klmbank/notification")
public class NotificationController {
	
	@Autowired
	NotificationService notificationService; 
	
	@GetMapping("/")
	public List<Notification> getAllNotification(){
		
		return notificationService.getAllNotifications();
		
	}

}
