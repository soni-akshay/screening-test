package com.sapient.app.util.currency.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currency {
	private String currencyCode;
	private BigDecimal baseRate;
	
	public Currency() {
		super();
	}
	
	public Currency(String currencyCode, BigDecimal baseRate) {
		this.baseRate = baseRate;
		this.currencyCode = currencyCode;
	}
}
