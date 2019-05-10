package com.santander.resources;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.santander.domain.Transacoes;
import com.santander.exception.EventException;
import com.santander.jms.producer.IncluirGastoProducer;

@RunWith(MockitoJUnitRunner.class)
public class IncluirGastoResourceResourceTest {

	@InjectMocks
	private IncluirGastoResource incluirGastoResource;

	@Mock
	private IncluirGastoProducer producer;

	@Test
	public void TesteEndPointPostGastoCartao() throws EventException, Exception {

		Transacoes historicoGastos = new Transacoes();
		historicoGastos.setCodigoUsuario(1);
		historicoGastos.setData(new Date());
		historicoGastos.setDescricao("Academia");
		historicoGastos.setId(1);
		historicoGastos.setValor(50.00);

		when(producer.send(historicoGastos)).thenReturn("ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1");

		ResponseEntity<String> response = incluirGastoResource.postGastoCartao(historicoGastos);

		Assert.assertEquals(200, response.getStatusCodeValue());

	}

}
