package com.slokam.FirstProgram.Controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.slokam.FirstProgram.Exception.DuplicateNameException;
import com.slokam.FirstProgram.Exception.UnsupportedFileFormateExceptions;

@ControllerAdvice
public class OurException {
	
	Logger logger = Logger.getLogger(OurException.class);
	
	@ExceptionHandler(DuplicateNameException.class)
	public ResponseEntity handleException(Exception e)
	{
		e.printStackTrace();
		logger.debug("DuplicateNameException occured");
		System.out.println("DuplicateNameException");
		ResponseEntity response = new ResponseEntity<>(HttpStatus.CONFLICT);
		logger.debug("Debuging handleException Ends");
		return response;
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException1(Exception e)
	{
		e.printStackTrace();
		logger.debug("Exception occured");
		System.out.println("Exception");
		ResponseEntity response = new ResponseEntity<>(HttpStatus.CONFLICT);
		logger.debug("Debuging handleException1 Ends");
		return response;
		
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity handleException2(Exception e)
	{
		e.printStackTrace();
		logger.debug("IOException occured");
		System.out.println("IOException");
		ResponseEntity response = new ResponseEntity<>(HttpStatus.CONFLICT);
		logger.debug("Debuging handleException2 Ends");
		return response;
		
	}
	
	@ExceptionHandler(UnsupportedFileFormateExceptions.class)
	public ResponseEntity UnsupportedFormateException(UnsupportedFileFormateExceptions e)
	{
		ResponseEntity response = new ResponseEntity<>(e.getMessage(),HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		return response;
		
	}



}
