package com.sapient.app.util.currency.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.sapient.app.util.currency.model.Currency;
import com.sapient.app.util.currency.service.CurrencyService;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {

	private Map<String, Currency> _currencyCache;

	@PostConstruct
	public void init() {
		// Static Data. Can be done from a external source or somewhere.
		this._currencyCache = new HashMap<>();
		this._currencyCache.put("INR", new Currency("INR", new BigDecimal(0.013)));
		this._currencyCache.put("USD", new Currency("USD", new BigDecimal(1)));
		this._currencyCache.put("HKD", new Currency("HKD", new BigDecimal(0.13)));
		this._currencyCache.put("SGP", new Currency("SGP", new BigDecimal(0.74)));
		this._currencyCache.put("GBP", new Currency("GBP", new BigDecimal(1.34)));
	}

	@Override
	public Currency getCurrency(String symbol) {
		return this._currencyCache.get(symbol);
	}

}
