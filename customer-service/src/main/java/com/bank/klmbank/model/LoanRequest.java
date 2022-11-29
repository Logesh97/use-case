package com.bank.klmbank.model;

public class LoanRequest {
	
	private Double requestAmount;
	private Long customerId;
	private String accountNumber;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(Double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
