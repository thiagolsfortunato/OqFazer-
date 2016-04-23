package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.UserDTO;

public class ContextUser implements Serializable{

	/***/
	private static final long serialVersionUID = 3395922774753783569L;

	private UserDTO userDTO;
	private List<UserDTO> usersDTO;
	
	public UserDTO getUserDTO() {
		return userDTO;
	}
	
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<UserDTO> getUsersDTO() {
		return usersDTO;
	}

	public void setUsersDTO(List<UserDTO> usersDTO) {
		this.usersDTO = usersDTO;
	}	
}
