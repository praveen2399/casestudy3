package com.tcs.hack.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.tcs.hack.model.Inventory;

@Component
public class Processor implements ItemProcessor<Inventory, Inventory> {

	@Override
	public Inventory process(Inventory inventory) throws Exception {
		
		
		System.out.println("Inventory Read "+inventory.getSkuId());
		
		return inventory;
	}
	
	

}
