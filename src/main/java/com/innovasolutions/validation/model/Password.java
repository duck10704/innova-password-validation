package com.innovasolutions.validation.model;

import java.io.Serializable;

public class Password implements Serializable {

	private static final long serialVersionUID = 3818165577572576277L;

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
