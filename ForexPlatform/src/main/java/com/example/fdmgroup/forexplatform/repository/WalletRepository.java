package com.example.fdmgroup.forexplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fdmgroup.forexplatform.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

	@Query("SELECT w FROM Wallet w WHERE w.trader.traderID LIKE :traderID")
	public Optional<Wallet> findByTraderId(@Param("traderID") long traderID);
}
