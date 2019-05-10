package com.santander.jms.producer;

import org.springframework.stereotype.Component;

import com.santander.domain.Cliente;

@Component
public class InserirClienteProducer extends Producer<Cliente> {

	public InserirClienteProducer() {
		super();
	}

	@Override
	protected String getQueueName() {
		return com.santander.constants.Constants.FILA_INSERIR_CLIENTE;
	}

}
