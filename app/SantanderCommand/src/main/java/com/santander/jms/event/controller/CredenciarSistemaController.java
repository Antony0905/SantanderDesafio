package com.santander.jms.event.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santander.domain.SistemaCredenciado;
import com.santander.exception.EventException;
import com.santander.jms.event.EventController;
import com.santander.services.SistemaCredenciadoService;

@Component
public class CredenciarSistemaController implements EventController<SistemaCredenciado> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SistemaCredenciadoService sistemaCredenciadoService;

	@Override
	public void fire(SistemaCredenciado sistemaCredenciado, String id) throws EventException {

		try {
			if (sistemaCredenciadoService.validarSistema(sistemaCredenciado.getEmail())) {

				sistemaCredenciadoService.save(sistemaCredenciado);
				logger.info("[OK] Sistema " + sistemaCredenciado.getNome() + " Credenciado com Sucesso");

			} else {

				logger.error("[FAILED] Email " + sistemaCredenciado.getEmail()
						+ " Ja cadastrado. Por favor insira um novo Email");

			}

		} catch (Exception e) {

			logger.error("Ocorreu um erro. Identificador da Mensagem = [" + id + "]");
			throw new EventException(e);

		}
	}

}
