package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;

import br.com.fatec.oqfazer.api.dto.UserDTO;

public class ContextLogin implements Serializable{

	/***/
	private static final long serialVersionUID = 2310542235874985181L;
	
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
