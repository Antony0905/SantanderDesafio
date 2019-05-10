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
import com.santander.jms.event.controller.IncluirGastoController;

@RunWith(MockitoJUnitRunner.class)
public class IncluirGastoConsumerTest {

	@InjectMocks
	private IncluirGastoConsumer incluirGastoConsumer;

	@Mock
	private IncluirGastoController incluirGastoController;

	@Test
	public void validarConsumerIncluirGasto() throws ConsumerException, JMSException, EventException {

		String messageId = "123456789";
		Transacoes historicoGastos = new Transacoes();

		Message message = new ActiveMQObjectMessage();
		message.setJMSMessageID(messageId);
		((ActiveMQObjectMessage) message).setObject(historicoGastos);

		Mockito.doNothing().when(incluirGastoController).fire(historicoGastos, "ID:" + messageId);

		incluirGastoConsumer.process(message);

	}

}
