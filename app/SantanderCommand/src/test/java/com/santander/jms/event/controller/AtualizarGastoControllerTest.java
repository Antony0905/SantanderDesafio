package com.santander.jms.event.controller;

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
import com.santander.services.TransacoesService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AtualizarGastoControllerTest {

	@InjectMocks
	private AtualizarGastoController atualizarGastoController;

	@Mock
	private TransacoesService historicoGastosService;

	@Mock
	private SincronismoProducer producer;

	@Test
	public void atualizacaoDeGastoNoHistorico() throws EventException, JMSException {

		
		Transacoes transacoes = new Transacoes();
		transacoes.adicionarGasto(1, "SmartFit", 50.00, new Date(), "Academia");
		
		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(historicoGastosService.findOneById(transacoes.getId())).thenReturn(transacoes);

		Mockito.doNothing().when(historicoGastosService).save(transacoes);

		atualizarGastoController.fire(transacoes, id);

		Mockito.verify(historicoGastosService, Mockito.times(1)).save(transacoes);
		Mockito.verify(producer, Mockito.times(1)).send(any());

	}

	@Test(expected = EventException.class)
	public void gastoNaoEncontradoNoHistoricoDeveLancarException() throws EventException {

		Transacoes transacoes = new Transacoes();
		transacoes.adicionarGasto(1, "SmartFit", 50.00, new Date(), "Academia");

		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(historicoGastosService.findOneById(transacoes.getId())).thenReturn(null);
		Mockito.doNothing().when(historicoGastosService).save(transacoes);

		atualizarGastoController.fire(transacoes, id);

	}

}
