package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.User;

public interface Participation {
	
	Long insertParticipation(Long eventId, Long userId);
	
	void deleteParticipation(Long eventId, Long userId);
	
	List<Long> searchUsers(Long eventId);
	
	List<Long> searchEvents(Long userId);
}
