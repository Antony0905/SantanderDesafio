package com.santander.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.santander.domain.HistoricoGastos;

@Service
public class HistoricoGastosService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<HistoricoGastos> findByCodigoUsuario(Integer codigoUsuario) {

		Query query = new Query();
		query.addCriteria(Criteria.where("codigoUsuario").is(codigoUsuario));
		query.with(new Sort(Sort.Direction.DESC, "data"));

		return mongoTemplate.find(query, HistoricoGastos.class);

	}

	public List<HistoricoGastos> findByCodigoUsuarioAndData(Integer codigoUsuario, Date startDate) {

		Query query = new Query();
		Date endDate = new Date(startDate.getTime() + 24 * 60 * 60 * 1000);

		query.addCriteria(Criteria.where("codigoUsuario").is(codigoUsuario)
				.andOperator(Criteria.where("data").gte(startDate), Criteria.where("data").lt(endDate)));

		return mongoTemplate.find(query, HistoricoGastos.class);

	}

}
