package com.santander.jms.event.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.santander.exception.EventException;
import com.santander.jms.event.EventController;
import com.santander.services.TransacoesService;
import com.santander.sincronismo.HistoricoGastosSync;
import com.santander.sincronismo.Sincronismo;

@Component
public class SincronismoController implements EventController<Sincronismo> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private TransacoesService transacoesService;

	@Override
	public void fire(Sincronismo sincronismo, String id) throws EventException {

		try {

			logger.info("[SYNC] Sincronizando Base de Dados");

			if (sincronismo.getAcao().equals("I")) {
				mongoTemplate.save(sincronismo.getObject());
			}

			if (sincronismo.getAcao().equals("A")) {
				if (sincronismo.getNomeObjeto().equals("HistoricoGastos")) {

					HistoricoGastosSync object = (HistoricoGastosSync) sincronismo.getObject();

					Query query = new Query();
					query.addCriteria(Criteria.where("id").is(object.getId()));

					HistoricoGastosSync historicoGastosSync = mongoTemplate.findOne(query, HistoricoGastosSync.class);
					transacoesService.update(historicoGastosSync, object);

				}
			}

		} catch (Exception e) {
			throw new EventException(e);
		}
	}

}
