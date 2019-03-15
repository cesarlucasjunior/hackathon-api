package com.stefanini.projeto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CachorroCorPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "CA_NU")
	private Long idCachorro;

	@Column(name = "CO_NU")
	private Long idCor;

	public CachorroCorPK() {
		super();
	}

	public CachorroCorPK(Long idCachorro, Long idCor) {
		super();
		this.idCachorro = idCachorro;
		this.idCor = idCor;
	}

	public Long getIdCachorro() {
		return idCachorro;
	}

	public void setIdCachorro(Long idCachorro) {
		this.idCachorro = idCachorro;
	}

	public Long getIdCor() {
		return idCor;
	}

	public void setIdCor(Long idCor) {
		this.idCor = idCor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCachorro == null) ? 0 : idCachorro.hashCode());
		result = prime * result + ((idCor == null) ? 0 : idCor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CachorroCorPK other = (CachorroCorPK) obj;
		if (idCachorro == null) {
			if (other.idCachorro != null)
				return false;
		} else if (!idCachorro.equals(other.idCachorro))
			return false;
		if (idCor == null) {
			if (other.idCor != null)
				return false;
		} else if (!idCor.equals(other.idCor))
			return false;
		return true;
	}

}