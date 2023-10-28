package com.example.fdmgroup.forexplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fdmgroup.forexplatform.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

}
