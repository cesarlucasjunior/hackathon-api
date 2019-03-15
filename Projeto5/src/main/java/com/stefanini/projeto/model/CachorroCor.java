package com.stefanini.projeto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.stefanini.projeto.enums.SituacaoEnum;

@Entity
public class CachorroCor implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CachorroCorPK id;

	@ManyToOne
	@JoinColumn(name = "CA_NU", referencedColumnName = "CA_NU", insertable = false, updatable = false)
	private Cachorro cachorro;

	@ManyToOne
	@JoinColumn(name = "CO_NU", referencedColumnName = "CO_NU", insertable = false, updatable = false)
	private Cor cor;

	@Column(name = "CC_SIT")
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao;

	public CachorroCor() {
		super();
	}

	public CachorroCor(Cachorro cachorro, Cor cor, SituacaoEnum situacao) {
		super();
		this.id = new CachorroCorPK(cachorro.getId(), cor.getId());
		this.cachorro = cachorro;
		this.cor = cor;
		this.situacao = situacao;
	}

	public CachorroCorPK getId() {
		return id;
	}

	public void setId(CachorroCorPK id) {
		this.id = id;
	}

	public Cachorro getCachorro() {
		return cachorro;
	}

	public void setCachorro(Cachorro cachorro) {
		this.cachorro = cachorro;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

}