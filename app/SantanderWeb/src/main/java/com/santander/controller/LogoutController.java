package com.santander.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public String index(HttpServletRequest request) throws URISyntaxException {

		request.getSession().invalidate();

		return "login";

	}

}
