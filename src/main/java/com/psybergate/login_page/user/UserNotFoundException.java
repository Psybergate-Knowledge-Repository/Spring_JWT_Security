package com.psybergate.login_page.user;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String s) {
		super(s);
	}

	public UserNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public UserNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public UserNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
