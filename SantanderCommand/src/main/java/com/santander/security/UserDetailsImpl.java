package com.santander.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.santander.enums.PerfilSistemaCredenciado;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	public UserDetailsImpl() {

	}

	public UserDetailsImpl(Integer id, String email, String senha, Set<PerfilSistemaCredenciado> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toList());
	}

	private Integer id;
	private String email;
	private String senha;

	/* Atributo Responsavel Por Resgatar os Perfis do Usuario */
	private Collection<? extends GrantedAuthority> authorities;

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
