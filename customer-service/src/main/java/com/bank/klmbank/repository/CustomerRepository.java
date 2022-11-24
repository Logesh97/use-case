package com.bank.klmbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.klmbank.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUsername(String username);

}
