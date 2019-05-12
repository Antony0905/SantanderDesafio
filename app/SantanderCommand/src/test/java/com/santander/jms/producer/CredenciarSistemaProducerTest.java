package com.santander.jms.producer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.constants.Constants;

@RunWith(MockitoJUnitRunner.class)
public class CredenciarSistemaProducerTest {

	@InjectMocks
	private CredenciarSistemaProducer credenciarSistemaProducer;

	@Test
	public void validarFilaCredenciarSistema() {

		Assert.assertEquals(Constants.FILA_CREDENCIAR_SISTEMA, credenciarSistemaProducer.getQueueName());

	}

}
