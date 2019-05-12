package com.santander.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.santander.constants.Constants;
import com.santander.domain.Cliente;
import com.santander.exception.ConsumerException;
import com.santander.jms.event.controller.InserirClienteController;

@Component
public class InserirClienteConsumer extends Consumer<Cliente, InserirClienteController> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Async
	@JmsListener(destination = Constants.FILA_INSERIR_CLIENTE, containerFactory = Constants.FABRICA_JMS)
	public void process(Message objectMessage) throws ConsumerException, JMSException {

		logger.info("[CONSUMER] Fila INSERIR_CLIENTE.");
		super.process(objectMessage);

	}

}