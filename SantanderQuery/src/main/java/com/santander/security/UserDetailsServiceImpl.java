package com.santander.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santander.domain.Cliente;
import com.santander.service.ClienteService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteService clienteService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cliente = clienteService.findOneByEmail(email);
		if (cliente == null) {
			throw new UsernameNotFoundException(email);
		}

		return new UserDetailsImpl(cliente.getEmail(), cliente.getSenha(), cliente.getPerfisCliente());
	}

}
