package br.com.fatec.api.dao;

import java.util.List;
import br.com.fatec.api.entity.User;

public interface UserDAO {
	
	Long insertUser(User user);
	
	Long deleteUser(Long id);
	
	void updateUser(User user);
	
	User searchUserById(Long id);
	
	List<User> searchAllUser();
}
