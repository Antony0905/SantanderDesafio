package com.santander.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "historicogastos")
public class HistoricoGastos implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private Integer codigoUsuario;
	private String descricao;
	private Double valor;
	private Date data;
	private String categoria;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public HistoricoGastos(Integer codigoUsuario, String descricao, Double valor, Date data, String categoria) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;
	}

	public HistoricoGastos() {
		super();
	}

}
