package com.santander.resources;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import com.santander.domain.Cliente;
import com.santander.domain.HistoricoGastos;
import com.santander.enums.PerfilCliente;
import com.santander.security.UserDetailsImpl;
import com.santander.security.UserService;
import com.santander.service.ClienteService;
import com.santander.service.HistoricoGastosService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class ListagemGastosResourceTest {

	@InjectMocks
	private ListagemGastosResource listagemGastosResource;

	@Mock
	private HistoricoGastosService historicoGastosService;

	@Mock
	private ClienteService clienteService;

	@Mock
	private UserService userService;

	@Test
	public void TesteEndPointListarGastosById() throws Exception {

		Integer codigoUsuario = 1;

		List<HistoricoGastos> listHistoricoGastos = new ArrayList<>();
		HistoricoGastos historicoGastos = new HistoricoGastos(1, "Smart Fit", 50.00, new Date(), "Academia");
		listHistoricoGastos.add(historicoGastos);

		when(historicoGastosService.findByCodigoUsuario(codigoUsuario)).thenReturn(listHistoricoGastos);

		ResponseEntity<List<HistoricoGastos>> response = listagemGastosResource.listarGastosById(codigoUsuario);

		Assert.assertEquals(200, response.getStatusCodeValue());

	}

	@Test
	public void TesteEndPointListarGastos() throws Exception {

		PowerMockito.mockStatic(UserService.class);

		Set<PerfilCliente> perfis = new HashSet<>();
		perfis.add(PerfilCliente.CLIENTE);

		UserDetailsImpl user = new UserDetailsImpl("matheus@matheus.com",
				"$2a$10$RXUmuF//FvuE3x0HLhvZD.Q.jufe8hnt6xbIaq5d7sHiJI2SjVX7S", perfis);

		Cliente cliente = new Cliente(1, "matheus", "matheus@matheus.com",
				"$2a$10$RXUmuF//FvuE3x0HLhvZD.Q.jufe8hnt6xbIaq5d7sHiJI2SjVX7S");

		List<HistoricoGastos> listHistoricoGastos = new ArrayList<>();
		HistoricoGastos historicoGastos = new HistoricoGastos(1, "Smart Fit", 50.00, new Date(), "Academia");
		HistoricoGastos historicoGastos2 = new HistoricoGastos(1, "Extra", 50.00, new Date(), "Mercado");
		HistoricoGastos historicoGastos3 = new HistoricoGastos(1, "Saraiva", 50.00, new Date(), "Livraria");

		listHistoricoGastos.add(historicoGastos);
		listHistoricoGastos.add(historicoGastos2);
		listHistoricoGastos.add(historicoGastos3);

		PowerMockito.when(UserService.authenticated()).thenReturn(user);
		when(clienteService.findOneByEmail(user.getUsername())).thenReturn(cliente);
		when(historicoGastosService.findByCodigoUsuario(cliente.getCodigoUsuario())).thenReturn(listHistoricoGastos);

		ResponseEntity<List<HistoricoGastos>> response = listagemGastosResource.listarGastos();

		Assert.assertEquals(200, response.getStatusCodeValue());

	}

	@Test
	public void TesteEndPointListarGastosByDate() throws Exception {

		PowerMockito.mockStatic(UserService.class);

		Set<PerfilCliente> perfis = new HashSet<>();
		perfis.add(PerfilCliente.CLIENTE);

		UserDetailsImpl user = new UserDetailsImpl("matheus@matheus.com",
				"$2a$10$RXUmuF//FvuE3x0HLhvZD.Q.jufe8hnt6xbIaq5d7sHiJI2SjVX7S", perfis);

		Cliente cliente = new Cliente(1, "matheus", "matheus@matheus.com",
				"$2a$10$RXUmuF//FvuE3x0HLhvZD.Q.jufe8hnt6xbIaq5d7sHiJI2SjVX7S");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data = sdf.parse("05/05/2019");

		List<HistoricoGastos> listHistoricoGastos = new ArrayList<>();
		HistoricoGastos historicoGastos = new HistoricoGastos(1, "Smart Fit", 50.00, data, "Academia");
		HistoricoGastos historicoGastos2 = new HistoricoGastos(1, "Extra", 50.00, data, "Mercado");
		HistoricoGastos historicoGastos3 = new HistoricoGastos(1, "Saraiva", 50.00, data, "Livraria");

		listHistoricoGastos.add(historicoGastos);
		listHistoricoGastos.add(historicoGastos2);
		listHistoricoGastos.add(historicoGastos3);

		PowerMockito.when(UserService.authenticated()).thenReturn(user);
		when(clienteService.findOneByEmail(user.getUsername())).thenReturn(cliente);
		when(historicoGastosService.findByCodigoUsuarioAndData(cliente.getCodigoUsuario(), data))
				.thenReturn(listHistoricoGastos);

		ResponseEntity<List<HistoricoGastos>> response = listagemGastosResource.listarGastosByDate("05/05/2019");

		Assert.assertEquals(200, response.getStatusCodeValue());

	}

}
