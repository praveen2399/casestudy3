package com.tcs.hack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.hack.service.CustomerService;
import com.tcs.hack.model.Customer;



@RestController
public class CustomerController {
	
	 @Autowired
	   	private CustomerService customerService;
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = "application/json")
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Customer> createNewModel(@RequestBody Customer customer) {
	        customerService.createModel(customer);
	        return new ResponseEntity<>(customer, HttpStatus.CREATED);
	    }
	
	 	@DeleteMapping(value = "/erase")
	    @ResponseStatus(HttpStatus.OK)
	    public void deleteAllModels() {
	        customerService.deleteAllModels();
	    }
	 
	 	@DeleteMapping(value = "/customer/{id}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deleteModelById(@PathVariable long id) {
		 customerService.deleteModelById(id);
	    }
	 
	 	@GetMapping(value = "/customer")
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<List<Customer>> getAllModels() {
		 if(customerService.getAllModels().size()>0)
		 {
			 return new ResponseEntity<List<Customer>>(customerService.getAllModels(),HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<List<Customer>>(customerService.getAllModels(),HttpStatus.NOT_FOUND);
		 }
	       
	    }

	    @GetMapping(value = "/customer/{id}")
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Customer> getModelById(@PathVariable long id) {
	    	
	    	if(customerService.getModelById(id) !=null)
	    	{
	    		return new ResponseEntity<Customer>(customerService.getModelById(id),HttpStatus.OK);
	    	}
	    	else
	    	{
	    		return new ResponseEntity<Customer>(customerService.getModelById(id),HttpStatus.NOT_FOUND);
	    	}
	       
	    }
	 
	    @PutMapping(value = "/customer/{id}")
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Customer> updateModelByID(@RequestBody Customer customer, @PathVariable Long id)
	    {
	    	Customer respCustomer=customerService.updateModelById(customer,id);
	    	
	    	if(null != respCustomer)
	    	{
	    		return new ResponseEntity<Customer>(respCustomer,HttpStatus.OK);
	    	}
	    	else
	    	{
	    		return new ResponseEntity<Customer>(respCustomer,HttpStatus.NOT_FOUND);
	    	}
	    	
	    }


}
