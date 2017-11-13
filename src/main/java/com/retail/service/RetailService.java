package com.retail.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.retail.dao.service.RetailDaoService;
import com.retail.exception.RetailException;
import com.retail.exception.RetailExceptionCodes;
import com.retail.util.ProductDTO;
import com.retail.util.RetailConstants;
import com.retail.util.RetailUtil;

@Service
public class RetailService {
	 private static Logger LOGGER = LoggerFactory.getLogger(RetailService.class);
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    RetailDaoService retailDaoService;
    
	@Autowired
	@Qualifier("retailUtil")
	RetailUtil retailUtil;

    
    public ProductDTO getProduct(long productId) throws RetailException {
    	LOGGER.debug("Entering getProduct");
        ProductDTO productDTO= null;
        String productName=null;
        try{
        	productDTO =retailDaoService.getProduct(productId);
        }catch(RetailException re){
        	throw re;
        }
        try{
        	productName=getProductName(String.valueOf(productDTO.getId()));
        }catch(RetailException re){
        	LOGGER.error("Exception occured to get the product name:: "+re.getErrorMsg());
        }
        if(StringUtils.isEmpty(productName))
        	productDTO.setName("No Product Description Found");
        else
        	productDTO.setName(productName);
        LOGGER.debug("Exiting getProduct");
        return productDTO;
    }

    private String getProductName(String productId) throws RetailException{
    	LOGGER.debug("Entering getProductName");
    	String productName=null;
    	try{
    	String uri = retailUtil.getProp("target.rest.uri");
        if(!StringUtils.isEmpty(uri)) uri=uri.replace("PRODUCT_ID", productId);
        String productResponse = restTemplate.getForObject(uri, String.class);
        try{
        	productName= parseProductResponse(productResponse);
        }catch(RetailException re){
        	throw re;
        }
        LOGGER.info(productResponse);
    	}catch(Exception e){
    		throw new RetailException(RetailExceptionCodes.ERROR_TARGET_PRODUCT_REST_CALL);
    		//TODO Retry the rest call
    		
    	}
    	LOGGER.debug("Exiting getProductName");
        return productName;
    }
    

    
    private String parseProductResponse(String response) throws RetailException{
    	LOGGER.debug("Entering parseProductResponse");
    	String itemName=null;
        	JsonParser parser = new JsonParser(); 
        	JsonObject jsonObjectRoot=null;
    			 try{
    				 jsonObjectRoot = (JsonObject) parser.parse(response);
    				 JsonObject jsonObjectProduct=null;
    				 JsonObject jsonObjectItem=null;
    				 JsonObject jsonObjectProductDescription=null;
    				 JsonElement jsonItemNameElement=null;
    				 if(null!=jsonObjectRoot && !jsonObjectRoot.isJsonNull()){
	    				 jsonObjectProduct =jsonObjectRoot.getAsJsonObject("product");
	    				 if(null!=jsonObjectProduct && !jsonObjectProduct.isJsonNull()){
		    				jsonObjectItem =jsonObjectProduct.getAsJsonObject("item");
		    				 if(null!=jsonObjectItem && !jsonObjectItem.isJsonNull()){
		    					 jsonObjectProductDescription=jsonObjectItem.getAsJsonObject("product_description");
		    					 if(null!=jsonObjectProductDescription && !jsonObjectProductDescription.isJsonNull()){
		    						 jsonItemNameElement=jsonObjectProductDescription.get("title");
		    						 if(null!=jsonItemNameElement && !jsonItemNameElement.isJsonNull())
		    							 itemName=jsonItemNameElement.getAsString();
			    				 }	
		    				 }		    				 
	    				 }
    				}
    			 }
    			 catch(Exception e){
    				 LOGGER.error("Error occured during parsing the response to get the product Name");
    				 throw new RetailException(RetailExceptionCodes.PARSE_ERROR);
    			 }
    			 if(itemName==null)
    				 throw new RetailException(RetailExceptionCodes.PRODUCT_NAME_NOT_FOUND);
    			 LOGGER.info(" itemName :: "+itemName);
    	LOGGER.debug("Exiting parseProductResponse");	
		 return itemName;
    
    }

	public void addProduct(ProductDTO productDTO) throws RetailException {
		LOGGER.info("Entering addProduct");
    	 try{
    		 retailDaoService.saveProduct(productDTO);
    		 ProductDTO newProductDTO =retailDaoService.getProduct(productDTO.getId());
    		 LOGGER.info("newProductDTO :: "+newProductDTO);   	
    	 }catch(Exception e){
    		 LOGGER.error("Exception occured during saving the product");
    	 }
    	 LOGGER.info("Exiting addProduct");
    }

    public String updateProduct(ProductDTO productDTO) throws RetailException {
    	 LOGGER.info("Entering updateProduct");
    	 try{
    	    if(RetailConstants.TRUE.equalsIgnoreCase(retailUtil.getProp("fetch.product.name.from.rest.service"))){
    	    	String productName=null;
    	    	try{
    	        	productName=getProductName(String.valueOf(productDTO.getId()));
    	        }catch(RetailException re){
    	        	LOGGER.error("Exception occured to get the product name:: "+re.getErrorMsg());
    	        }
    	        productDTO.setName(productName);
    	    }
    	     try{
    	    	 retailDaoService.saveProduct(productDTO);
    	     }catch(RetailException re){
    	        	throw re;
    	     }
    	
    	 }catch(Exception e){
    		 if(e instanceof RetailException) {
    			 throw e;
    		 }
            throw new RetailException(RetailExceptionCodes.SYSTEM_ERROR);
    	 }
    	 LOGGER.info("Exiting updateProduct");
    	 return RetailConstants.SAVE_SUCCESSFULLY;
    }
    
    
    /*private ResponseEntity<String> getAuthenticated(String gocdTriggerURL) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", String.valueOf(MediaType.TEXT_XML));
        httpHeaders.set("Confirm", String.valueOf(true));
        httpHeaders.set("authorization", "Basic YWRtaW46YWRtaW4=");
        
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        return getRestClient().exchange(url, HttpMethod.POST, entity, String.class);
    }*/

   

}
