package com.example.fdmgroup.forexplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fdmgroup.forexplatform.model.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long>{

	@Query("SELECT b FROM Balance b WHERE b.wallet.walletID LIKE :walletID AND b.currencyCode LIKE :currencyCode")
	public Optional<Balance> findByWalletIdAndCurrencyCode(@Param("walletID") long walletID, @Param("currencyCode") String currencyCode);
}
