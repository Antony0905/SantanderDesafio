package com.santander.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.santander.constants.Constants;
import com.santander.domain.SistemaCredenciado;
import com.santander.exception.ConsumerException;
import com.santander.jms.event.controller.CredenciarSistemaController;

@Component
public class CredenciarSistemaConsumer extends Consumer<SistemaCredenciado, CredenciarSistemaController> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Async
	@JmsListener(destination = Constants.FILA_CREDENCIAR_SISTEMA, containerFactory = Constants.FABRICA_JMS)
	public void process(Message objectMessage) throws ConsumerException, JMSException {

		logger.info("[CONSUMER] Fila CREDENCIAR_SISTEMA.");
		super.process(objectMessage);

	}

}