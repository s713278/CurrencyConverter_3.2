package com.srtech.dto;

import lombok.Data;

@Data
public class CurrencyRequest {

	private Double amount;
	private String toCurrency;
	
}
