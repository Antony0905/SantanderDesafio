package com.santander.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.domain.Transacoes;
import com.santander.exception.ConsumerException;
import com.santander.exception.EventException;
import com.santander.jms.event.controller.AtualizarGastoController;

@RunWith(MockitoJUnitRunner.class)
public class AtualizarGastoConsumerTest {

	@InjectMocks
	private AtualizarGastoConsumer atualizarGastoConsumer;

	@Mock
	private AtualizarGastoController atualizarGastoController;

	@Test
	public void validarConsumerAtualizarGasto() throws ConsumerException, JMSException, EventException {

		String messageId = "123456789";
		Transacoes historicoGastos = new Transacoes();

		Message message = new ActiveMQObjectMessage();
		message.setJMSMessageID(messageId);
		((ActiveMQObjectMessage) message).setObject(historicoGastos);

		Mockito.doNothing().when(atualizarGastoController).fire(historicoGastos, "ID:" + messageId);

		atualizarGastoConsumer.process(message);

	}

}
