package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.UserDTO;

public class ContextUser implements Serializable{

	/***/
	private static final long serialVersionUID = 3395922774753783569L;

	private UserDTO user;
	private List<UserDTO> users;
	
	
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
