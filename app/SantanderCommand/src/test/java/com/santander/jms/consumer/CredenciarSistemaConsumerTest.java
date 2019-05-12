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

import com.santander.domain.SistemaCredenciado;
import com.santander.exception.ConsumerException;
import com.santander.exception.EventException;
import com.santander.jms.event.controller.CredenciarSistemaController;

@RunWith(MockitoJUnitRunner.class)
public class CredenciarSistemaConsumerTest {

	@InjectMocks
	private CredenciarSistemaConsumer credenciarSistemaConsumer;

	@Mock
	private CredenciarSistemaController credenciarSistemaController;

	@Test
	public void validarConsumerCredenciarSistema() throws ConsumerException, JMSException, EventException {

		String messageId = "123456789";
		SistemaCredenciado sistemaCredenciado = new SistemaCredenciado();

		Message message = new ActiveMQObjectMessage();
		message.setJMSMessageID(messageId);
		((ActiveMQObjectMessage) message).setObject(sistemaCredenciado);

		Mockito.doNothing().when(credenciarSistemaController).fire(sistemaCredenciado, "ID:" + messageId);

		credenciarSistemaConsumer.process(message);

	}

}
