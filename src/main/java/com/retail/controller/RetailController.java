package com.retail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retail.exception.RetailException;
import com.retail.service.RetailService;
import com.retail.util.ProductDTO;


@RestController
@RequestMapping("/api")
class RetailController {
	 private static Logger LOGGER = LoggerFactory.getLogger(RetailController.class);
    @Autowired
    RetailService retailService;

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    ProductDTO getProduct(@PathVariable("id") long productId) throws RetailException {
        return retailService.getProduct(productId);
    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    String updateProduct(@PathVariable("id") long productId, @RequestBody ProductDTO product) throws RetailException {
    	product.setId(productId);
        return retailService.updateProduct(product);
    }
    
   /* @RequestMapping(value = "/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void addProduct(@RequestBody ProductDTO productDTO) throws RetailException {
    	retailService.addProduct(productDTO);
    }*/

    
}
