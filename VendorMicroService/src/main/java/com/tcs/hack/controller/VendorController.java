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

import com.tcs.hack.model.Vendor;
import com.tcs.hack.service.VendorService;



@RestController
public class VendorController {
	
	@Autowired
   	private VendorService vendorService;
	
	  @GetMapping(value = "/vendor")
 	    @ResponseStatus(HttpStatus.OK)
 	    public ResponseEntity<List<Vendor>> getAllVendors() {
 		 if(vendorService.getAllModels().size()>0)
 		 {
 			 return new ResponseEntity<List<Vendor>>(vendorService.getAllModels(),HttpStatus.OK);
 		 }
 		 else
 		 {
 			 return new ResponseEntity<List<Vendor>>(vendorService.getAllModels(),HttpStatus.NOT_FOUND);
 		 }
 	       
 	    }
 	    
 	    @GetMapping(value = "/vendor/{id}")
 	    @ResponseStatus(HttpStatus.OK)
 	    public ResponseEntity<Vendor> getVendorByID(@PathVariable long id) {
 	    	
 	    	if(vendorService.getModelById(id) !=null)
 	    	{
 	    		return new ResponseEntity<Vendor>(vendorService.getModelById(id),HttpStatus.OK);
 	    	}
 	    	else
 	    	{
 	    		return new ResponseEntity<Vendor>(vendorService.getModelById(id),HttpStatus.NOT_FOUND);
 	    	}
 	       
 	    }
 	    
 	    
 	    @RequestMapping(value = "/vendor", method = RequestMethod.POST, consumes = "application/json")
 	    @ResponseStatus(HttpStatus.CREATED)
 	    public ResponseEntity<Vendor> createNewVendor(@RequestBody Vendor vendor) {
 	        vendorService.createModel(vendor);
 	        return new ResponseEntity<>(vendor, HttpStatus.CREATED);
 	    }
 	    
 	    @PutMapping(value = "/vendor/{id}")
 	    @ResponseStatus(HttpStatus.OK)
 	    public ResponseEntity<Vendor> updateVendorById(@RequestBody Vendor vendor, @PathVariable Long id)
 	    {
 	    	Vendor respVendor=vendorService.updateModelById(vendor,id);
 	    	
 	    	if(null != respVendor)
 	    	{
 	    		return new ResponseEntity<Vendor>(respVendor,HttpStatus.OK);
 	    	}
 	    	else
 	    	{
 	    		return new ResponseEntity<Vendor>(respVendor,HttpStatus.NOT_FOUND);
 	    	}
 	    	
 	    }
 	    
 	    @DeleteMapping(value = "/vendor/{id}")
 	    @ResponseStatus(HttpStatus.OK)
 	    public void deleeVendorByID(@PathVariable long id) {
 		 vendorService.deleteModelById(id);
 	    }
 	 
 	    @DeleteMapping(value = "/vendor")
 	    @ResponseStatus(HttpStatus.OK)
 	    public void deleteAllVendors() {
 	        vendorService.deleteAllModels();
 	    }
 	    
 	    

}
