package com.bank.klmbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.klmbank.model.LoanRequest;
import com.bank.klmbank.model.Transaction;
import com.bank.klmbank.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getAllTransactions() {
		try {
			return transactionRepository.findAll();
		}catch(Exception exception) {
			throw exception;
		}
	}

	public Transaction applyLoan(LoanRequest loanRequest) {
		Transaction loanApplication = new Transaction();
		
		loanApplication.setTransactionType(Transaction.Type.LOAN);
		loanApplication.setCustomerId(loanRequest.getCustomerId());
		loanApplication.setAmount(loanRequest.getRequestAmount());
		loanApplication.setAccountNumber(loanRequest.getAccountNumber());
		
		try {
			return transactionRepository.save(loanApplication);			
		}catch(Exception exception) {
			throw exception;
		}
	}
	
	
}
