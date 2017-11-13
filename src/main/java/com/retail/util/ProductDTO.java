package com.retail.util;

import java.io.Serializable;


public class ProductDTO  implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 private long id;
	 private String name;
	 private PriceDTO current_price;
	 
	 
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
	public PriceDTO getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(PriceDTO current_price) {
		this.current_price = current_price;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", current_price=" + current_price + "]";
	}
	
	
	
}
