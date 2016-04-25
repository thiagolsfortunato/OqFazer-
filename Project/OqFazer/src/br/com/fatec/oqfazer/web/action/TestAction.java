
package br.com.fatec.oqfazer.web.action;

import com.opensymphony.xwork2.ActionSupport;

import br.com.fatec.oqfazer.api.dto.UserDTO;

public class TestAction extends ActionSupport {
	
	private UserDTO userDTO;
	public static final String JSP = "jsp";
	
	private static final long serialVersionUID = 1071989853380980252L;
	
	public String builUser(){
		this.setUserDTO(new UserDTO(1l, "user 1", "user1", "user@user", 1111));
		return JSP;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
