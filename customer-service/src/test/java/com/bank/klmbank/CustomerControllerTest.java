package com.bank.klmbank;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bank.klmbank.controller.CustomerController;
import com.bank.klmbank.model.Customer;
import com.bank.klmbank.repository.CustomerRepository;
import com.bank.klmbank.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ContextConfiguration(classes = SecurityConfig.class)
//@WebAppConfiguration
//@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

//	@Autowired
//	private WebApplicationContext context;
	
//	@Autowired
//    MockMvc mockMvc;
//	
//	@BeforeEach
//	public void setup() {
//		mockMvc = MockMvcBuilders
//				.webAppContextSetup(context)
//				.apply(springSecurity()) 
//				.build();
//	}
//    @Autowired
//    ObjectMapper mapper;
    
    @Autowired
    CustomerService customerService;
    
    @Mock
    CustomerRepository customerRepository;
    
    Customer RECORD_1 = new Customer(1L, "Logeshwaran" , "logeshra" , "123456789" , "savings", 100000.00 , 
			"EFGH0989I" ,"MADU0000001" , "Madurai", "logesh@gmail.com", "Pillayar koil street , puthur , madurai"
			,"9999999999" , LocalDate.parse("1996-11-15"));
    
    @Test
    public void getAllRecords_success() throws Exception {
        List<Customer> records = new ArrayList<>(Arrays.asList(RECORD_1));
        
        Mockito.when(customerRepository.findAll()).thenReturn(records);
        
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//                .andExpect(jsonPath("$[2].name", is("Jane Doe")));
        
        assertEquals(1, customerService.getAllCustomers().size());
    }
}
