package com.tcs.hack.service;

import java.util.List;

import com.tcs.hack.model.Customer;






public interface CustomerService {
	
	  	void deleteAllModels();
	    void deleteModelById(Long id);

	    void createModel(Customer customer);

	    Customer getModelById(Long id);

	    List<Customer> getAllModels();
	    
	    Customer updateModelById(Customer customer,Long id);

}
