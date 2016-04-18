package br.com.fatec.oqfazer.api.dto;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

public class EventDTO {
	
	private Long id;
	private String name;
	private String description;
	private Date registration_date;
	private Date event_date;
	private String local;
	private String imageURL;
	private RegionDTO regionDTO;
	private UserDTO owner;
	private List<UserDTO> participation = Lists.newArrayList();
	private List<CategoryDTO> categories = Lists.newArrayList();
	
	public EventDTO(){};
	
	public EventDTO(Long id, String name, String description, Date registration_date, Date event_date, String local,
			String imageURL, RegionDTO regionDTO, UserDTO owner) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.registration_date = registration_date;
		this.event_date = event_date;
		this.local = local;
		this.imageURL = imageURL;
		this.regionDTO = regionDTO;
		this.owner = owner;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public RegionDTO getRegion() {
		return regionDTO;
	}

	public void setRegion(RegionDTO regionDTO) {
		this.regionDTO = regionDTO;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}
		
	public String toString(){
		return "Event[" + this.id + " - " + this.name + " ]";
	}

	public List<UserDTO> getParticipation() {
		return participation;
	}

	public void setParticipation(List<UserDTO> participation) {
		this.participation = participation;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}	
}
