package com.fdgroup.OOD3Assessment.CurrencyConverter;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {

	public static void main(String[] args) {
		
		Logger log = LogManager.getLogger();
		
		ArrayList<Transaction> transactions = DatabaseReader.getTransactions(".\\src\\main\\resources\\transactions.txt");
		
		User[] users = DatabaseReader.deserialiseUserObjects(".\\src\\main\\resources\\users.json");
		
		User[] updatedUsers = DatabaseReader.deserialiseUserObjects(".\\src\\main\\resources\\users.json");
		
		
		String nameT;
		String fromCurrencyT;
		String toCurrencyT;
		double amountToConvertT;
		
		User userOfInterest;
		
		String nameU;
		Map<String, Double> walletU;
		double fromCurrencyAmountU;
		double toCurrencyAmountU;
	
		
		toNextTransaction: for ( Transaction transaction : transactions ) {
			
			nameT = transaction.getUser().getName();
			fromCurrencyT = transaction.getFromCurrency();
			toCurrencyT = transaction.getToCurrency();
			amountToConvertT = transaction.getAmountToConvert();
			
			userOfInterest = new User();
			
			setUserLoop: for ( User user : users ) {
				if ( user.getName().equals(nameT) ) {
					userOfInterest = user;
					break setUserLoop;
				}
			}
			
			nameU = userOfInterest.getName();
			walletU = userOfInterest.getWallet();
			
			String message = "Transaction request has been skipped.";

			if ( nameU == null ) {
				if ( log.isInfoEnabled() ) {
					log.info("This user ({}) doesn't exist. {}", nameT, message );
				}
				continue toNextTransaction;
			} else if( fromCurrencyT.equals(toCurrencyT) ) {
				if ( log.isInfoEnabled() ) {
					log.info("This user ({}) is requesting to convert to the same currency ({}). {}", nameT, toCurrencyT, message );
				}
				continue toNextTransaction;
			} else if( ! walletU.containsKey(fromCurrencyT) ) {
				if ( log.isInfoEnabled() ) {
					log.info("This user ({}) is requesting to convert from a currency ({}) s/he doesn't have. {}", nameT, fromCurrencyT, message );
				}
				continue toNextTransaction;
			} else if ( userOfInterest.getCurrencyAmount(fromCurrencyT) < amountToConvertT ) {
				if ( log.isInfoEnabled() ) {
					log.info("This user ({}) is requesting to convert from a currency ({}) with insufficient value. {}", nameT, fromCurrencyT, message );
				}
				continue toNextTransaction;
			}
			
			fromCurrencyAmountU = userOfInterest.getCurrencyAmount(fromCurrencyT);

			if ( walletU.containsKey(fromCurrencyT)
			  && fromCurrencyAmountU >= amountToConvertT ) {
				
				if ( ! walletU.containsKey(toCurrencyT) ) {
					userOfInterest.addCurrency(toCurrencyT, 0.0);
				}
				
				toCurrencyAmountU = userOfInterest.getCurrencyAmount(toCurrencyT);
				
				double newFromCurrencyAmount = TransactionProcessor.deduct(fromCurrencyAmountU, amountToConvertT);
				double toCurrencyAmount;
				double newToCurrencyAmount;
				
				if ( ! fromCurrencyT.equals("usd")
				  && ! toCurrencyT.equals("usd") ) {
		
					double usdAmount = Converter.convert(fromCurrencyT, "usd", amountToConvertT);
					toCurrencyAmount = Converter.convert("usd", toCurrencyT, usdAmount);
				} else {
					toCurrencyAmount = Converter.convert(fromCurrencyT, toCurrencyT, amountToConvertT);	
				}
				
				if ( toCurrencyAmount == 0 ) {
					if ( log.isErrorEnabled() ) {
						log.error("Conversion rate for the currency {} and/or {} cannot be found.", fromCurrencyT, toCurrencyT);
						continue toNextTransaction;
					}
				}
				newToCurrencyAmount = TransactionProcessor.add(toCurrencyAmountU, toCurrencyAmount);
				userOfInterest.addCurrency(fromCurrencyT, newFromCurrencyAmount);
				userOfInterest.addCurrency(toCurrencyT, newToCurrencyAmount);
				
				if ( log.isInfoEnabled() ) {
					log.info("Transaction for this user ({}) is completed. {} {} has been converted to {} {}. Balance is {} {}.", nameT, fromCurrencyT, amountToConvertT, toCurrencyT, newToCurrencyAmount, fromCurrencyT, newFromCurrencyAmount );
				}
				
			}
			
			for ( int i = 0; i < users.length; i++ ) {
				if ( users[i].getName().equals(nameU) ) {
					updatedUsers[i] = userOfInterest;
				}
			}
		}
		
		DatabaseWriter.serialiseUserObjects(updatedUsers);
		

		
	}
}

