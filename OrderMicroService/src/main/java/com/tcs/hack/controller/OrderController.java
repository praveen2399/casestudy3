package com.tcs.hack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.tcs.hack.model.Order;
import com.tcs.hack.model.OrderDTO;
import com.tcs.hack.service.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value="/order/cust/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Order>> getAllOrderCustomer(@PathVariable long id)
	{
		 if(orderService.findAllOrdersCust(id) .size()>0)
		 {
			 return new ResponseEntity<List<Order>>(orderService.findAllOrdersCust(id),HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<List<Order>>(orderService.findAllOrdersCust(id),HttpStatus.NOT_FOUND);
		 }
	}
	
	//@PostMapping(value="/order")
	@RequestMapping(value = "/order", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Order> createNewOrder(@RequestBody OrderDTO orderDTO)
	{
		
		Order order=orderService.createModel(orderDTO);
			
		
		
		orderService.sendOrder(orderDTO);
		
		
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> getModelById(@PathVariable long id) {
    	
    	if(orderService.getModelById(id) !=null)
    	{
    		return new ResponseEntity<Order>(orderService.getModelById(id),HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<Order>(orderService.getModelById(id),HttpStatus.NOT_FOUND);
    	}
       
    }
	
	@DeleteMapping(value="/order/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteModelById(@PathVariable long id)
	{
		orderService.deleteModelById(id);
	}
	
	@PatchMapping(value="/order/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Order> updateOrderStatus(@RequestBody Order order,@PathVariable Long id)
	{
		Order updOrder = orderService.updateModelById(order, id);
		
		if(null != updOrder)
    	{
    		return new ResponseEntity<Order>(updOrder,HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<Order>(updOrder,HttpStatus.NOT_FOUND);
    	}
	}
	
	/*@GetMapping(value="/sendorder/{id}")
	public String sendOrder(@PathVariable Long id)
	{
		Order updOrder = orderService.getModelById(id);
		
		if(null != updOrder)
    	{
			orderService.sendOrder(updOrder);

			return "Message sent to the RabbitMQ JavaInUse Successfully";
    	}
		
		return "Completed";
	}*/

}
