package com.tcs.hack.service;

import java.util.List;

import com.tcs.hack.model.Vendor;



public interface VendorService {
	
	void deleteAllModels();
    void deleteModelById(Long id);

    void createModel(Vendor vendor);

    Vendor getModelById(Long id);

    List<Vendor> getAllModels();
    
    Vendor updateModelById(Vendor vendor,Long id);

}
