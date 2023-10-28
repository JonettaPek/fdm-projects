package com.example.fdmgroup.forexplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fdmgroup.forexplatform.model.Order;
import com.example.fdmgroup.forexplatform.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	public Order createNewOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public boolean orderSuccessfully(Order order) {
		orderRepository.save(order);
		return true;
	}
	
	public List<Order> retrieveAllOrdersByStatus(String status) {
		return orderRepository.findAllByStatus(status);
	}
	
	public List<Order> retrieveAllOrdersBySideType(String sideType) {
		return orderRepository.findAllBySideType(sideType);
	}
}
