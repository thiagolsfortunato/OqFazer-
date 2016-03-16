package br.com.fatec.login.api.entity;

public class GrupoPapel {
	
	public static final String TABLE = "OQFAZER_TESTE_GRUPO_PAPEL";
	public static final String COL_ID = "ID";
	public static final String COL_NOME = "NOME";
	public static final String COL_DESCRICAO = "DESCRICAO";
	
	private Long id;
	private String name;
	private String description;
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
