package br.com.fatec.oqfazer.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Category {
	
	public static final String TABLE = "CATEGORY";
	public static final String COL_ID = "CTG_ID";
	public static final String COL_NAME = "CTG_NAME";
	public static final String COL_ID_CATEGORY = "CTG_CATEGORY_ID";
	
	private Long id;
	private String name;
	private Long parent;
	
	public Category(){};
	
	public Category(Long id, String name, Long parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
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
	public Long getParent() {
		return parent;
	}
	public void setParent(Long parent) {
		this.parent = parent;
	}
	
	public static List<String> getColumns() {
		return Lists.newArrayList(COL_ID, COL_NAME, COL_ID_CATEGORY);
	}

	public static String[] getColumnsArray() {
		return new String[] {COL_ID, COL_NAME, COL_ID_CATEGORY};
	}
}
