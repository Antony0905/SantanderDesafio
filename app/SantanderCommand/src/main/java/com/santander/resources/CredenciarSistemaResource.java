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
import com.santander.domain.SistemaCredenciado;
import com.santander.exception.EventException;
import com.santander.jms.producer.CredenciarSistemaProducer;

@RestController
public class CredenciarSistemaResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CredenciarSistemaProducer producer;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/credenciarSistema", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> credenciarSistema(@Valid @RequestBody SistemaCredenciado sistemaCredenciado)
			throws EventException, Exception {

		logger.info("[START APPLICATION] CREDENCIAR SISTEMA");

		try {

			logger.info("[PRODUCER] Fila CREDENCIAR_SISTEMA");

			sistemaCredenciado.setSenha(bCryptPasswordEncoder.encode(sistemaCredenciado.getSenha()));
			String result = new Gson().toJson(producer.send(sistemaCredenciado));
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (JMSException e) {

			throw new EventException(e);

		}

	}

}
