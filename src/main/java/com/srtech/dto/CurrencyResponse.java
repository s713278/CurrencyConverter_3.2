package com.srtech.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CurrencyResponse {

	private Double amount;
	private String fromCurrency;
	private String toCurrency;
	private Double convertedValue;
	private Date conversionDate;
	
}
