package com.retail.dao.couchbase.model;



import java.io.Serializable;
import com.couchbase.client.java.repository.annotation.Field;
import com.retail.util.RetailConstants;


public class Price implements Serializable{

	private static final long serialVersionUID = 1L;

	@Field("VALUE")
	private double value;

	@Field("CURRENCY_CODE")
	private String currency_code;

	@Field("TYPE")
	private String entityType=RetailConstants.ENTITY_PRICE;

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

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Override
	public String toString() {
		return "Price [value=" + value + ", currency_code=" + currency_code + ", entityType=" + entityType + "]";
	}
	
	

}
