package com.srtech.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.srtech.dto.CurrencyRequest;
import com.srtech.dto.CurrencyResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CurrencyControllerTest {
	
	@LocalServerPort
	private  int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@DisplayName("Test error message for invlid amount")
	@Test
	void testCurrencyConvert() {
		
	}
	
	
	@DisplayName("Test RuntimeException scenario for invlid currency type")
	@Test
	void testRuntimeExcpetionFrInvlidType() {
		CurrencyRequest currencyRequest = new CurrencyRequest();
		currencyRequest.setAmount(6250d);
		currencyRequest.setToCurrency("AUDacbs");
		ResponseEntity responseEntity=  restTemplate.postForEntity("http://localhost:"+port+"/api/v1/currency/convert", currencyRequest, CurrencyResponse.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	
	@DisplayName("Test AUD conversion scenario for valid request")
	@Test
	void testAUDCurrencyConvert1() {
		CurrencyRequest currencyRequest = new CurrencyRequest();
		currencyRequest.setAmount(6250d);
		currencyRequest.setToCurrency("AUD");
		ResponseEntity<CurrencyResponse> responseEntity =
				restTemplate.postForEntity("http://localhost:"+port+"/api/v1/currency/convert", currencyRequest, CurrencyResponse.class);
		assertAll(() -> {
			assertNotNull(responseEntity);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
			assertEquals(100,responseEntity.getBody().getConvertedValue());
			assertEquals(6250d,responseEntity.getBody().getAmount());
			assertNotNull(responseEntity.getBody().getConversionDate());
		});
	}
	
	@DisplayName("Test USD conversion scenario for valid request")
	@Test
	void testUSDCurrencyConvert1() {
		CurrencyRequest currencyRequest = new CurrencyRequest();
		currencyRequest.setAmount(8250d);
		currencyRequest.setToCurrency("USD");
		ResponseEntity<CurrencyResponse> responseEntity =
				restTemplate.postForEntity("http://localhost:"+port+"/api/v1/currency/convert", currencyRequest, CurrencyResponse.class);
		assertAll(() -> {
			assertNotNull(responseEntity);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
			assertEquals(100,responseEntity.getBody().getConvertedValue());
			assertEquals(8250d,responseEntity.getBody().getAmount());
			assertNotNull(responseEntity.getBody().getConversionDate());
		});
	}

}
