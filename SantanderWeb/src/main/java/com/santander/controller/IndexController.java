package com.santander.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santander.domain.HistoricoGastos;
import com.santander.domain.dto.ClienteDTO;
import com.santander.service.HistoricoGastosService;

@Controller
public class IndexController {

	@Autowired
	private HistoricoGastosService gastoCartaoService;

	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) throws URISyntaxException {

		ClienteDTO clienteLogado = (ClienteDTO) request.getSession().getAttribute("clienteLogado");

		if (clienteLogado != null) {

			List<HistoricoGastos> historicoGastos = gastoCartaoService.getGastosCartao(clienteLogado.getToken());
			model.addAttribute("historicoGastos", historicoGastos);
			model.addAttribute("emailCliente", clienteLogado.getEmail());

			return "index";

		} else {

			return "login";

		}

	}

}
