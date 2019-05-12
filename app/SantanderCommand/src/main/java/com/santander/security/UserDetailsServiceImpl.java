package com.santander.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santander.domain.SistemaCredenciado;
import com.santander.services.SistemaCredenciadoService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SistemaCredenciadoService sistemaCredenciadoService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		SistemaCredenciado sistemaCredenciado = sistemaCredenciadoService.findOneByEmail(email);
		if (sistemaCredenciado == null) {
			throw new UsernameNotFoundException(email);
		}

		return new UserDetailsImpl(sistemaCredenciado.getId(), sistemaCredenciado.getEmail(),
				sistemaCredenciado.getSenha(), sistemaCredenciado.getPerfis());
	}

}
