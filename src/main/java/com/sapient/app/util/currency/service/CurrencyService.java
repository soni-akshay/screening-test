package com.sapient.app.util.currency.service;

import com.sapient.app.util.currency.model.Currency;

public interface CurrencyService {
	Currency getCurrency(String symbol);
}
