package com.test.lms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {
	@Id
	private String auth_name;


	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(String auth_name) {
		super();
		this.auth_name = auth_name;
	}
	
}
