package com.example.fdmgroup.forexplatform.repository;

import org.springframework.stereotype.Repository;

import com.example.fdmgroup.forexplatform.model.Trader;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {

	Optional<Trader> findByEmail(String email);
}
