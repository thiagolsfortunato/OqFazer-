package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.User;

public interface Participation {
	
	void updateListParticipation(Long eventId, List<User> user);
	
	void updateListParticipation(Long eventId, User user);
	
	List<User> searchUsers(Long eventId);
}
