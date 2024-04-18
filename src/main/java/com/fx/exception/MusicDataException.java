package com.fx.exception;

public class MusicDataException extends Throwable{
	public MusicDataException() {
		super();
	}
	
	public MusicDataException(Throwable t) {
		super(t);
	}
	
	public MusicDataException(Throwable t, String msg) {
		super(new Throwable(msg+": "+t.getMessage()));
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}
}
