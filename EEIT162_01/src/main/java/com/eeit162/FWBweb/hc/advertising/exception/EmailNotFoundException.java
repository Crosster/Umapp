package com.eeit162.FWBweb.hc.advertising.exception;

public class EmailNotFoundException extends Exception {

	public EmailNotFoundException(String email) {
		super("此信箱不存在: " + email);
	}

	private static final long serialVersionUID = 1L;

}
