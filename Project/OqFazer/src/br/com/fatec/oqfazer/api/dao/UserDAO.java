package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;

public interface UserDAO {
	
	Long insertUser(User user);
	
	void deleteUser(Long id);
	
	void updateUser(User user);
	
	User searchUserById(Long id);
	
	List<User> searchAllUsers();
	
	List<User> searchUsersByIds(List<Long> idsUser);

	List<Event> searchByUsers(Long idEvent);
}
