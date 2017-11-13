package com.retail.exception;

public enum RetailExceptionCodes {
	  INVALID_REQUEST(0, "The request is invalid"),
	  MISSING_PARAMETER(1, "Required query parameter is missing"),
	  MISSING_HEADER(2, "Required header is missing"),
	  PARSE_ERROR(3, "Error occured during parsing the get product response"),
	  PRODUCT_NAME_NOT_FOUND(4, "Product name not  found"), 
	  ERROR_TARGET_PRODUCT_REST_CALL(5, "Error occured during target product service call"), 
	  INTERNAL_DATA_STORE_EXCEPTION(6, "Internal data store exception"),
	  PRODUCT_NOT_FOUND_IN_DATA_STORE(7, "Product not found in data store"), 
	  SYSTEM_ERROR(8, "System Exception");

	  private final int id;
	  private final String msg;

	  RetailExceptionCodes(int id, String msg) {
	    this.id = id;
	    this.msg = msg;
	   
	  }

	  public int getId() {
	    return this.id;
	  }

	  public String getMsg() {
	    return this.msg;
	  }
}
