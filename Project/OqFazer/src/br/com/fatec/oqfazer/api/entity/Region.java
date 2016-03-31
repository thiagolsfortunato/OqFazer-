package br.com.fatec.oqfazer.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Region {
	
	public static final String TABLE = "REGION";
	public static final String COL_ID = "RGN_ID";
	public static final String COL_NAME = "RGN_NAME";
	
	private Long id;
	private String name;
	private List<City> city;
		
	public Region(){};
	
	public Region(Long id, String name, List<City> city) {
		this.id = id;
		this.name = name;
		this.city = city;
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
	public List<City> getCity() {
		return city;
	}
	public void setCity(List<City> city) {
		this.city = city;
	}
	
	public static List<String> getColumns() {
		return Lists.newArrayList(COL_ID, COL_NAME);
	}

	public static String[] getColumnsArray() {
		return new String[] {COL_ID, COL_NAME};
	}
}
