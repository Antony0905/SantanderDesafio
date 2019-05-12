package com.santander.jms.producer;

import org.springframework.stereotype.Component;

import com.santander.domain.Transacoes;

@Component
public class IncluirGastoProducer extends Producer<Transacoes> {

	public IncluirGastoProducer() {
		super();
	}

	@Override
	protected String getQueueName() {
		return com.santander.constants.Constants.FILA_INCLUIR_GASTO;
	}

}
