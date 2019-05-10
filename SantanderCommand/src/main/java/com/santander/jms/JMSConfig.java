package com.santander.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

/**
 * Classe responsavel por habilitar o uso de JMS, bem como definir metodos de
 * configuracao.
 * 
 * @author Everis - Matheus Antony
 * @version 1.0
 * @since 27/04/2019
 */
@Configuration
@EnableJms
public class JMSConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Value("${spring.activemq.user}")
	private String user;

	@Value("${spring.activemq.password}")
	private String password;

	/**
	 * Metodo responsavel por configurar a Fabrica de Conexoes JMS.
	 * 
	 * @param connectionFactory Objeto responsavel por prover conexoes com o Broker
	 *                          JMS.
	 * @return Fabrica de Objeto responsavel por intermediar a comunicacao com o
	 *         Broker JMS.
	 */
	@Bean
	public DefaultJmsListenerContainerFactory jmsFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {

		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setSessionTransacted(false);
		factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

		configurer.configure(factory, connectionFactory);
		return factory;
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		connectionFactory.setPassword(user);
		connectionFactory.setUserName(password);
		connectionFactory.setTrustAllPackages(true);

		RedeliveryPolicy policy = new RedeliveryPolicy();
		policy.setInitialRedeliveryDelay(1000);
		policy.setUseCollisionAvoidance(true);
		policy.setUseExponentialBackOff(false);
		policy.setMaximumRedeliveries(1);

		connectionFactory.setRedeliveryPolicy(policy);

		return connectionFactory;
	}
}