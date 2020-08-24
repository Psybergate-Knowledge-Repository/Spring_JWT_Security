package com.psybergate.login_page.role;

public class RoleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RoleNotFoundException() {
	}

	public RoleNotFoundException(String s) {
		super(s);
	}

	public RoleNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public RoleNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public RoleNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
