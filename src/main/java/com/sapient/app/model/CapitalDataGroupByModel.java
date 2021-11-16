package com.sapient.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CapitalDataGroupByModel implements Comparable<CapitalDataGroupByModel> {
	private String country;
	private String gender;
	private Double income;

	public CapitalDataGroupByModel() {
		super();
	}

	public CapitalDataGroupByModel(String country, String gender, Double income) {
		super();
		this.gender = gender;
		this.country = country;
		this.income = income;
	}

	@Override
	public int hashCode() {

		return this.country.length() + this.gender.length();
	}

	@Override
	public boolean equals(Object obj) {

		CapitalDataGroupByModel other = (CapitalDataGroupByModel) obj;

		if (other.getCountry().equals(this.country) && other.getGender().equals(this.gender))
			return true;
		return false;
	}

	@Override
	public int compareTo(CapitalDataGroupByModel o) {
		int c;
		c = o.getCountry().compareTo(this.getCountry());
		if (c == 0)
			c = o.getGender().compareTo(this.getGender());
		if (c == 0)
			c = o.getIncome().compareTo(this.getIncome());
		return c;
	}
}
