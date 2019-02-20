package com.tcs.hack.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcs.hack.model.Inventory;
import com.tcs.hack.repository.InventoryRepository;

@Component
public class DBWriter implements ItemWriter<Inventory> {

	@Autowired
	InventoryRepository invRepo;
	
	@Override
	public void write(List<? extends Inventory> inventory) throws Exception {
		invRepo.save(inventory);
		
	}

	
}
