package com.santander.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.domain.SistemaCredenciado;
import com.santander.repository.SistemaCredenciadoRepository;

@Service
public class SistemaCredenciadoService {

	@Autowired
	private SistemaCredenciadoRepository sistemaCredenciadoRepository;

	public void save(SistemaCredenciado sistemaCredenciado) {
		sistemaCredenciadoRepository.save(sistemaCredenciado);
	}

	public SistemaCredenciado findOneByEmail(String email) {
		return sistemaCredenciadoRepository.findOneByEmail(email);
	}

	public boolean validarSistema(String email) {
		if (sistemaCredenciadoRepository.findOneByEmail(email) == null) {
			return true;
		}
		return false;
	}

}
