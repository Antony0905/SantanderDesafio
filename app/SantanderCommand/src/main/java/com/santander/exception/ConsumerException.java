package com.santander.exception;

/**
 * Excecao a ser disparada sempre que ocorrer um erro na camada de Consumer.
 * 
 * @author Everis - Matheus Antony
 * @version 1.0
 * @since 27/04/2019
 */
public class ConsumerException extends Exception {

	/**
	 * Serial Version Defaul.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrao
	 */
	public ConsumerException() {

		super();
	}

	/**
	 * Exception com tres parametros e um Throwable
	 * 
	 * @param arg0 String
	 * @param arg1 Throwable
	 * @param arg2 boolean
	 * @param arg3 boolean
	 */
	public ConsumerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {

		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * Exception com um parametro e um Throwable
	 * 
	 * @param arg0 String
	 * @param arg1 Throwable
	 */
	public ConsumerException(String arg0, Throwable arg1) {

		super(arg0, arg1);
	}

	/**
	 * Exception com um parametro
	 * 
	 * @param arg0 String
	 */
	public ConsumerException(String arg0) {

		super(arg0);
	}

	/**
	 * Exception com um Throwable
	 * 
	 * @param arg0 Throwable
	 */
	public ConsumerException(Throwable arg0) {

		super(arg0);
	}
}
