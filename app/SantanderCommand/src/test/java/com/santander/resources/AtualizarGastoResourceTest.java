package com.santander.resources;

import static org.mockito.Mockito.when;

import java.util.Date;

import javax.jms.JMSException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.santander.domain.Transacoes;
import com.santander.exception.EventException;
import com.santander.jms.producer.AtualizarGastoProducer;

@RunWith(MockitoJUnitRunner.class)
public class AtualizarGastoResourceTest {

	@InjectMocks
	private AtualizarGastoResource atualizarGastoResource;

	@Mock
	private AtualizarGastoProducer producer;

	@Test
	public void TesteEndPointAtualizarGasto() throws EventException, JMSException {

		Transacoes historicoGastos = new Transacoes();

		historicoGastos.setCategoria("Academia");
		historicoGastos.setCodigoUsuario(1);
		historicoGastos.setData(new Date());
		historicoGastos.setDescricao("Smart Fit");
		historicoGastos.setId(1);
		historicoGastos.setValor(50.00);

		when(producer.send(historicoGastos)).thenReturn("ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1");

		ResponseEntity<String> response = atualizarGastoResource.postGastoCartao(historicoGastos);

		Assert.assertEquals(200, response.getStatusCodeValue());

	}

}
