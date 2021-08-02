package com.apex.apex.respository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apex.apex.dto.Customer;

@Repository
public interface CustomerRespository extends PagingAndSortingRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName, Pageable pageable);
}