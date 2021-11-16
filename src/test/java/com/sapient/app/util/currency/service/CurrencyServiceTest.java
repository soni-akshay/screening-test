package com.sapient.app.util.currency.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CurrencyServiceTest {

	@Autowired
	private CurrencyService _currencyService;

	@Test
	public void testForCurrencyExists() throws Exception {
		Assert.notNull(_currencyService.getCurrency("INR"), "INR currency should not be null.");
	}

}
