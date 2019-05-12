package com.santander.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.santander.domain.HistoricoGastos;

@Service
public class HistoricoGastosService {

	public List<HistoricoGastos> getGastosCartao(String token) {

		try {

			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			String url = "http://172.72.0.101:8801/listarGastos";
			headers.add("Authorization", token);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<List<HistoricoGastos>> response = template.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<HistoricoGastos>>() {
					});

			List<HistoricoGastos> historicoGastos = response.getBody();

			return historicoGastos;

		} catch (Exception e) {

			return null;

		}

	}

	public List<HistoricoGastos> getGastosCartaoByDate(String token, String date) {

		try {

			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			String url = "http://172.72.0.101:8801/listarGastosByDate?date=" + date;
			headers.add("Authorization", token);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<List<HistoricoGastos>> response = template.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<HistoricoGastos>>() {
					});

			List<HistoricoGastos> gastosCartao = response.getBody();

			return gastosCartao;

		} catch (Exception e) {

			return null;

		}

	}

	public void atualizarGasto(HistoricoGastos historicoGastos) throws Exception {

		try {

			RestTemplate restTemplate = new RestTemplate();
			String url = "http://172.72.0.100:8800/atualizarGasto";

			HttpEntity<HistoricoGastos> entity = new HttpEntity<HistoricoGastos>(historicoGastos);
			restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		} catch (Exception e) {

			throw new Exception("Ocorreu erro na tentativa de atualizacao de Gasto. MSG: " + e);

		}

	}

}
