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
import org.slf4j.Logger;

import com.santander.domain.Cliente;
import com.santander.exception.EventException;
import com.santander.services.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class InserirClienteControllerTest {

	@InjectMocks
	private InserirClienteController inserirClienteController;

	@Mock
	private ClienteService clienteService;


	@Mock
	private Logger logger;

	@Test
	public void clienteInseridoESincronizadoComSucesso() throws EventException, JMSException {

		Cliente cliente = new Cliente(1, "matheus", "matheus@matheus.com", "matheus");

		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(clienteService.validarCodigoUsuarioCliente(cliente.getCodigoUsuario())).thenReturn(false);
		when(clienteService.validarEmailCliente(cliente.getEmail())).thenReturn(false);

		Mockito.doNothing().when(clienteService).save(cliente);

		inserirClienteController.fire(cliente, id);

		Mockito.verify(clienteService, Mockito.times(1)).save(cliente);

	}

	@Test
	public void clienteComCodigoUsuarioJaCadastrado() throws EventException, JMSException {

		Cliente cliente = new Cliente(1, "matheus", "matheus@matheus.com", "matheus");

		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(clienteService.validarCodigoUsuarioCliente(cliente.getCodigoUsuario())).thenReturn(true);

		inserirClienteController.fire(cliente, id);

		assertEquals(true, clienteService.validarCodigoUsuarioCliente(cliente.getCodigoUsuario()));

	}

	@Test
	public void ClienteComEmailJaCadastrado() throws EventException, JMSException {

		Cliente cliente = new Cliente(1, "matheus", "matheus@matheus.com", "matheus");

		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		when(clienteService.validarCodigoUsuarioCliente(cliente.getCodigoUsuario())).thenReturn(false);
		when(clienteService.validarEmailCliente(cliente.getEmail())).thenReturn(true);

		inserirClienteController.fire(cliente, id);

		assertEquals(true, clienteService.validarEmailCliente(cliente.getEmail()));

	}

}
