package com.santander.jms.producer;

import org.springframework.stereotype.Component;

import com.santander.domain.SistemaCredenciado;

@Component
public class CredenciarSistemaProducer extends Producer<SistemaCredenciado> {

	public CredenciarSistemaProducer() {
		super();
	}

	@Override
	protected String getQueueName() {
		return com.santander.constants.Constants.FILA_CREDENCIAR_SISTEMA;
	}

}
