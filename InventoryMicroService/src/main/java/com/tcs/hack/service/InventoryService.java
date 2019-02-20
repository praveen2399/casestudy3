package com.tcs.hack.service;

import java.util.List;

import com.tcs.hack.model.Inventory;




public interface InventoryService  {
	
	void deleteAllModels();
    void deleteModelById(Long id);

    void createModel(Inventory inventory);

    Inventory getModelById(Long id);

    List<Inventory> getAllModels();
    
    Inventory updateModelById(Inventory inventory,Long id);


}
