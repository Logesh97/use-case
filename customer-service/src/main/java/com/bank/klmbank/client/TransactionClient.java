package com.bank.klmbank.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.klmbank.model.LoanRequest;
import com.bank.klmbank.model.Transaction;

@FeignClient("bank-transaction")
public interface TransactionClient {
	
	@GetMapping("/api/v1/klmbank/transaction/")
	public List<Transaction> getAllTransactions();

	@PostMapping("/api/v1/klmbank/transaction/applyLoan")
	public Transaction applyLoan(@RequestBody LoanRequest loanRequest);
	

}
