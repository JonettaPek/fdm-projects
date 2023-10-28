package com.example.fdmgroup.forexplatform.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fdmgroup.forexplatform.exception.TraderNotFoundException;
import com.example.fdmgroup.forexplatform.model.Trader;
import com.example.fdmgroup.forexplatform.model.Portfolio;
import com.example.fdmgroup.forexplatform.model.Wallet;
import com.example.fdmgroup.forexplatform.repository.TraderRepository;

@Service
public class TraderService {

	private final TraderRepository traderRepository;

	@Autowired
	public TraderService(TraderRepository traderRepository) {
		super();
		this.traderRepository = traderRepository;
	}
	
	public boolean emailDoesNotExist(String email) {
		return traderRepository.findByEmail(email).isEmpty();
	}
	
	public boolean emailExists(String email) {
		return traderRepository.findByEmail(email).isPresent();
	}
	
	public boolean correctPassword(String email, String password) {
		Optional<Trader> retrievedTrader = traderRepository.findByEmail(email);
		if (retrievedTrader.isPresent()) {
			return retrievedTrader.get().getPassword().equals(password);
		}
		return false;
	}
	
	public boolean registerSuccessfully(Trader trader, String email, String password, String confirmPassword) {
		if (emailDoesNotExist(email) && password.equals(confirmPassword)) {
			traderRepository.save(trader);
			return true;
		}
		return false;
	}
	
	public boolean loginSuccessfully(String email, String password) {
		if (emailExists(email) && correctPassword(email, password)) {
			return true;
		}
		return false;
	}
	
	public long retrieveIdByEmail(String email) {
		Optional<Trader> retrievedTrader = traderRepository.findByEmail(email);
		if (retrievedTrader.isPresent()) {
			return retrievedTrader.get().getTraderID();
		}
		return 0L;
	}
	
	public Trader retrieveTraderById(Long traderID) throws TraderNotFoundException {
		Optional<Trader> retrievedTrader = traderRepository.findById(traderID);
		if (retrievedTrader.isPresent()) {
			return retrievedTrader.get();
		}
		throw new TraderNotFoundException("Trader with ID " + traderID + " does not exist");
	}
	
	public Trader retrieveTraderByEmail(String email) throws TraderNotFoundException {
		Optional<Trader> retrievedTrader = traderRepository.findByEmail(email);
		if (retrievedTrader.isPresent()) {
			return retrievedTrader.get();
		}
		throw new TraderNotFoundException("Trader with email " + email + " does not exist");
	}
	
	public void updatePortfolioAndWalletId(Trader trader, Portfolio portfolio, Wallet wallet) {
		Optional<Trader> retrievedTrader = traderRepository.findById(trader.getTraderID());
		if (retrievedTrader.isPresent()) {
			trader.setPortfolio(portfolio);
			trader.setWallet(wallet);
			traderRepository.save(trader);
		}
	}
	
	public Trader updateTrader(Trader trader) {
		return traderRepository.save(trader);
	}
}