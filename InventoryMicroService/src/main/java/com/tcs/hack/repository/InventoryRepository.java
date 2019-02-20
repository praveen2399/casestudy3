package com.tcs.hack.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.hack.model.Inventory;



public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	@Transactional
    Long deleteBySkuId(Long skuID);
	
	Inventory findBySkuId(Long skuID);
	
	

}
