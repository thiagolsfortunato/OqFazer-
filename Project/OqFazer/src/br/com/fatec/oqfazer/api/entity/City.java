package br.com.fatec.oqfazer.api.entity;

public enum City {
	
	SAO_JOSE_DOS_CAMPOS("São José dos Campos"), TAUBATE("Taubaté"), CACAPAVA("Caçapava"), JACAREI("Jacareí");
	
	public static final String TABLE = "CITY";
	public static final String COL_NAME = "CT_NAME";
	
	private String nome;
	
	private City(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
