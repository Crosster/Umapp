package com.eeit162.FWBweb.hc.advertising.exception;

public class EmailAlreadyExistsException extends Exception {

	public EmailAlreadyExistsException(String email) {
		super("此信箱已經存在: " + email);
	}

	private static final long serialVersionUID = 1L;

}
