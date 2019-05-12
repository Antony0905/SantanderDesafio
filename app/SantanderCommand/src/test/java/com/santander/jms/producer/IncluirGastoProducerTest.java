package com.santander.jms.producer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.constants.Constants;

@RunWith(MockitoJUnitRunner.class)
public class IncluirGastoProducerTest {

	@InjectMocks
	private IncluirGastoProducer incluirGastoProducer;

	@Test
	public void validarFilaIncluirGasto() {

		Assert.assertEquals(Constants.FILA_INCLUIR_GASTO, incluirGastoProducer.getQueueName());

	}

}
