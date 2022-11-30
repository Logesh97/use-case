package com.bank.klmbank;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bank.klmbank.advice.CustomerException;
import com.bank.klmbank.model.Customer;
import com.bank.klmbank.repository.CustomerRepository;
import com.bank.klmbank.service.CustomerService;

@SpringBootTest
class CustomerServiceApplicationTests {
	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testGetAllCustomer() {
		Customer RECORD_1 = new Customer(1L, "Logeshwaran" , "logeshra" , "123456789" , "savings", 100000.00 , 
				"EFGH0989I" ,"MADU0000001" , "Madurai", "logesh@gmail.com", "Pillayar koil street , puthur , madurai"
				,"9999999999" , LocalDate.parse("1996-11-15"));
		List<Customer> records = new ArrayList<>();
		records.add(RECORD_1);
		
		Mockito.when(customerRepository.findAll()).thenReturn(records);
		assertEquals(1, customerService.getAllCustomers().size());
	}
	
	@Test
	void testSave() {
		Customer RECORD_1 = new Customer(1L, "Logeshwaran" , "logeshra" , "123456789" , "savings", 100000.00 , 
				"EFGH0989I" ,"MADU0000001" , "Madurai", "logesh@gmail.com", "Pillayar koil street , puthur , madurai"
				,"9999999999" , LocalDate.parse("1996-11-15"));
		
		Mockito.when(customerService.findByUsername(RECORD_1.getUsername())).thenReturn(RECORD_1);
		assertThatExceptionOfType(CustomerException.class).isThrownBy(
				() -> {
					customerService.save(RECORD_1);
				}
				);
	}
	
	
	

}
