package com.santander.resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santander.domain.Cliente;
import com.santander.domain.HistoricoGastos;
import com.santander.security.UserDetailsImpl;
import com.santander.security.UserService;
import com.santander.service.ClienteService;
import com.santander.service.HistoricoGastosService;

@RestController
public class ListagemGastosResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HistoricoGastosService historicoGastosService;

	@Autowired
	private ClienteService clienteService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/listarGastos/{codigoUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HistoricoGastos>> listarGastosById(@PathVariable Integer codigoUsuario)
			throws Exception {

		logger.info("[START APPLICATION] LISTAGEM DE GASTOS BY ID");

		try {

			List<HistoricoGastos> historicoGastos = historicoGastosService.findByCodigoUsuario(codigoUsuario);
			return new ResponseEntity<List<HistoricoGastos>>(historicoGastos, HttpStatus.OK);

		} catch (Exception e) {

			throw new Exception(e);

		}

	}

	@GetMapping(value = "/listarGastos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HistoricoGastos>> listarGastos() throws Exception {

		logger.info("[START APPLICATION] LISTAGEM DE GASTOS");

		try {

			UserDetailsImpl user = UserService.authenticated();
			Cliente cliente = clienteService.findOneByEmail(user.getUsername());

			List<HistoricoGastos> gastosCartao = historicoGastosService.findByCodigoUsuario(cliente.getCodigoUsuario());
			return new ResponseEntity<List<HistoricoGastos>>(gastosCartao, HttpStatus.OK);

		} catch (Exception e) {

			throw new Exception(e);

		}
	}

	@GetMapping(value = "/listarGastosByDate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HistoricoGastos>> listarGastosByDate(@RequestParam("date") String dateString)
			throws Exception {

		logger.info("[START APPLICATION] LISTAGEM DE GASTOS BY DATE");

		try {

			UserDetailsImpl user = UserService.authenticated();
			Cliente cliente = clienteService.findOneByEmail(user.getUsername());

			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			List<HistoricoGastos> gastosCartao = historicoGastosService
					.findByCodigoUsuarioAndData(cliente.getCodigoUsuario(), date);
			return new ResponseEntity<List<HistoricoGastos>>(gastosCartao, HttpStatus.OK);

		} catch (Exception e) {

			throw new Exception(e);

		}

	}

}
