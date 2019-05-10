package com.santander.resources;

import javax.jms.JMSException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.santander.domain.Cliente;
import com.santander.exception.EventException;
import com.santander.jms.producer.InserirClienteProducer;

@RestController
public class InserirClienteResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InserirClienteProducer producer;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/inserirCliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> inserirCliente(@Valid @RequestBody Cliente cliente) throws EventException, Exception {

		logger.info("[START APPLICATION] INSERIR CLIENTE");

		try {

			logger.info("[PRODUCER] Fila INSERIR_CLIENTE");

			cliente.setSenha(bCryptPasswordEncoder.encode(cliente.getSenha()));
			String result = new Gson().toJson(producer.send(cliente));
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (JMSException e) {

			throw new EventException(e);

		}

	}

}
