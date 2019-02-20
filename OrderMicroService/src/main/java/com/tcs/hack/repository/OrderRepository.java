package com.tcs.hack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.tcs.hack.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	void deleteByOrderId(Long id);
	
	@Query("select a from Order a where a.customerID=?1")
	List<Order> findAllCustOrder(Long customerId);
	


}
