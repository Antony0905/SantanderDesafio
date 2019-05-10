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

import com.santander.enums.PerfilSistemaCredenciado;

@Entity
@Table
public class SistemaCredenciado implements Serializable {

	private static final long serialVersionUID = 1L;

	public SistemaCredenciado(@NotNull String nome, @NotNull String email, @NotNull String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		addPerfil(PerfilSistemaCredenciado.ADMIN);
	}

	public SistemaCredenciado() {
		addPerfil(PerfilSistemaCredenciado.ADMIN);
	}

	@Id
	@GeneratedValue(generator = "sistema_credenciado_generator")
	@SequenceGenerator(name = "sistema_credenciado_generator", sequenceName = "sistema_credenciado_generator", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfisSistemaCredenciado")
	private Set<Integer> perfis = new HashSet<>();

	public Set<PerfilSistemaCredenciado> getPerfis() {
		return perfis.stream().map(x -> PerfilSistemaCredenciado.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(PerfilSistemaCredenciado perfil) {
		perfis.add(perfil.getCod());
	}

	public void setPerfis(Set<Integer> perfis) {
		this.perfis = perfis;
	}

	public Set<Integer> getPerfisNumber() {
		return perfis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
