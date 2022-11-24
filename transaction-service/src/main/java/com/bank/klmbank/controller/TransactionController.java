package com.bank.klmbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.klmbank.model.LoanRequest;
import com.bank.klmbank.model.Transaction;
import com.bank.klmbank.service.TransactionService;

@RestController
@RequestMapping("/api/v1/klmbank/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/")
	public List<Transaction> getAllTransactions(){
		return transactionService.getAllTransactions();
	}
	
	@PostMapping("/applyLoan")
	public Transaction applyLoan(@RequestBody LoanRequest loanRequest) {
		return transactionService.applyLoan(loanRequest);
	}

}
