package com.santander.sincronismo;

import java.io.Serializable;

public class Sincronismo implements Serializable {

	private static final long serialVersionUID = 1L;

	public Sincronismo(String acao, Object object) {
		super();
		this.acao = acao;
		this.object = object;
	}

	public Sincronismo() {
		super();
	}

	private String acao;

	private String nomeObjeto;

	private Object object;

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
		result = prime * result + ((nomeObjeto == null) ? 0 : nomeObjeto.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
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
		Sincronismo other = (Sincronismo) obj;
		if (acao == null) {
			if (other.acao != null)
				return false;
		} else if (!acao.equals(other.acao))
			return false;
		if (nomeObjeto == null) {
			if (other.nomeObjeto != null)
				return false;
		} else if (!nomeObjeto.equals(other.nomeObjeto))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		return true;
	}

}
