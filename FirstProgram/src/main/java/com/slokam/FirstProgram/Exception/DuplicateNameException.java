package com.slokam.FirstProgram.Exception;

public class DuplicateNameException extends Exception{

	private String message;
	
	public DuplicateNameException(String message) {
		
		super(message);
		this.message=message;
	}
}
