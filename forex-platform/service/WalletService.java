package com.example.fdmgroup.forexplatform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fdmgroup.forexplatform.exception.WalletNotFoundException;
import com.example.fdmgroup.forexplatform.model.Wallet;
import com.example.fdmgroup.forexplatform.repository.WalletRepository;

@Service
public class WalletService {

	private final WalletRepository walletRepository;

	@Autowired
	public WalletService(WalletRepository walletRepository) {
		super();
		this.walletRepository = walletRepository;
	}
	
	public Wallet createNewWallet(Wallet wallet) {
		return walletRepository.save(wallet);
	}
	
	public Wallet retrieveWalletByTraderId(long traderID) throws WalletNotFoundException {
		Optional<Wallet> retrievedWallet = walletRepository.findByTraderId(traderID);
		if (retrievedWallet.isPresent()) {
			return retrievedWallet.get();
		}
		throw new WalletNotFoundException("Wallet with trader ID " + traderID + " not found");
	}
	
	public Wallet updateWallet(Wallet wallet) {
		return walletRepository.save(wallet);
	}
}
