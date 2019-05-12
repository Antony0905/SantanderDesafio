package com.santander.jms.event.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.santander.domain.SistemaCredenciado;
import com.santander.exception.EventException;
import com.santander.services.SistemaCredenciadoService;

@RunWith(MockitoJUnitRunner.class)
public class CredenciarSistemaControllerTest {

	@InjectMocks
	private CredenciarSistemaController credenciarSistemaController;

	@Mock
	private SistemaCredenciadoService sistemaCredenciadoService;

	@Test
	public void sistemaCredenciadoInseridoESincronizadoComSucesso() throws EventException, JMSException {

		SistemaCredenciado sistemaCredenciado = new SistemaCredenciado("matheus", "matheus@matheus.com", "matheus");
		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(sistemaCredenciadoService.validarSistema(sistemaCredenciado.getEmail())).thenReturn(true);

		Mockito.doNothing().when(sistemaCredenciadoService).save(sistemaCredenciado);

		credenciarSistemaController.fire(sistemaCredenciado, id);

		Mockito.verify(sistemaCredenciadoService, Mockito.times(1)).save(sistemaCredenciado);

	}

	@Test
	public void clienteComEmailJaCadastrado() throws EventException, JMSException {

		SistemaCredenciado sistemaCredenciado = new SistemaCredenciado("matheus", "matheus@matheus.com", "matheus");
		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(sistemaCredenciadoService.validarSistema(sistemaCredenciado.getEmail())).thenReturn(false);

		credenciarSistemaController.fire(sistemaCredenciado, id);

		assertEquals(false, sistemaCredenciadoService.validarSistema(sistemaCredenciado.getEmail()));

	}

}
