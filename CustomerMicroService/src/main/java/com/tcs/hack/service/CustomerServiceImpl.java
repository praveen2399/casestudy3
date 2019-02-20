package com.tcs.hack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.hack.exception.BadResourceRequestException;
import com.tcs.hack.exception.NoSuchResourceFoundException;
import com.tcs.hack.model.Customer;
import com.tcs.hack.repository.CustomerRepository;



@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	

    @Override
    public void deleteAllModels() {
    	customerRepo.deleteAllInBatch();
    }

    @Override
    public void deleteModelById(Long id) {
    	
    	 customerRepo.deleteByCustomerID(id);
    }

    @Override
    public void createModel(Customer customer) {
        Customer existingCustomer = customerRepo.findOne(customer.getCustomerID());

        if (existingCustomer != null) {
            throw new BadResourceRequestException("Model with same id exists.");
        }

        customerRepo.save(customer);
    }

    @Override
    public Customer getModelById(Long id) {
        Customer customer = customerRepo.findOne(id);

        if (customer == null) {
            throw new NoSuchResourceFoundException("No model with given id found.");
        }

        return customer;
    }

    @Override
    public List<Customer> getAllModels() {
        return customerRepo.findAll();
    }

	@Override
	public Customer updateModelById(Customer customer,Long id) {
		
		Customer tempCustomer=customerRepo.findOne(id);
			
		if(null != tempCustomer)
		{
			tempCustomer.setAddress(customer.getAddress());
			tempCustomer.setContactNumber(customer.getContactNumber());
			tempCustomer.setGender(customer.getGender());
			customerRepo.save(tempCustomer);
			return tempCustomer;
		}
		
		return null;
	}
	

    
    
}
