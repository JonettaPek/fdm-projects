package com.example.fdmgroup.forexplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fdmgroup.forexplatform.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	public List<Order> findAllByStatus(String status);
	public List<Order> findAllBySideType(String sideType);
}
