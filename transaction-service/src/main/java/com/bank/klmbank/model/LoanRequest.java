package com.bank.klmbank.model;

public class LoanRequest {
	
	private Double requestAmount;
	private String customerId;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
