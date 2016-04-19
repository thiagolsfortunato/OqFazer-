package br.com.fatec.oqfazer.api.dto;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import br.com.fatec.oqfazer.api.dao.Participation;

public class UserDTO {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private int phone;
	/*Esta lista de Participações seria o que ?
	 *  Não existe lista da classe participações, ela representa os Users
	 *  Acho que gostaria de saber quais eventos o User esta, e pra isso voce ja tem a lista ali em baixo 
	 */
	private List<Participation> participations = Lists.newArrayList();
	private List<EventDTO> events = Lists.newArrayList();
	private Set<EventDTO> eventsUser = Sets.newHashSet();
	
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
	
	public List<Participation> getParticipations() {
		return participations;
	}

	public List<EventDTO> getEventos() {
		return events;
	}

	public void setEvents(List<EventDTO> eventos) {
		this.events = eventos;
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
	
	 // verificar essa comparação !
	public boolean isOwner(String UserName){
		for (EventDTO event: this.getEventsUser()){
			if (event.getName().equals(UserName)) return true;
		}
		return false;
	}

	public Long getStartSession() {
		return startSession;
	}

	public void setStartSession(Long startSession) {
		this.startSession = startSession;
	}

	public Set<EventDTO> getEventsUser() {
		return eventsUser;
	}

	public void setEventsUser(Set<EventDTO> eventsUser) {
		this.eventsUser = eventsUser;
	}

}
