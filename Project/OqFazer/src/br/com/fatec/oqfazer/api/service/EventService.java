package br.com.fatec.oqfazer.api.service;

import java.util.List;

import br.com.fatec.oqfazer.api.dto.EventDTO;

public interface EventService {
	
	EventDTO insert(EventDTO eventDTO);
	
	void update(EventDTO eventDTO);
	
	void delete(Long idEventDTO);
	
	List<EventDTO> searchAll();
	
	EventDTO searchById(Long idEventDTO);
}
