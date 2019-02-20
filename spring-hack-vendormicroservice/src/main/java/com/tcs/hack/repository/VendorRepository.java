package com.tcs.hack.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.hack.model.Vendor;



public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	@Transactional
    Long deleteByVendorId(Long vendorID);

}
