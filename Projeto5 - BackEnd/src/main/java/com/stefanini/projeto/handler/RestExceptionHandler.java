package com.stefanini.projeto.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.stefanini.projeto.exception.DataIntegrityException;
import com.stefanini.projeto.exception.InstanceNumberExceeded;
import com.stefanini.projeto.exception.ObjectNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<?> handleDataIntegrityException(DataIntegrityException dataIntException){
		ResourceNotFoundDetails resourceDetail = 
				new ResourceNotFoundDetails
				("Erro durante integração", HttpStatus.NOT_FOUND.value(), dataIntException.getMessage());
		
		return new ResponseEntity<>(resourceDetail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InstanceNumberExceeded.class)
	public ResponseEntity<?> handleInstanceNumberExceeded(InstanceNumberExceeded instanceNumberExceed){
		ResourceNotFoundDetails resourceDetail = 
				new ResourceNotFoundDetails
				("Erro durante integração", HttpStatus.BAD_REQUEST.value(), instanceNumberExceed.getMessage());
		
		return new ResponseEntity<>(resourceDetail, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<?> handleObjectNotFoundException(ObjectNotFoundException objectNotFound){
		ResourceNotFoundDetails resourceDetails = 
				new ResourceNotFoundDetails
				("Erro durante integração", HttpStatus.BAD_REQUEST.value(), objectNotFound.getMessage());
		
		return new ResponseEntity<>(resourceDetails, HttpStatus.BAD_REQUEST);
				
	}

}
