package com.example.fdmgroup.forexplatform.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fdmgroup.forexplatform.exception.FixerIOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class GeneralService {

	private final String apiAccessKey = new String("Hfl71punwpxdWTfBIFOH9UHf2cQpSahn");
	
	public List<String> retrieveSymbols() throws FixerIOException {
	    OkHttpClient client = new OkHttpClient().newBuilder().build();
	    Request request = new Request.Builder()
	      .url("https://api.apilayer.com/fixer/symbols")
	      .addHeader("apikey", apiAccessKey)
	      .build();
	    Response response;
		try {
			response = client.newCall(request).execute();
			String responseString = response.body().string();
			JsonReader reader = Json.createReader(new StringReader(responseString));
			JsonObject rootJsonObject = reader.readObject();
			JsonObject symbolJsonObject = rootJsonObject.getJsonObject("symbols");
			List<String> keys = symbolJsonObject.keySet().stream().collect(Collectors.toList());
			return keys;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new FixerIOException("Fail to retrieve 3-letter codes from fixer.io symbols endpoint ");
	}
	
	public float convert(String fromCurrency, String toCurrency, float amount) throws FixerIOException {
	    OkHttpClient client = new OkHttpClient().newBuilder().build();
	    Request request = new Request.Builder()
	      .url("https://api.apilayer.com/fixer/convert?to="
	    		  + toCurrency.toUpperCase()
	    		  + "&from=" 
	    		  + fromCurrency.toUpperCase() 
	    		  + "&amount=" 
	    		  + amount)
	      .addHeader("apikey", apiAccessKey)
	      .get()
	      .build();
	    Response response;
		try {
			response = client.newCall(request).execute();
			String responseString = response.body().string();
			byte[] responseByteArray = responseString.getBytes();
			ObjectMapper map = new ObjectMapper();
			JsonNode rootNode = map.readTree(responseByteArray);
			JsonNode resultNode = rootNode.get("result");
			String amountString = map.treeToValue(resultNode, String.class);
			float retrievedAmount = Float.parseFloat(amountString);
			return retrievedAmount;
		} catch (IOException e) {
			e.printStackTrace();
		}
//		if (fromCurrency.equals(toCurrency)) {
//			throw new FixerIOException("Fail to convert " + fromCurrency + " " + amount + " to " + toCurrency);
//		}
		throw new FixerIOException("Fail to convert " + fromCurrency + " " + amount + " to " + toCurrency);
	}
	
	public float retrieveCurrencyRate(String withGetCurrency, String buySellCurrency) throws FixerIOException {
	    OkHttpClient client = new OkHttpClient().newBuilder().build();

	    Request request = new Request.Builder()
	      .url("https://api.apilayer.com/fixer/latest?symbols="
	    		  + withGetCurrency
	    		  + "&base="
	    		  + buySellCurrency)
	      .addHeader("apikey", "Hfl71punwpxdWTfBIFOH9UHf2cQpSahn")
	      .get()
	      .build();
	    Response response;
		try {
			response = client.newCall(request).execute();
			String responseString = response.body().string();
			byte[] responseByteArray = responseString.getBytes();
			ObjectMapper map = new ObjectMapper();
			JsonNode rootNode = map.readTree(responseByteArray);
			JsonNode ratesNode = rootNode.get("rates");
			JsonNode resultNode = ratesNode.get(withGetCurrency);
			String amountString = map.treeToValue(resultNode, String.class);
			float retrievedAmount = Float.parseFloat(amountString);
			return retrievedAmount;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new FixerIOException("Fail to retrieve currency rate");
	}
}
