package com.tcs.hack.service;

import java.io.IOException;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tcs.hack.exception.BadResourceRequestException;
import com.tcs.hack.exception.NoSuchResourceFoundException;

import com.tcs.hack.model.Inventory;
import com.tcs.hack.model.OrderDTO;
import com.tcs.hack.repository.InventoryRepository;


@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	InventoryRepository invRepo;
	
	

    @Override
    public void deleteAllModels() {
    	invRepo.deleteAllInBatch();
    }

    @Override
    public void deleteModelById(Long id) {
    	
    	invRepo.deleteBySkuId(id);
    }

    @Override
    public void createModel(Inventory inv) {
        Inventory existingInv = invRepo.findOne(inv.getSkuId());

        if (existingInv != null) {
            throw new BadResourceRequestException("Model with same id exists.");
        }

        invRepo.save(inv);
    }

    @Override
    public Inventory getModelById(Long id) {
        Inventory inv = invRepo.findOne(id);

        if (inv == null) {
            throw new NoSuchResourceFoundException("No model with given id found.");
        }

        return inv;
    }

    @Override
    public List<Inventory> getAllModels() {
        return invRepo.findAll();
    }

	@Override
	public Inventory updateModelById(Inventory inv,Long id) {
		
		Inventory tempInv=invRepo.findOne(id);
			
		if(null != tempInv)
		{
			tempInv.setInventoryOnHand(inv.getInventoryOnHand());
			tempInv.setMinQtyReq(inv.getMinQtyReq());
			tempInv.setPrice(inv.getPrice());
			tempInv.setProductLabel(inv.getProductLabel());
			tempInv.setProductName(inv.getProductName());
			
			invRepo.save(tempInv);
			return tempInv;
		}
		
		return null;
	}
/*	
	@RabbitListener(queues="${com.tcs.hack.rabbitmq.queue}")
	public void recieveEmail(OrderDTO order) 
	{
		System.out.println("Recieved Message From RabbitMQ in Inventory: "+order.getCustomerID()); 
		
		Inventory tempInv=invRepo.findOne(order.getSkuId());
		
		if(null != tempInv)
		{
			tempInv.setInventoryOnHand(tempInv.getInventoryOnHand()-order.getItemQty());			
			
			invRepo.save(tempInv);
			
		}
		
		//sendmail("pk2399user@gmail.com",order.getOrderId(),order.getOrderStatus());
	}*

	
	public boolean isProductAvailable(Long skuId)
	{
		 Inventory existingInv = invRepo.findOne(skuId);
		
		if( existingInv != null && existingInv.getInventoryOnHand()>0)
				{
			return true;
				}
		return false;
	}

    

}
