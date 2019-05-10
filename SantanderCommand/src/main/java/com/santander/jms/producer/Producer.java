package com.santander.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import java.util.concurrent.atomic.AtomicReference;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Classe responsavel por definir metodos do producer JMS.
 * 
 * @author Everis - Matheus Antony
 * @version 1.0
 * @since 27/04/2019
 */
public abstract class Producer<T> {

	/**
	 * Referencia para Instancia JmsTemplate, responsavel por prover a comunicacao
	 * com o Broker JMS.
	 */
	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * Construtor padrao.
	 */
	public Producer() {

	}

	/**
	 * Metodo responsavel por enviar o object ao Broker JMS.
	 * 
	 * @param object objeto a ser enviado ao Broker JMS
	 * @return Identificador da mensagem.
	 * @throws JMSException
	 */
	public String send(T object) throws JMSException {

		final AtomicReference<Message> messageRef = new AtomicReference<>();

		jmsTemplate.convertAndSend(getQueueName(), object, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws JMSException {

				messageRef.set(message);
				return message;
			}
		});

		return messageRef.get().getJMSMessageID();
	}

	/**
	 * Metodo responsavel por definir o nome da Fila a ser utilizada.
	 * 
	 * @return nome da Fila.
	 */
	protected abstract String getQueueName();
}
