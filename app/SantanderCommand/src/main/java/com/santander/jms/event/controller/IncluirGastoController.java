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
import com.santander.services.ClienteService;
import com.santander.sincronismo.HistoricoGastosSync;
import com.santander.sincronismo.Sincronismo;

@Component
public class IncluirGastoController implements EventController<Transacoes> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private TransacoesService transacoesService;

	@Autowired
	private SincronismoProducer producer;

	@Override
	public void fire(Transacoes transacoes, String id) throws EventException {

		try {

			if (clienteService.validarCodigoUsuarioCliente(transacoes.getCodigoUsuario())) {

				transacoesService.save(transacoes);
				logger.info("Gasto Inserido com Sucesso!");

				HistoricoGastosSync historicoGastosSync = new HistoricoGastosSync();
				BeanUtils.copyProperties(transacoes, historicoGastosSync);
				Sincronismo sincronismo = new Sincronismo();
				sincronismo.setAcao("I");
				sincronismo.setObject(historicoGastosSync);

				producer.send(sincronismo);

			} else {

				logger.error("[FAILED] Codigo Usuario " + transacoes.getCodigoUsuario() + " Inexistente");

			}

		} catch (Exception e) {
			logger.error("Ocorreu um erro. Identificador da Mensagem = [" + id + "]");
			throw new EventException(e);
		}
	}

}
