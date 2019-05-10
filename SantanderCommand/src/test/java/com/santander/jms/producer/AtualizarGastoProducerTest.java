package com.santander.jms.producer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.constants.Constants;

@RunWith(MockitoJUnitRunner.class)
public class AtualizarGastoProducerTest {

	@InjectMocks
	private AtualizarGastoProducer atualizarGastoProducer;

	@Test
	public void validarFilaAtualizarGasto() {

		Assert.assertEquals(Constants.FILA_ATUALIZAR_GASTO, atualizarGastoProducer.getQueueName());

	}

}
