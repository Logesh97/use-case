package com.bank.klmbank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.klmbank.advice.CustomerException;
import com.bank.klmbank.client.TransactionClient;
import com.bank.klmbank.model.Customer;
import com.bank.klmbank.model.LoanRequest;
import com.bank.klmbank.model.Transaction;
import com.bank.klmbank.repository.CustomerRepository;
import com.bank.klmbank.utils.PasswordUtil;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PasswordUtil passwordUtil;
	
	@Autowired
	TransactionClient transactionClient;

	public Customer findByUsername(String username) {
		return customerRepository.findByUsername(username);
	}

	public Customer save(Customer customer) throws CustomerException {
		if(customer == null) {
			throw new CustomerException("Customer details not found!");
		} else if(customer.getUsername() != null &&
				customer.getUsername().isEmpty() ) {
			throw new CustomerException("Username of the customer not found!!");
		} else if(findByUsername(customer.getUsername()) != null) {
			throw new CustomerException("Username already registered!");
		}
		customer.setAmount(1000.00);
		customer.setAccountNumber(getAccountNumber(customer.getBranchName()));
		customer.setPassword(passwordUtil.encodePassword(customer.getPassword()));
		return customerRepository.save(customer);
	}

	public String getAccountNumber(String branchName) {
		String accountNumber;
		Customer customer;
		long count = this.customerRepository.count();
		do {
			accountNumber = branchName.substring(0 , 4)
					.toUpperCase()
					.concat(String.format("%07d", ++count));
			customer = customerRepository.findByAccountNumber(accountNumber);
		}while(customer != null);
		return accountNumber;
	}

	public List<Customer> getAllCustomers() {
		try {
			return customerRepository.findAll();
		}catch(Exception exception) {
			throw exception;
		}
	}

	public Customer updateProfile(Customer customer) {
		Optional<Customer> customerById = customerRepository.findById(customer.getCustomerId());
		Customer updatedCustomer = null;
		if(customerById.isPresent()) {
			updatedCustomer = customerById.get();
		}
		if(customer.getAddress() != null) {
			updatedCustomer.setAddress(customer.getAddress());
		}
		if(customer.getContactNumber() != null) {
			updatedCustomer.setContactNumber(customer.getContactNumber());
		}
		if(customer.getDateOfBirth() != null) {
			updatedCustomer.setDateOfBirth(customer.getDateOfBirth());
		}
		if(customer.getEmail() != null) {
			updatedCustomer.setEmail(customer.getEmail());
		}
		return customerRepository.save(updatedCustomer);
	}

	public List<Transaction> getAllTransactionByCustomerId(Long customerId) {
		List<Transaction> allTransactions = transactionClient.getAllTransactions();
		return allTransactions.stream()
				.filter(transaction -> transaction.getCustomerId() == customerId)
				.collect(Collectors.toList());
	}

	public Customer getCustomerById(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isPresent())
			return customer.get();
		return null;
	}

	public Transaction applyLoan(LoanRequest loanRequest) {
		Customer customer = null;
		if(loanRequest.getCustomerId() != null) {
			customer = getCustomerById(loanRequest.getCustomerId());
			if(customer == null) {
				throw new CustomerException("Invalid customerId requested!");
			} else {
				loanRequest.setAccountNumber(customer.getAccountNumber());
			}
		}else {
			throw new CustomerException("Invalid customerId requested!");
		}
		return transactionClient.applyLoan(loanRequest);
	}

}
