package com.santander.jms.event.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santander.domain.Transacoes;
import com.santander.exception.EventException;
import com.santander.jms.event.EventController;
import com.santander.jms.producer.SincronismoProducer;
import com.santander.services.TransacoesService;
import com.santander.sincronismo.HistoricoGastosSync;
import com.santander.sincronismo.Sincronismo;

@Component
public class AtualizarGastoController implements EventController<Transacoes> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransacoesService transacoesService;

	@Autowired
	private SincronismoProducer producer;

	@Override
	public void fire(Transacoes object, String id) throws EventException {

		try {

			Transacoes transacoes = transacoesService.findOneById(object.getId());

			if (transacoes != null) {

				BeanUtils.copyProperties(object, transacoes);
				transacoesService.save(transacoes);

				logger.info("Historico de Gastos Atualizado com Sucesso!");

				HistoricoGastosSync historicoGastosSync = new HistoricoGastosSync();
				BeanUtils.copyProperties(transacoes, historicoGastosSync);
				Sincronismo sincronismo = new Sincronismo();
				sincronismo.setAcao("A");
				sincronismo.setNomeObjeto("HistoricoGastos");
				sincronismo.setObject(historicoGastosSync);

				producer.send(sincronismo);

			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			logger.error("Ocorreu um erro. Identificador da Mensagem = [" + id + "]");
			throw new EventException(e);
		}
	}

}
