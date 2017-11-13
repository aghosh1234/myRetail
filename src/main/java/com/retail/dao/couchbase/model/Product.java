package com.retail.dao.couchbase.model;



import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import com.couchbase.client.java.repository.annotation.Field;
import com.retail.util.RetailConstants;

@Document
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;

	@Field("NAME")
	private String name;

	@Field("PRICE")
	private Price price;
	
	@Field("TYPE")
	private String entityType=RetailConstants.ENTITY_PRODUCT;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", entityType=" + entityType + "]";
	}

	
	
}
