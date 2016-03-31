package br.com.fatec.oqfazer.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public enum City {
	
	SAO_JOSE_DOS_CAMPOS("São José dos Campos"), TAUBATE("Taubaté"), CACAPAVA("Caçapava"), JACAREI("Jacareí"),
	SAO_PAULO("São Paulo"), GUARULHOS("Guarulhos"), ARUJA("Arujá"), 
	CARAGUATATUBA("Caraguatatuba"), UBATUBA("Ubatuba"), ILHA_BELA("Ilha Bela"), SAO_SEBASTIAO("São Sebastião");
	
	public static final String TABLE = "CITY";
	public static final String COL_ID = "CTY_REGION_ID";
	public static final String COL_NAME = "CTY_NAME";
	
	private Region region;
	private String nome;
	
	private City(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	public static List<String> getColumns() {
		return Lists.newArrayList(COL_ID, COL_NAME);
	}

	public static String[] getColumnsArray() {
		return new String[] {COL_ID, COL_NAME};
	}
}
