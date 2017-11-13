package com.retail.exception;

public class RetailException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int errorCode;
	  private String errorMsg;

	  public RetailException(RetailExceptionCodes code) {
		super(code.getMsg());
	    this.errorMsg = code.getMsg();
	    this.errorCode = code.getId();
	  }

	  public int getErrorCode() {
	    return errorCode;
	  }

	  public String getErrorMsg() {
	    return errorMsg;
	  }
}
