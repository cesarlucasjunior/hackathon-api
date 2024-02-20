package com.stefanini.projeto.exception;

public class InstanceNumberExceeded extends RuntimeException {

	private static final long serialVersionUID = -3309357061731100245L;

	public InstanceNumberExceeded(String mensagem) {
		super(mensagem);
	}

}
