package com.bank.klmbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.klmbank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
