package br.com.fatec.oqfazer.api.entity;

public enum City {
	SAO_JOSE_DOS_CAMPOS("São José dos Campos"), TAUBATE("Taubaté"), CACAPAVA("Caçapava"), JACAREI("Jacareí");
	
	private String nome;
	
	private City(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
