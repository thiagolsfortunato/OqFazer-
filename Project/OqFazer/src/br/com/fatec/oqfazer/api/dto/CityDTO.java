package br.com.fatec.oqfazer.api.dto;

public class CityDTO {
	private String name; // enum
	private String nome; // string bonita

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
