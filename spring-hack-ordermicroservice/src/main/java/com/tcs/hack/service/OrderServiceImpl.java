package com.tcs.hack.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tcs.hack.exception.BadResourceRequestException;
import com.tcs.hack.exception.NoSuchResourceFoundException;
import com.tcs.hack.model.Order;
import com.tcs.hack.model.OrderDTO;
import com.tcs.hack.model.OrderLineItem;
import com.tcs.hack.repository.OrderLineRepository;
import com.tcs.hack.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderLineRepository orderLineRepo;
	
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${com.tcs.hack.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${com.tcs.hack.rabbitmq.routingkey}")
	private String routingkey;
	
	  @Override
	    public void deleteAllModels() {
	    	orderRepo.deleteAllInBatch();
	    }

	    @Override
	    @Transactional
	    public void deleteModelById(Long id) {
	    	 orderRepo.deleteByOrderId(id);
	    }

	    @Override
	    public Order createModel(OrderDTO orderDTO) {
	    	
	    	Order order=new Order();
			
			order.setCustomerID(orderDTO.getCustomerID());
			order.setIsCod(orderDTO.getIsCod());
			order.setOrderCreatedOn(orderDTO.getOrderCreatedOn());
			//order.setOrderId(new Long("123"));
			order.setOrderStatus("Intiated");
			//order.setOrderId(orderDTO.getOrderId());
			order.setTotalAmount(orderDTO.getTotalAmount());
			order.setShippingAddress(orderDTO.getShippingAddress());
			order.setPaymentChannel(orderDTO.getPaymentChannel());
			
			OrderLineItem orderLineItem=new OrderLineItem();
			orderLineItem.setItemQty(orderDTO.getItemQty());
			orderLineItem.setSkuId(orderDTO.getSkuId());
			
			List<OrderLineItem> orderLineItemlst=new ArrayList<OrderLineItem>();
			orderLineItemlst.add(orderLineItem);
			
			order.setOrderLineItemlst(orderLineItemlst);
			
			//order.setOrderLineItemlst(orderLineItem);
			
	    	
	       /* Order existingOrder = orderRepo.findOne(order.getOrderId());

	        if (existingOrder != null) {
	            throw new BadResourceRequestException("Model with same id exists.");
	        }*/

	       orderRepo.save(order);
	       
	       sendOrder(orderDTO);
	       
	       return order;
	   
	    }

	    @Override
	    public Order getModelById(Long id) {
	        Order order = orderRepo.findOne(id);

	        if (order == null) {
	            throw new NoSuchResourceFoundException("No model with given id found.");
	        }

	        return order;
	    }

	    @Override
	    public List<Order> getAllModels() {
	        return orderRepo.findAll();
	    }

		@Override
		public Order updateModelById(Order order,Long id) {
			
			Order tempOrder=orderRepo.findOne(id);
				
			if(null != tempOrder)
			{
				tempOrder.setIsCod(order.getIsCod());
				tempOrder.setOrderCreatedOn(order.getOrderCreatedOn());
				tempOrder.setOrderStatus(order.getOrderStatus());
				tempOrder.setPaymentChannel(order.getPaymentChannel());
				tempOrder.setShippingAddress(order.getShippingAddress());
				tempOrder.setTotalAmount(order.getTotalAmount());
				
				orderRepo.save(tempOrder);
				return tempOrder;
			}
			
			return null;
		}
		
		public List<Order> findAllOrdersCust(Long customerId)
		{
			return orderRepo.findAllCustOrder(customerId);
		}

		@Override
		public void sendOrder(OrderDTO order) {
			
			rabbitTemplate.convertAndSend(exchange, routingkey, order);
			System.out.println("Send msg = " + order);
			
		}

		@Override
		public OrderLineItem createOrderLineItem(OrderLineItem orderLineItem) {
			
			orderLineRepo.save(orderLineItem);
			
			return null;
		}
		

}
