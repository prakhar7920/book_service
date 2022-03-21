package com.test.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String user_Id;
	private String user_name;
	private String user_email;
	
	@Size()
	private String user_password;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String user_Id, String user_name, String user_email, String user_password) {
		super();
		this.user_Id = user_Id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_Id=" + user_Id + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", user_password=" + user_password + "]";
	}
	
	
	
}
