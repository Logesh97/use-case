package com.bank.klmbank.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.klmbank.model.Customer;
import com.bank.klmbank.repository.CustomerRepository;
import com.bank.klmbank.utils.PasswordUtil;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PasswordUtil passwordUtil;

	public Customer findByUsername(String username) {
		return customerRepository.findByUsername(username);
	}

	public Customer save(Customer customer) throws Exception {
		if(customer == null)
			throw new Exception("Customer details not found!");
		else if(customer.getUsername() != null &&
				customer.getUsername().isEmpty() )
			throw new Exception("Username of the customer not found!!");
		customer.setPassword(passwordUtil.encodePassword(customer.getPassword()));
		return customerRepository.save(customer);
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

}
