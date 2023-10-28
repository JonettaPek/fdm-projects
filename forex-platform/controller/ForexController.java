package com.example.fdmgroup.forexplatform.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// API KEY: Hfl71punwpxdWTfBIFOH9UHf2cQpSahn

@Controller
public class ForexController {

//	@GetMapping("latest/?access_key=Hfl71punwpxdWTfBIFOH9UHf2cQpSahn")
//	public void retrieve() {
//		
//	}
	
//	@GetMapping("")
//	@ResponseBody
//	public void convertCurrency() {
//		
//	}
	
	@GetMapping("/test") 
	public void ConvertAmount() {
		
//	    OkHttpClient client = new OkHttpClient().newBuilder().build();
//	    
//	    Request request = new Request.Builder()
//	      .url("https://api.apilayer.com/fixer/convert?to=USD&from=SGD&amount=10")
//	      .addHeader("apikey", "Hfl71punwpxdWTfBIFOH9UHf2cQpSahn")
//	      .get()
//	      .build();
//	    
//	    Response response;
//	    String resultString;
//	    byte[] resultByteArray;
//	    Float amount;
//		try {
//			response = client.newCall(request).execute();
//			System.out.println("hi");
//			resultString = response.body().toString();
//			resultByteArray = resultString.getBytes();
//			ObjectMapper map = new ObjectMapper();
//			JsonNode rootNode = map.readTree(resultByteArray);
//			JsonNode resultNode = rootNode.get("result");
//			String amountString = map.treeToValue(resultNode, String.class);
//			amount = Float.parseFloat(amountString);
//			System.out.println(amount);
//			
//
//			
//		} catch (IOException e) {
//			e.printStackTrace();
		
		
	    OkHttpClient client = new OkHttpClient().newBuilder().build();
	    
	    Request request = new Request.Builder()
	      .url("https://api.apilayer.com/fixer/convert?to=USD&from=SGD&amount=10")
	      .addHeader("apikey", "Hfl71punwpxdWTfBIFOH9UHf2cQpSahn")
	      .get()
	      .build();
	    
	    Response response;
	    String result;
	    Float amount;
		try {
			response = client.newCall(request).execute();
			System.out.println("hi");
			result = response.body().toString();
			System.out.println(response.body().string());
//			ObjectMapper map = new ObjectMapper();
//			JsonFactory factory = map.getFactory();
//			JsonParser parser = factory.createParser(result);
//			JsonNode node = (JsonNode) map.readTree(parser).get("result");
//			amount = Float.parseFloat(map.treeToValue(node, String.class));
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
