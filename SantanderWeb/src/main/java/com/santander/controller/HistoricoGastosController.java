package com.santander.controller;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santander.domain.HistoricoGastos;
import com.santander.domain.dto.ClienteDTO;
import com.santander.service.HistoricoGastosService;

@Controller
public class HistoricoGastosController {

	@Autowired
	private HistoricoGastosService historicoGastosService;

	@RequestMapping("filtrardata")
	public String filtrarData(Model model, HttpServletRequest request) throws URISyntaxException, ParseException {

		try {

			String date = request.getParameter("date");

			ClienteDTO clienteLogado = (ClienteDTO) request.getSession().getAttribute("clienteLogado");

			if (clienteLogado != null) {

				if (date != null && date != "") {

					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");

					Date now = format1.parse(date);
					date = format2.format(now);

					List<HistoricoGastos> historicoGastos = historicoGastosService
							.getGastosCartaoByDate(clienteLogado.getToken(), date);

					model.addAttribute("historicoGastos", historicoGastos);
					model.addAttribute("emailCliente", clienteLogado.getEmail());

					return "index";

				} else {

					return "redirect:/";

				}

			} else {

				return "login";

			}
		} catch (Exception e) {
			return "failed";
		}

	}

	@RequestMapping("atualizarGastoCartao")
	public String atualizarGastoCartao(Model model, HttpServletRequest request) {

		try {

			ClienteDTO clienteLogado = (ClienteDTO) request.getSession().getAttribute("clienteLogado");

			if (clienteLogado != null) {

				SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);

				String id = request.getParameter("id");
				String codigoUsuario = request.getParameter("codigoUsuarioModal");
				String descricao = request.getParameter("descricaoModal");
				String data = request.getParameter("dataModal");
				String categoria = request.getParameter("categoriaModal");
				String valor = request.getParameter("valorModal");

				Date dataFormatada = format.parse(data);

				HistoricoGastos historicoGastos = new HistoricoGastos(id, Integer.parseInt(codigoUsuario), descricao,
						Double.parseDouble(valor), dataFormatada, categoria);

				historicoGastosService.atualizarGasto(historicoGastos);

				Thread.sleep(1000);
				return "redirect:/";
			} else {
				return "login";
			}

		} catch (Exception e) {
			return "failed";
		}

	}

}
