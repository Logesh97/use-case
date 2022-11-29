package com.bank.klmbank;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bank.klmbank.controller.CustomerController;
import com.bank.klmbank.model.Customer;
import com.bank.klmbank.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    CustomerRepository customerRepository;
    Customer RECORD_1 = new Customer(1L, "Logeshwaran" , "logeshra" , "123456789" , "savings", 100000.00 , 
			"EFGH0989I" ,"MADU0000001" , "Madurai", "logesh@gmail.com", "Pillayar koil street , puthur , madurai"
			,"9999999999" , LocalDate.parse("1996-11-15"));
    
    @Test
    public void getAllRecords_success() throws Exception {
        List<Customer> records = new ArrayList<>(Arrays.asList(RECORD_1));
        
        Mockito.when(customerRepository.findAll()).thenReturn(records);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
//                .andExpect(jsonPath("$[2].name", is("Jane Doe")));
    }
}
