package com.bank.klmbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.klmbank.model.Customer;
import com.bank.klmbank.repository.CustomerRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// find user from database where user = :username
// userRepo.findByUsername(username);// username, password, roles
		Customer customer = customerRepository.findByUsername(username);
		return customer;
	}
}
