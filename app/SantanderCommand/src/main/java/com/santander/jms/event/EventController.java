package com.santander.jms.event;

import com.santander.exception.EventException;

/**
 * Interface que devera ser implementada por todos os Controllers de
 * Processamento de Mensagens.
 * 
 * @author Everis - Matheus Antony
 * @version 1.0
 * @since 27/04/2019
 *
 * @param <O>
 */
public interface EventController<O> {

	/**
	 * Metodo responsavel por processar mensagem recuperadas por consumidores de
	 * Filas.
	 * 
	 * @param object Objeto a ser processado.
	 * @param id     Identificador da Mensagem.
	 * @throws EventException Excecao a ser disparada sempre que ocorrer um erro no
	 *                        processamento da mensagem.
	 */
	void fire(O object, String id) throws EventException;

}
