package com.santander.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.santander.domain.Transacoes;
import com.santander.repository.TransacoesRepository;
import com.santander.sincronismo.HistoricoGastosSync;

@Service
public class TransacoesService {

	@Autowired
	private TransacoesRepository transacoesRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(Transacoes transacoes) {
		transacoesRepository.save(transacoes);
	}

	public Transacoes findOneById(Integer id) {
		return transacoesRepository.findOneById(id);
	}

	public void update(HistoricoGastosSync historicoGastosSync, HistoricoGastosSync object) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(historicoGastosSync.getId()));

		Update update = new Update();
		update.set("descricao", object.getDescricao());
		update.set("categoria", object.getCategoria());
		mongoTemplate.updateFirst(query, update, HistoricoGastosSync.class);

	}

}
