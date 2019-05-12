package com.santander.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.santander.domain.Cliente;

@Service
public class LoginService {

	public String authenticate(Cliente cliente) {

		try {

			RestTemplate restTemplate = new RestTemplate();
			String url = "http://172.72.0.101:8801/login";

			HttpEntity<Cliente> entity = new HttpEntity<Cliente>(cliente);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			String token = response.getHeaders().getFirst("Authorization");

			return token;

		} catch (Exception e) {

			return null;

		}

	}

}
