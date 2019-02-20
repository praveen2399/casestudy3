package com.tcs.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.hack.model.OrderLineItem;

public interface OrderLineRepository extends JpaRepository<OrderLineItem, Long> {

}
