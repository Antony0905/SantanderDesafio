package com.santander.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Transacoes implements Serializable {

	private static final long serialVersionUID = 1L;

	public Transacoes() {
		super();
	}

	@Id
	@GeneratedValue(generator = "transacoes_generator")
	@SequenceGenerator(name = "transacoes_generator", sequenceName = "transacoes_generator", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
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

	public void adicionarGasto(@NotNull Integer codigoUsuario, @NotNull String descricao, @NotNull Double valor,
			@NotNull Date data, String categoria) {

		this.codigoUsuario = codigoUsuario;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;

	}

}
