package com.santander.jms.event.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santander.domain.Cliente;
import com.santander.exception.EventException;
import com.santander.jms.event.EventController;
import com.santander.services.ClienteService;

@Component
public class InserirClienteController implements EventController<Cliente> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClienteService clienteService;

	@Override
	public void fire(Cliente cliente, String id) throws EventException {

		try {

			if (!clienteService.validarCodigoUsuarioCliente(cliente.getCodigoUsuario())) {

				if (!clienteService.validarEmailCliente(cliente.getEmail())) {

					clienteService.save(cliente);
					logger.info("[OK] Cliente Inserido com Sucesso");

				} else {

					logger.error(
							"[FAILED] Email " + cliente.getEmail() + " Ja cadastrado. Por favor insira um novo Email");

				}

			} else {

				logger.error("[FAILED] Codigo Usuario " + cliente.getCodigoUsuario() + " Ja cadastrado.");

			}

		} catch (Exception e) {
			logger.error("Ocorreu um erro. Identificador da Mensagem = [" + id + "]");
			throw new EventException(e);
		}
	}

}
