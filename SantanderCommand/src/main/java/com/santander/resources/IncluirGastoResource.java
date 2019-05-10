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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.santander.domain.Transacoes;
import com.santander.exception.EventException;
import com.santander.jms.producer.IncluirGastoProducer;

@RestController
public class IncluirGastoResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IncluirGastoProducer producer;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/postGastoCartao", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postGastoCartao(@Valid @RequestBody Transacoes transacoes) throws EventException {

		logger.info("[START APPLICATION] INCLUIR GASTO");

		try {

			logger.info("[PRODUCER] Fila INCLUIR_GASTO");

			String result = new Gson().toJson(producer.send(transacoes));
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (JMSException e) {

			throw new EventException(e);

		}

	}

}
