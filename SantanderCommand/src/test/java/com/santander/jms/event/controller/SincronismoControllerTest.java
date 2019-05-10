package com.santander.jms.event.controller;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.santander.exception.EventException;
import com.santander.services.TransacoesService;
import com.santander.sincronismo.HistoricoGastosSync;
import com.santander.sincronismo.Sincronismo;

@RunWith(MockitoJUnitRunner.class)
public class SincronismoControllerTest {

	@InjectMocks
	private SincronismoController sincronismoController;

	@Mock
	private MongoTemplate mongoTemplate;

	@Mock
	private TransacoesService historicoGastosService;

	@Test
	public void validarInsertMongoTemplate() throws EventException {

		HistoricoGastosSync historicoGastosSync = new HistoricoGastosSync(1, "Smart Fit", 50.00, new Date(),
				"Academia");

		Sincronismo sincronismo = new Sincronismo();
		sincronismo.setAcao("I");
		sincronismo.setObject(historicoGastosSync);

		when(mongoTemplate.save(sincronismo.getObject())).thenReturn(historicoGastosSync);

		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		sincronismoController.fire(sincronismo, id);

		Mockito.verify(mongoTemplate, Mockito.times(1)).save(sincronismo.getObject());

	}

	@Test
	public void validarAtualizacaoMongoTemplate() throws EventException {

		HistoricoGastosSync historicoGastosSync = new HistoricoGastosSync(1, "Smart Fit", 50.00, new Date(),
				"Academia");
		String id = "ID:SAO-3MKH2M2-51364-1557029444957-1:7:1:1:1";

		Sincronismo sincronismo = new Sincronismo();
		sincronismo.setAcao("A");
		sincronismo.setNomeObjeto("HistoricoGastos");
		sincronismo.setObject(historicoGastosSync);

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(((HistoricoGastosSync) sincronismo.getObject()).getId()));

		when(mongoTemplate.findOne(query, HistoricoGastosSync.class)).thenReturn(historicoGastosSync);

		sincronismoController.fire(sincronismo, id);

		Mockito.verify(historicoGastosService, Mockito.times(1)).update(historicoGastosSync,
				(HistoricoGastosSync) sincronismo.getObject());

	}

}
