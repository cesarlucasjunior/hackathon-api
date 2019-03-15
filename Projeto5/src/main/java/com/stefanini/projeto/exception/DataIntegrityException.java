package com.stefanini.projeto.exception;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -2635021174885315836L;

	public DataIntegrityException(String mensagem) {
		super(mensagem);
	}
	
	public DataIntegrityException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
