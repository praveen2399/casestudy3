package com.tcs.hack.controller;

import java.util.List;



import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.hack.config.SpringbatchConfig;
import com.tcs.hack.model.Inventory;
import com.tcs.hack.service.InventoryService;


@RestController
public class InventoryController {
	
	@Autowired
   	private InventoryService invService;
	
	@Autowired
    JobLauncher jobLauncher;
      
    @Autowired
    @Qualifier("File-Load")
    Job job;
	 
	
	   @GetMapping(value = "/item")
  	    @ResponseStatus(HttpStatus.OK)
  	    public ResponseEntity<List<Inventory>> getAllItems() {
  		 if(invService.getAllModels().size()>0)
  		 {
  			 return new ResponseEntity<List<Inventory>>(invService.getAllModels(),HttpStatus.OK);
  		 }
  		 else
  		 {
  			 return new ResponseEntity<List<Inventory>>(invService.getAllModels(),HttpStatus.NOT_FOUND);
  		 }
  	       
  	    }
  	    
  	    @GetMapping(value = "/item/{id}")
  	    @ResponseStatus(HttpStatus.OK)
  	    public ResponseEntity<Inventory> getItemByID(@PathVariable long id) {
  	    	
  	    	if(invService.getModelById(id) !=null)
  	    	{
  	    		return new ResponseEntity<Inventory>(invService.getModelById(id),HttpStatus.OK);
  	    	}
  	    	else
  	    	{
  	    		return new ResponseEntity<Inventory>(invService.getModelById(id),HttpStatus.NOT_FOUND);
  	    	}
  	       
  	    }
  	    
  	    
  	    @PostMapping(value = "/item", consumes = "application/json")
  	    @ResponseStatus(HttpStatus.CREATED)
  	    public ResponseEntity<Inventory> createNewItem(@RequestBody Inventory item) {
  	        invService.createModel(item);
  	        return new ResponseEntity<>(item, HttpStatus.CREATED);
  	    }
  	    
  	    @PutMapping(value = "/item/{id}")
  	    @ResponseStatus(HttpStatus.OK)
  	    public ResponseEntity<Inventory> updateVendorById(@RequestBody Inventory item, @PathVariable Long id)
  	    {
  	    	Inventory repItem=invService.updateModelById(item,id);
  	    	
  	    	if(null != repItem)
  	    	{
  	    		return new ResponseEntity<Inventory>(repItem,HttpStatus.OK);
  	    	}
  	    	else
  	    	{
  	    		return new ResponseEntity<Inventory>(repItem,HttpStatus.NOT_FOUND);
  	    	}
  	    	
  	    }
  	    
  	    @DeleteMapping(value = "/item/{id}")
  	    @ResponseStatus(HttpStatus.OK)
  	    public void deleeItemByID(@PathVariable long id) {
  		 invService.deleteModelById(id);
  	    }
  	 
  	    @DeleteMapping(value = "/item")
  	    @ResponseStatus(HttpStatus.OK)
  	    public void deleteAllItems() {
  	    	invService.deleteAllModels();
  	    }
  	    
  	    @GetMapping(value ="/item/update/{skuId}")
  	    @ResponseStatus(HttpStatus.OK)
  	    public Inventory updateInventory(@PathVariable Long skuId)
  	    {
  	    	return invService.getModelById(skuId);
  	    }
  	    
  	   @GetMapping(value="/item/load")
 	    public BatchStatus inventoryLoad() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
 	    {
 	    	JobParameters params = new JobParametersBuilder().addString("jobId", String.valueOf(System.currentTimeMillis())).toJobParameters();
 	    	
 	    	JobExecution jobExe=jobLauncher.run(job, params);
 	    	
 	    	SpringbatchConfig.batchStep="Job1";
 	    	
 	    	while(jobExe.isRunning())
 	    	{
 	    		System.out.println("Job running");
 	    	}
 	    	
 	    	return jobExe.getStatus();
 	    }
      
      
}
