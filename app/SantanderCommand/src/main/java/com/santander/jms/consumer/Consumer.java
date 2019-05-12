package com.santander.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.santander.exception.ConsumerException;
import com.santander.jms.event.EventController;

public abstract class Consumer<O, E extends EventController<O>> {

	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * Controller Responsavel pelo processamento da mensagem recuperada.
	 */
	@Autowired
	private E controller;

	/**
	 * Construtor Padrao.
	 */
	public Consumer() {

	}

	/**
	 * Metodo responsavel por recuperar a mensage de uma Fila e encaminha-la para
	 * processamento ao Event Controller.
	 * 
	 * @param objectMessage Mensagem
	 * @throws ConsumerException
	 * @throws JMSException
	 */
	@SuppressWarnings("unchecked")
	public void process(Message objectMessage) throws ConsumerException, JMSException {

		String messageId = null;
		O message = null;

		try {

			messageId = objectMessage.getJMSMessageID();

			if (objectMessage instanceof ActiveMQTextMessage) {
				message = (O) ((ActiveMQTextMessage) objectMessage).getText();
			} else {
				message = (O) ((ActiveMQObjectMessage) objectMessage).getObject();
			}

			logger.debug("Mensagem Recebida. Identificador da Mensagem = [{}], Objeto = [{}]", messageId, message);

			controller.fire(message, objectMessage.getJMSMessageID());
		} catch (Exception e) {

			logger.error("Ocorreu um erro. Identificador da Mensagem = [{}], Objeto = [{}]", messageId, message, e);
			throw new ConsumerException(e);

		}
	}

}
