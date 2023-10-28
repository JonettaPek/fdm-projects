package com.example.fdmgroup.forexplatform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fdmgroup.forexplatform.model.Balance;
import com.example.fdmgroup.forexplatform.repository.BalanceRepository;

@Service
public class BalanceService {

	private final BalanceRepository balanceRepository;

	@Autowired
	public BalanceService(BalanceRepository balanceRepository) {
		super();
		this.balanceRepository = balanceRepository;
	}
	
	public Balance createNewBalance(Balance balance) {
		return balanceRepository.save(balance);
	}
	
	public void deleteBalanceByWalletIdAndCurrencyCode(Balance balance, long walletID, String currencyCode) {
		Optional<Balance> retrievedBalance = balanceRepository.findByWalletIdAndCurrencyCode(walletID, currencyCode);
		if (retrievedBalance.isPresent()) {
			balanceRepository.delete(balance);
		}
	}
}
