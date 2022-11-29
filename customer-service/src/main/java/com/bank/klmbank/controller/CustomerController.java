package com.bank.klmbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.klmbank.advice.CustomerException;
import com.bank.klmbank.model.Customer;
import com.bank.klmbank.model.JwtResponse;
import com.bank.klmbank.model.LoanRequest;
import com.bank.klmbank.model.Transaction;
import com.bank.klmbank.service.CustomerService;
import com.bank.klmbank.utils.JwtTokenUtil;


@RestController
@RequestMapping("/api/v1/klmbank/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/")
	public List<Customer> getAllCustomer(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/{customerId}")
	public Customer getAllCustomerById(@PathVariable Long customerId){
		return customerService.getCustomerById(customerId);
	}
	
	@GetMapping("/all-transaction/{customerId}")
	public List<Transaction> getAllTransactionByCustomerId(@PathVariable Long customerId){
		return customerService.getAllTransactionByCustomerId(customerId);
	}
	
	@PutMapping("/updateProfile")
	public Customer updateProfile(@RequestBody Customer customer) {
		return customerService.updateProfile(customer);
	}
	
	@PostMapping("/apply-loan")
	public Transaction ApplyLoan(@RequestBody LoanRequest loanRequest) {
		if(loanRequest.getRequestAmount() == null || loanRequest.getRequestAmount() < 100) {
			throw new CustomerException("Invalid amount requested!");
		}
		return customerService.applyLoan(loanRequest);
	}
	
	@PostMapping("/register")
	public Customer register(@RequestBody Customer customer) throws Exception {
		System.out.println(customer.getUsername());
		return customerService.save(customer);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody Customer customer) throws Exception {
		authenticate(customer.getUsername(), customer.getPassword());
		final Customer customerByUsername = customerService.findByUsername(customer.getUsername());
		final String token = jwtTokenUtil.generateToken(customerByUsername);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
