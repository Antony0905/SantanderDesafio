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

import com.santander.domain.Cliente;
import com.santander.exception.ConsumerException;
import com.santander.exception.EventException;
import com.santander.jms.event.controller.InserirClienteController;

@RunWith(MockitoJUnitRunner.class)
public class InserirClienteConsumerTest {

	@InjectMocks
	private InserirClienteConsumer inserirClienteConsumer;

	@Mock
	private InserirClienteController inserirClienteController;

	@Test
	public void validarConsumerInserirCliente() throws ConsumerException, JMSException, EventException {

		String messageId = "123456789";
		Cliente cliente = new Cliente();

		Message message = new ActiveMQObjectMessage();
		message.setJMSMessageID(messageId);
		((ActiveMQObjectMessage) message).setObject(cliente);

		Mockito.doNothing().when(inserirClienteController).fire(cliente, "ID:" + messageId);

		inserirClienteConsumer.process(message);

	}

}
