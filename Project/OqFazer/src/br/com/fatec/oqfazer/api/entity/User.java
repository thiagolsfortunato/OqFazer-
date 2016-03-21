package br.com.fatec.oqfazer.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class User {
	
	public static final String TABLE = "";
	public static final String COL_ID = "";
	public static final String COL_NAME = "";
	public static final String COL_PASSWORD = "";
	public static final String COL_EMAIL = "";
	public static final String COL_SEX = "";
	public static final String COL_PHONE = "";
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private char sex;
	private int phone;
	
	
	public User(){};	
	
	public User(Long id, String name, String password, String email, char sex, int phone) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.phone = phone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public static List<String> getColumns() {
		return Lists.newArrayList(COL_ID, COL_NAME, COL_PASSWORD, COL_EMAIL, COL_SEX, COL_PHONE);
	}

	public static String[] getColumnsArray() {
		return new String[] {COL_ID, COL_NAME, COL_PASSWORD, COL_EMAIL, COL_SEX, COL_PHONE};
	}
	
}
