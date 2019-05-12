package com.santander.resources;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.santander.domain.SistemaCredenciado;
import com.santander.exception.EventException;
import com.santander.jms.producer.CredenciarSistemaProducer;

@RunWith(MockitoJUnitRunner.class)
public class CredenciarSistemaResourceTest {

	@InjectMocks
	private CredenciarSistemaResource credenciarSistemaResource;

	@Mock
	private CredenciarSistemaProducer producer;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void TesteEndPointCredenciarSistema() throws EventException, Exception {

		SistemaCredenciado sistemaCredenciado = new SistemaCredenciado("Maria", "maria@maria.com", "maria");

		when(producer.send(sistemaCredenciado)).thenReturn("ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1");
		when(bCryptPasswordEncoder.encode(sistemaCredenciado.getSenha()))
				.thenReturn("$2a$10$RXUmuF//FvuE3x0HLhvZD.Q.jufe8hnt6xbIaq5d7sHiJI2SjVX7S");

		ResponseEntity<String> response = credenciarSistemaResource.credenciarSistema(sistemaCredenciado);

		Assert.assertEquals(200, response.getStatusCodeValue());

	}

}
