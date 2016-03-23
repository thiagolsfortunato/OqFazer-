package br.com.fatec.oqfazer.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Category {
	
	public static final String TABLE = "CATEGORY";
	public static final String COL_ID = "CTG_ID";
	public static final String COL_NAME = "CTG_NAME";
	public static final String COL_ID_CATEGORY = "CTG_ID_CATEGORY";
	
	private Long id;
	private String name;
	private Category category;
	
	public Category(){};
	
	public Category(Long id, String name, Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public static List<String> getColumns() {
		return Lists.newArrayList(COL_ID, COL_NAME, COL_ID_CATEGORY);
	}

	public static String[] getColumnsArray() {
		return new String[] {COL_ID, COL_NAME, COL_ID_CATEGORY};
	}
}
