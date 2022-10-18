package com.cpa.exception;

public class CPException extends Exception{

	String errCode;
	String errMsg;
	
	
	//constructors

	public CPException() {
		super();
	}

	
	public CPException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}






	//getters & setters
	
	public String getErrMsg() {
		return errMsg;
	}
	public String getErrCode() {
		return errCode;
	}


	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}


	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
	
	
}