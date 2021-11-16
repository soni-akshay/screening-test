package com.sapient.app.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean(name = "exchangeRestTemplate")
	public RestTemplate exchangeRestTemplate() {
		return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(5000)).build();
	}
}
