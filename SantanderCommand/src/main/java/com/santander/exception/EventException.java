package com.santander.exception;

public class EventException extends Exception {

	/**
	 * Serial Version Defaul.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrao
	 */
	public EventException() {

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
	public EventException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {

		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * Exception com um parametro e um Throwable
	 * 
	 * @param arg0 String
	 * @param arg1 Throwable
	 */
	public EventException(String arg0, Throwable arg1) {

		super(arg0, arg1);
	}

	/**
	 * Exception com um parametro
	 * 
	 * @param arg0 String
	 */
	public EventException(String arg0) {

		super(arg0);
	}

	/**
	 * Exception com um Throwable
	 * 
	 * @param arg0 Throwable
	 */
	public EventException(Throwable arg0) {

		super(arg0);
	}
}