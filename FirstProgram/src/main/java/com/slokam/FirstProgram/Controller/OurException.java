package com.slokam.FirstProgram.Controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.slokam.FirstProgram.Exception.DuplicateNameException;

@ControllerAdvice
public class OurException {
	
	Logger logger = Logger.getLogger(OurException.class);
	
	@ExceptionHandler(DuplicateNameException.class)
	public ResponseEntity handleException()
	{
		logger.debug("DuplicateNameException occured");
		System.out.println("DuplicateNameException");
		ResponseEntity response = new ResponseEntity<>(HttpStatus.CONFLICT);
		logger.debug("Debuging handleException Ends");
		return response;
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException1()
	{
		logger.debug("Exception occured");
		System.out.println("Exception");
		ResponseEntity response = new ResponseEntity<>(HttpStatus.CONFLICT);
		logger.debug("Debuging handleException1 Ends");
		return response;
		
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity handleException2()
	{
		logger.debug("IOException occured");
		System.out.println("IOException");
		ResponseEntity response = new ResponseEntity<>(HttpStatus.CONFLICT);
		logger.debug("Debuging handleException2 Ends");
		return response;
		
	}



}
