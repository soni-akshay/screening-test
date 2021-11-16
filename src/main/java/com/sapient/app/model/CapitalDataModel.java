package com.sapient.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CapitalDataModel {
	private String city;
	private String country;
	private String gender;
	private String currency;
	private Double income;

	public CapitalDataModel(String city, String country, String gender, String currency, Double income) {
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.currency = currency;
		this.income = income;
	}
}
