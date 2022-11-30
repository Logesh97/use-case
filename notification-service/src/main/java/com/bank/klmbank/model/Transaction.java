package com.bank.klmbank.model;

public class Transaction {
	
	public static enum Status{
		SUBMITTED , INPROGRESS , PENDING , APPROVED , REJECTED
	};
	
	public static enum Type{
		TRANSFER , LOAN
	};

	private Long transactionId;
	
	private Type transactionType;
	private String accountNumber;
	private Long customerId;
	private Double amount;
	private Status transactionStatus = Status.SUBMITTED; 
	
	
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
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	} 
}
