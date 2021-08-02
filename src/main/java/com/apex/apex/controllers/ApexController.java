package com.apex.apex.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apex.apex.dto.Customer;
import com.apex.apex.dto.CustomerResponse;
import com.apex.apex.respository.CustomerRespository;

@RestController
@RequestMapping("/v1/customer")
public class ApexController {

	@Autowired
	private CustomerRespository customerRespository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity getCustomer(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "100") Integer pageSize) {
		Pageable pageable = null;

		  try {      
		      List<Customer> customers = new ArrayList<Customer>();
		      Pageable paging = PageRequest.of(pageNumber, pageSize);
		      
		      Page<Customer> customerPage = customerRespository.findAll(paging);
		      customers = customerPage.getContent();
		            
		      CustomerResponse response = new CustomerResponse();
		      
		      response.setPageItems(customers);
		      response.setPageNumber(customerPage.getNumber());
		      response.setTotalPages(customerPage.getTotalPages());
		      response.setTotalElements(customerPage.getTotalElements());
		      
		      return new ResponseEntity<>(response, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomers(@PathVariable Long id) {
		return ResponseEntity.ok(customerRespository.findById(id).orElseThrow(RuntimeException::new));
	}

	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		Customer foundCustomer = customerRespository.findById(id).orElseThrow(RuntimeException::new);
		foundCustomer.setLastName(customer.getLastName());
		foundCustomer.setFristName(customer.getFristName());
		foundCustomer.setEmailId(customer.getEmailId());
		return ResponseEntity.ok(customerRespository.save(foundCustomer));
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer sCustomer = customerRespository.save(customer);
		return ResponseEntity.ok(sCustomer);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
		customerRespository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/lastName/{lastName}")
	public ResponseEntity<List<Customer>> getCustomerByLastname(@PathVariable String lastName,
			@PathVariable(required = false) Integer pageNumber, @PathVariable(required = false) Integer pageSize) {
		Pageable pageable = null;

		if (pageNumber != null && pageNumber != null) {
			pageable = PageRequest.of(pageNumber, pageSize);
		}
		
		List<Customer> foundCustomer = customerRespository.findByLastName(lastName, pageable);
		return ResponseEntity.ok(foundCustomer);
	}
}