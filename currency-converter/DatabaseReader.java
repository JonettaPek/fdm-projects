package com.fdgroup.OOD3Assessment.CurrencyConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <h1>public class DatabaseReader</h1>
 * <p>This class has the sole function of deserialising/reading from three main databases 
 * - users.json file (User objects), fx_rates.json file (Currency Objects) and transactions.txt file.</p>
 * @author Jonetta
 * @version 0.0.1
 */
public class DatabaseReader {

	/**
	 * <p>Deserialise an array of User objects in a json file format so that their fields (name, wallet) can be accessed</p>
	 * @param filepath - a relative path to the users.json file
	 * @return an array of User Objects
	 * @see User
	 */
	public static User[] deserialiseUserObjects(String filepath) {

		try {
			ObjectMapper map = new ObjectMapper();
			User[] users = map.readValue(new File(filepath), User[].class);
			return users;
			
		} catch ( FileNotFoundException fnfe ) {
			System.out.println("File provided - " + filepath + " - does not exist. Please provide another file");
			
		} catch( IOException ioe ) {
			System.out.println("File exists.");
			ioe.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * <p>Deserialise one specific Currency object from a set so that its fields (rate, inverseRate) can be accessed</p>
	 * @param filepath - a relative path to fx_rates.json file
	 * @param currency - the currency to be converted or the currency to convert to
	 * @return a Currency object
	 * @throws CurrencyNotFoundException
	 * @see Currency
	 * @see CurrencyNotFoundException
	 */
	public static Currency deserialiseCurrencyObject(String filepath, String currency) throws CurrencyNotFoundException  {
		String currencyToLowerCase = currency.toLowerCase();
		try {
			InputStream fileBytes = new FileInputStream(filepath);
			ObjectMapper map = new ObjectMapper();
			JsonNode rootNode = map.readTree(fileBytes);
			JsonNode currencyNode = rootNode.get(currencyToLowerCase);
			Currency currencyObject = map.treeToValue(currencyNode, Currency.class);
			if ( currencyObject == null ) {
				throw new CurrencyNotFoundException("Currency not found.");
			}
			return currencyObject;

		} catch ( FileNotFoundException fnfe ) {
			System.out.println("File provided - " + filepath + " - does not exist. Please provide another file");
			
		} catch( IOException ioe ) {
			System.out.println("File exists.");
			ioe.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * <p>Read from transactions.txt file and create a list of Transaction objects from the character stream</p>
	 * @param filepath - relative path to transactions.txt file
	 * @return a list of Transaction objects
	 * @see Transaction
	 */
	public static ArrayList<Transaction> getTransactions(String filepath) {
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
			String line = br.readLine(); 
			while ( line != null ) {
				String[] transactionInfo = null;
				transactionInfo = line.split(" ");
				Transaction transaction = new Transaction();
				transaction.getUser().setName(transactionInfo[0]);
				transaction.setFromCurrency(transactionInfo[1]);
				transaction.setToCurrency(transactionInfo[2]);
				if ( isNumeric(transactionInfo[3]) ) {
					transaction.setAmountToConvert(Double.parseDouble(transactionInfo[3]));
				} else {
					Logger log = LogManager.getLogger();
					if ( log.isInfoEnabled() ) {
						log.info("This user ({}) provided an invalid amount ({}) to convert.", transactionInfo[0], transactionInfo[3]);
					}
					line = br.readLine();
					continue;
				}
				transactions.add(transaction);
				line = br.readLine();
			}
		} catch ( FileNotFoundException fnfe ) {
			System.out.println("File provided - " + filepath + " - does not exist. Please provide another file");
			
		} catch( IOException ioe ) {
			System.out.println("File exists.");
			ioe.printStackTrace();
		}
		
		return transactions;
		
	}
	
	/**
	 * <p>Check if the amount to be converted is a valid number</p>
	 * @param amountToConvert
	 * @return true if the amount to convert is a valid number, otherwise, returns false
	 */
	public static boolean isNumeric(String amountToConvert) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(amountToConvert);
		if ( m.matches() )  {
			return true;
		} else {
			return false;
		}
	}

}
