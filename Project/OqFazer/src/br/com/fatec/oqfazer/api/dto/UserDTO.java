package br.com.fatec.oqfazer.api.dto;

import java.util.List;
import com.google.common.collect.Lists;

public class UserDTO {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private int phone;
	private List<EventDTO> participationEvents = Lists.newArrayList();
	private List<EventDTO> myEvents = Lists.newArrayList();
	
	//atributes screen 
	private Boolean isOwner;
	private Long startSession;
	
	public UserDTO (){
	}
	
	public UserDTO (Long id, String name, String password, String email, int phone){
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public List<EventDTO> getParticipationEvents() {
		return participationEvents;
	}

	public void setParticipationEvents(List<EventDTO> events) {
		this.participationEvents = events;
	}
	
	public List<EventDTO> getMyEvents(){
		return myEvents;
	}
	
	public void setMyEvents(List<EventDTO> myEvents){
		this.myEvents = myEvents;
	}
	
	public String toString(){
		return "User[" + this.id + " - " + this.name + "]";
	}

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	public boolean isOwner(String UserName){
		for (EventDTO event: this.getMyEvents()){
			if (event.getOwner().getName().equals(UserName)) return true;
		}
		return false;
	}

	public Long getStartSession() {
		return startSession;
	}

	public void setStartSession(Long startSession) {
		this.startSession = startSession;
	}
}
