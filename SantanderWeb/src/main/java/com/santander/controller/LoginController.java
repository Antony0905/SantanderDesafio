package com.santander.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santander.domain.Cliente;
import com.santander.domain.dto.ClienteDTO;
import com.santander.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public String efetuaLogin(@ModelAttribute("cliente") Cliente cliente, HttpSession session, Model model) {

		if (cliente.getEmail() != null && cliente.getSenha() != null) {

			String token = loginService.authenticate(cliente);
			if (token != null) {

				ClienteDTO clienteDTO = new ClienteDTO(cliente.getEmail(), cliente.getSenha(), token);
				session.setAttribute("clienteLogado", clienteDTO);
				return "redirect:/";

			} else {

				model.addAttribute("error", "Usuário ou Senha inválidos.");
				return "login";

			}

		}
		return "login";
	}

}
