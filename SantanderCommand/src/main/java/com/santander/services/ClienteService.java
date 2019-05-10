package com.santander.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.domain.Cliente;
import com.santander.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	public Cliente findOneByCodigoUsuario(Integer codigoUsuario) {
		return clienteRepository.findOneByCodigoUsuario(codigoUsuario);
	}

	public Cliente findOneByEmail(String email) {
		return clienteRepository.findOneByEmail(email);
	}

	public boolean validarCodigoUsuarioCliente(Integer codigoUsuario) {

		if (clienteRepository.findOneByCodigoUsuario(codigoUsuario) != null) {
			return true;
		}

		return false;
	}

	public boolean validarEmailCliente(String email) {

		if (clienteRepository.findOneByEmail(email) != null) {
			return true;
		}

		return false;
	}

}
