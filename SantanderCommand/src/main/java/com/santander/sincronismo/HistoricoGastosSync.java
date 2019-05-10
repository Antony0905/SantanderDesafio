package com.santander.sincronismo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "historicogastos")
public class HistoricoGastosSync implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull
	private Integer codigoUsuario;
	@NotNull
	private String descricao;
	@NotNull
	private Double valor;
	@NotNull
	private Date data;

	private String categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public HistoricoGastosSync(@NotNull Integer codigoUsuario, @NotNull String descricao, @NotNull Double valor,
			@NotNull Date data, String categoria) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;
	}

	public HistoricoGastosSync() {
		super();
	}

}
