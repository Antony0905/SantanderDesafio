package com.santander.jms.producer;

import org.springframework.stereotype.Component;

import com.santander.constants.Constants;
import com.santander.sincronismo.Sincronismo;

@Component
public class SincronismoProducer extends Producer<Sincronismo> {

	public SincronismoProducer() {
		super();
	}

	@Override
	protected String getQueueName() {
		return Constants.FILA_SINCRONISMO;
	}

}
