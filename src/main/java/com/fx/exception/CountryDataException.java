package com.fx.exception;

public class CountryDataException extends Exception{
	
	public CountryDataException() {
		super();
	}
	
	public CountryDataException(Throwable t) {
		super(t);
	}
	
	public CountryDataException(Throwable e, String msg) {
		super(new Throwable(msg+" : "+e.getMessage()));
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}
}
