package com.srtech.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtech.dto.CurrencyRequest;
import com.srtech.dto.CurrencyResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CurrencyController {

	private double usd_converstion_date=82.5;
	private double aud_converstion_date=62.5;
	
	@GetMapping("/currency/support/currencies")
	public List<String> getSupportesCurrencies(){
		log.debug("REST call for getting the supported currencies");
		
		/*List<String> list=new ArrayList<>();
		list.add("INR->USD");
		list.add("INR->AUD");
		return list;*/
		
		return List.of("INR->USD","INR->AUD");
	}
	
	
	@PostMapping("/currency/convert")
	public ResponseEntity<CurrencyResponse> convert(@RequestBody CurrencyRequest currencyRequest) {
		log.debug("REST call for Currency COnversion and The request is {}",currencyRequest);
		double totalValue=0;
		
		if(currencyRequest.getToCurrency().equals("USD")) {
			totalValue= currencyRequest.getAmount() / usd_converstion_date;
		}else if("AUD".equals(currencyRequest.getToCurrency())) {
			totalValue= currencyRequest.getAmount() / aud_converstion_date;
		}else {
			throw new RuntimeException("Invalid Currency Type");
		}
		
		CurrencyResponse currencyResponse = new CurrencyResponse();
		currencyResponse.setFromCurrency("INR");
		currencyResponse.setAmount(currencyRequest.getAmount());
		currencyResponse.setConvertedValue(totalValue);
		currencyResponse.setToCurrency(currencyRequest.getToCurrency());
		currencyResponse.setConversionDate(new Date());
		
		return new ResponseEntity<CurrencyResponse>(currencyResponse,HttpStatus.OK);
	}
	
}
