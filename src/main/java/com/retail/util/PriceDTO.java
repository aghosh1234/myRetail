package com.retail.util;

import java.io.Serializable;

public class PriceDTO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double value;
	private String currency_code;
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	@Override
	public String toString() {
		return "PriceDTO [value=" + value + ", currency_code=" + currency_code + "]";
	}
	
}
