package com.santander.jms.producer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.constants.Constants;

@RunWith(MockitoJUnitRunner.class)
public class InserirClienteProducerTest {

	@InjectMocks
	private InserirClienteProducer inserirClienteProducer;

	@Test
	public void validarFilaInserirCliente() {

		Assert.assertEquals(Constants.FILA_INSERIR_CLIENTE, inserirClienteProducer.getQueueName());

	}

}
