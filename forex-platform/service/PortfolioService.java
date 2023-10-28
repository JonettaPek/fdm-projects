package com.example.fdmgroup.forexplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fdmgroup.forexplatform.model.Portfolio;
import com.example.fdmgroup.forexplatform.repository.PortfolioRepository;

@Service
public class PortfolioService {

	private final PortfolioRepository portfolioRepository;

	@Autowired
	public PortfolioService(PortfolioRepository portfolioRepository) {
		super();
		this.portfolioRepository = portfolioRepository;
	}
	
	public void createNewPortfolio(Portfolio portfolio) {
		portfolioRepository.save(portfolio);
	}
}
