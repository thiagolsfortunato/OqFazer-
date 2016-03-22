package br.com.fatec.oqfazer.api.entity;

public enum City {
	SAO_JOSE_DOS_CAMPOS("S�o Jos� dos Campos"), TAUBATE("Taubat�"), CACAPAVA("Ca�apava"), JACAREI("Jacare�");
	
	private String nome;
	
	private City(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
