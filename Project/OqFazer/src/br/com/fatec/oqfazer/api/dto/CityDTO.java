package br.com.fatec.oqfazer.api.dto;

public class CityDTO {
	
	private String nome;
	private RegionDTO regionDTO;
	
	public CityDTO(){};
		
	public CityDTO(String nome, RegionDTO regionDTO) {
		super();
		this.nome = nome;
		this.regionDTO = regionDTO;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public RegionDTO getRegionDTO() {
		return regionDTO;
	}
	
	public void setRegionDTO(RegionDTO regionDTO) {
		this.regionDTO = regionDTO;
	}
}
