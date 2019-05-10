package com.santander.jms.producer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.constants.Constants;
import com.santander.jms.producer.SincronismoProducer;

@RunWith(MockitoJUnitRunner.class)
public class SincronismoProducerTest {

	@InjectMocks
	private SincronismoProducer sincronismoProducer;

	@Test
	public void validarFilaSincronismo() {

		Assert.assertEquals(Constants.FILA_SINCRONISMO, sincronismoProducer.getQueueName());

	}

}
