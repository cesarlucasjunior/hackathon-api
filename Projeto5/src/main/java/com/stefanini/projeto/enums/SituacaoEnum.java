package com.stefanini.projeto.enums;

public enum SituacaoEnum {

	A("Ativo"), 
	I("Inativo");

	private String descricao;

	private SituacaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}