package com.eeit162.FWBweb.hc.advertising.exception;

public class AccountNotEnabledException extends Exception {

	public AccountNotEnabledException() {
		super("此帳號已被停權");
	}

	private static final long serialVersionUID = 1L;

}
