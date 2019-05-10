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

import com.santander.exception.ConsumerException;
import com.santander.exception.EventException;
import com.santander.jms.event.controller.SincronismoController;
import com.santander.sincronismo.Sincronismo;

@RunWith(MockitoJUnitRunner.class)
public class SincronismoConsumerTest {

	@InjectMocks
	private SincronismoConsumer sincronismoConsumer;

	@Mock
	private SincronismoController sincronismoController;

	@Test
	public void validarConsumerSincronismo() throws ConsumerException, JMSException, EventException {

		String messageId = "123456789";
		Sincronismo sincronismo = new Sincronismo();

		Message message = new ActiveMQObjectMessage();
		message.setJMSMessageID(messageId);
		((ActiveMQObjectMessage) message).setObject(sincronismo);

		Mockito.doNothing().when(sincronismoController).fire(sincronismo, "ID:" + messageId);

		sincronismoConsumer.process(message);

	}

}
