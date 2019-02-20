package com.tcs.hack.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tcs.hack.model.Order;
import com.tcs.hack.model.OrderDTO;
import com.tcs.hack.model.OrderLineItem;


public interface OrderService {
	
	
	void deleteAllModels();
	@Transactional
    void deleteModelById(Long id);

    Order createModel(OrderDTO orderDTO);

    Order getModelById(Long id);

    List<Order> getAllModels();
    
    Order updateModelById(Order order,Long id);
    
    List<Order> findAllOrdersCust(Long customerId);
    
   
    void sendOrder(OrderDTO order);
    
    OrderLineItem createOrderLineItem(OrderLineItem orderLineItem);


}
