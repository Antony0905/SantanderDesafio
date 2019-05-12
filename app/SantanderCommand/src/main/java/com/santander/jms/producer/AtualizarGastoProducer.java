package com.santander.jms.producer;

import org.springframework.stereotype.Component;

import com.santander.domain.Transacoes;

@Component
public class AtualizarGastoProducer extends Producer<Transacoes> {

	public AtualizarGastoProducer() {
		super();
	}

	@Override
	protected String getQueueName() {
		return com.santander.constants.Constants.FILA_ATUALIZAR_GASTO;
	}

}
