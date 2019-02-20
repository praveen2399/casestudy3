package com.tcs.hack.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.hack.model.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Transactional
    Long deleteByCustomerID(Long customerID);

}
