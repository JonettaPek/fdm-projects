package com.fdgroup.OOD3Assessment.CurrencyConverter;

/**
 * <h1>public class Converter</h1>
 * <p>This class has the sole function of performing conversion calculations, converting an amount from or to USD</p>
 * @author Jonetta
 * @version 0.0.1
 */
public class Converter {

	/**
	 * <p>Converts an amount from or to usd and returns it.
	 * Call this method twice if both fromCurrency and toCurrency are not USD</p>
	 * @param fromCurrency a String value that contains the currency to be converted from
	 * @param toCurrency a String value that contains the currency to be converted to
	 * @param amountToConvert a double value that represents the amount to be converted
	 * @return a double value that represents the amount in the target currency after conversion
	 * @see Currency
	 */
	public static double convert(String fromCurrency, String toCurrency, double amountToConvert) {
		try {
			if ( toCurrency.equals("usd") ) {
				Currency currency = DatabaseReader.deserialiseCurrencyObject(".\\src\\main\\resources\\fx_rates.json", fromCurrency);
				return amountToConvert * currency.getInverseRate();
			} else if ( fromCurrency.equals("usd") ) {
				Currency currency = DatabaseReader.deserialiseCurrencyObject(".\\src\\main\\resources\\fx_rates.json", toCurrency);
				return amountToConvert * currency.getRate();
			} else {
				System.out.println("Trying to convert to or from a currency other than USD");
				return 0;
			}
		} catch ( CurrencyNotFoundException cnfe ) {
			System.out.println(cnfe.getMessage());
			return 0;
		}
	}
	

}
