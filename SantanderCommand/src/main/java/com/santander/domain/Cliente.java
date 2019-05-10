package com.santander.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.santander.enums.PerfilCliente;

@Entity
@Table
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	public Cliente(@NotNull Integer codigoUsuario, @NotNull String nome, @NotNull String email, @NotNull String senha) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		addPerfilCliente(PerfilCliente.CLIENTE);
	}

	public Cliente() {
		addPerfilCliente(PerfilCliente.CLIENTE);
	}

	@Id
	@GeneratedValue(generator = "cliente_generator")
	@SequenceGenerator(name = "cliente_generator", sequenceName = "cliente_generator", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@NotNull
	private Integer codigoUsuario;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfisCliente")
	private Set<Integer> perfisCliente = new HashSet<>();

	public Set<PerfilCliente> getPerfisCliente() {
		return perfisCliente.stream().map(x -> PerfilCliente.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfilCliente(PerfilCliente perfilCliente) {
		perfisCliente.add(perfilCliente.getCod());
	}

	public void setPerfisCliente(Set<Integer> perfis) {
		this.perfisCliente = perfis;
	}

	public Set<Integer> getPerfisClienteNumber() {
		return perfisCliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
