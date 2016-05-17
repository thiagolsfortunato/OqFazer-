package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;

public interface ParticipationDAO {
	
	Long insertParticipation(Long eventId, Long userId);
	
	void deleteParticipation(Long eventId, Long userId);
	
	List<Long> searchUsers(Long eventId);
	
	List<Long> searchEvents(Long userId);

	void updateEventParticipations(long userId, List<Event> events);
	
	void updateUserParticipations(long eventId, List<User> users);
}
