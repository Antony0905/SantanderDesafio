package com.santander.jms.event.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.domain.Transacoes;
import com.santander.exception.EventException;
import com.santander.jms.producer.SincronismoProducer;
import com.santander.services.ClienteService;
import com.santander.services.TransacoesService;

@RunWith(MockitoJUnitRunner.class)
public class IncluirGastoControllerTest {

	@InjectMocks
	private IncluirGastoController incluirGastoController;

	@Mock
	private ClienteService clienteService;

	@Mock
	private TransacoesService historicoGastosService;

	@Mock
	private SincronismoProducer producer;

	@Test
	public void historicoDeGastosInseridoESincronizadoComSucesso() throws EventException, JMSException {

		
		Transacoes transacoes = new Transacoes();
		transacoes.adicionarGasto(1, "SmartFit", 50.00, new Date(), "Academia");
		
		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(clienteService.validarCodigoUsuarioCliente(transacoes.getCodigoUsuario())).thenReturn(true);

		Mockito.doNothing().when(historicoGastosService).save(transacoes);

		incluirGastoController.fire(transacoes, id);

		Mockito.verify(historicoGastosService, Mockito.times(1)).save(transacoes);
		Mockito.verify(producer, Mockito.times(1)).send(any());

	}

	@Test
	public void clienteComCodigoUsuarioJaCadastrado() throws EventException, JMSException {

		Transacoes transacoes = new Transacoes();
		transacoes.adicionarGasto(1, "SmartFit", 50.00, new Date(), "Academia");
		
		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(clienteService.validarCodigoUsuarioCliente(transacoes.getCodigoUsuario())).thenReturn(false);

		incluirGastoController.fire(transacoes, id);

		assertEquals(false, clienteService.validarCodigoUsuarioCliente(transacoes.getCodigoUsuario()));

	}

}
