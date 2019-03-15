package com.stefanini.projeto.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7454218620872118346L;
	
	public ObjectNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public ObjectNotFoundException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
