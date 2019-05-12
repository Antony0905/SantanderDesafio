package com.santander.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.domain.Cliente;
import com.santander.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findOneByEmail(String email) {
		return clienteRepository.findOneByEmail(email);
	}

}
