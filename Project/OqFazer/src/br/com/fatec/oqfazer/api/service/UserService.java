package br.com.fatec.oqfazer.api.service;

import java.util.List;

import br.com.fatec.oqfazer.api.dto.UserDTO;

public interface UserService {
	
	UserDTO insert(UserDTO userDTO);
	
	void update (UserDTO userDTO);
	
	void delete (Long idUserDTO);
	
	List<UserDTO> searchAll();
	
	UserDTO searchById(Long IdUserDTO);
	
	UserDTO searchByUserAndPassword(String login, String password);

}
