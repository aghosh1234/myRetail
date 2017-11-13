package com.retail.dao.service;

import com.retail.exception.RetailException;
import com.retail.util.ProductDTO;

public interface RetailDaoService {
	
	public void saveProduct(Object product) throws RetailException;
	
	public ProductDTO getProduct(long id) throws RetailException;
}