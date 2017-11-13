package com.retail.dao.service;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.retail.dao.couchbase.dao.RetailCouchbaseDao;
import com.retail.dao.couchbase.model.Price;
import com.retail.dao.couchbase.model.Product;
import com.retail.exception.RetailException;
import com.retail.exception.RetailExceptionCodes;
import com.retail.util.PriceDTO;
import com.retail.util.ProductDTO;
import com.retail.util.RetailUtil;

@Service("retailDaoService")
public class RetailDaoServiceImpl implements RetailDaoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RetailDaoServiceImpl.class);
	
	@Autowired
	@Qualifier("retailCouchbaseDao")
	RetailCouchbaseDao retailCouchbaseDao;
	
	@Autowired
	@Qualifier("retailUtil")
	RetailUtil retailUtil;

	@Override
	public void saveProduct(Object productObj) throws RetailException {
		LOGGER.debug("Entering saveProduct");
		try {
			ProductDTO productDTO = (ProductDTO) productObj;
			Product product= new Product();
			Price price= new Price();
			BeanUtils.copyProperties(product, productDTO);
			if(productDTO!=null && productDTO.getCurrent_price()!=null){
				BeanUtils.copyProperties(price, productDTO.getCurrent_price());
			}
			product.setPrice(price);
			retailCouchbaseDao.save(product);
			
		} catch(Exception e) {
			LOGGER.error("Exception in saveProduct " + e);
			throw new RetailException(RetailExceptionCodes.INTERNAL_DATA_STORE_EXCEPTION);
		} catch(Throwable th) {
			LOGGER.error("Throwable in saveProduct " + th);
			throw new RetailException(RetailExceptionCodes.INTERNAL_DATA_STORE_EXCEPTION);
		}
		LOGGER.debug("Exiting saveProduct");
	}
	
	@Override
	public ProductDTO getProduct(long id) throws RetailException {
		LOGGER.debug("Entering getProduct");
		Product product=null;
		ProductDTO productDTO=null;
		PriceDTO priceDTO=null;
		try {						
			 product =retailCouchbaseDao.findOne(id);
			 if(product!=null){
				 productDTO=new ProductDTO();
				 priceDTO=new PriceDTO();
				 BeanUtils.copyProperties(productDTO, product);
				 if(product!=null && product.getPrice()!=null){
					 BeanUtils.copyProperties(priceDTO, product.getPrice());
				 }			 
				 if(productDTO!=null && priceDTO!=null && priceDTO.getValue()>0) 
					 productDTO.setCurrent_price(priceDTO);
			 }
		} catch(Exception e) {
			LOGGER.error("Exception in getProduct " + e);
			 throw new RetailException(RetailExceptionCodes.INTERNAL_DATA_STORE_EXCEPTION);
		} catch(Throwable th) {
			LOGGER.error("Throwable in getProduct " + th);
			 throw new RetailException(RetailExceptionCodes.INTERNAL_DATA_STORE_EXCEPTION);
		}
		LOGGER.debug("Exiting getProduct");
		if(product==null)
			 throw new RetailException(RetailExceptionCodes.PRODUCT_NOT_FOUND_IN_DATA_STORE);
		return productDTO;
	}
 
}
