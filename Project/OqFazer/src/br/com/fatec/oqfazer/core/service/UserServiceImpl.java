package br.com.fatec.oqfazer.core.service;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.ParticipationDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.fatec.oqfazer.api.service.EventService;
import br.com.fatec.oqfazer.api.service.UserService;
import br.com.fatec.oqfazer.core.converter.EventDTOConverter;
import br.com.fatec.oqfazer.core.converter.UserDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	private ParticipationDAO participationDAO;
	private EventDAO eventDAO;
	private EventService eventService;

	private UserDTOConverter userConverter;
	private EventDTOConverter eventConverter;

	public UserServiceImpl() {
		this.userDAO = ImplFinder.getImpl(UserDAO.class);
		this.participationDAO = ImplFinder.getImpl(ParticipationDAO.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.eventService = ImplFinder.getImpl(EventService.class);
		
		this.userConverter = ImplFinder.getFinalImpl(UserDTOConverter.class);
		this.eventConverter = ImplFinder.getFinalImpl(EventDTOConverter.class);
	}

	@Override
	public UserDTO insert(UserDTO userDTO) {
		User entityUser = this.userConverter.toEntity(userDTO);
		Long id = this.userDAO.insertUser(entityUser);
		List<EventDTO> eventsList = userDTO.getParticipationEvents();
		if (eventsList == null)
			this.updateParticipations(id, eventsList);
		userDTO.setId(id);
		return userDTO;
	}

	public void updateParticipations(long userId, List<EventDTO> eventsList) {
		List<Event> events = this.eventConverter.toEntity(eventsList);
		this.participationDAO.updateEventParticipations(userId, events);
	}

	@Override
	public void update(UserDTO userDTO) {
		User entityUser = this.userConverter.toEntity(userDTO);
		this.userDAO.updateUser(entityUser);
		if (!userDTO.getParticipationEvents().isEmpty())
			this.updateParticipations(userDTO.getId(), userDTO.getParticipationEvents());
	}

	@Override
	public void delete(Long userId) {
		UserDTO userDTO = this.searchById(userId);
		
		for (EventDTO eventDTO : userDTO.getMyEvents()) {
			for(UserDTO userEventDTO : eventDTO.getParticipation()) {
				User user = this.userConverter.toEntity(userEventDTO);
				this.participationDAO.deleteParticipation(eventDTO.getId(), user.getId());
			}
			this.eventService.delete(eventDTO.getId());
		}
		
		for(EventDTO eventDTO: userDTO.getParticipationEvents()){
			this.participationDAO.deleteParticipation(eventDTO.getId(), userDTO.getId());
		}
		
		this.userDAO.deleteUser(userId);
	}

	@Override
	public List<UserDTO> searchAll() {
		List<User> users = this.userDAO.searchAllUsers();
		return this.userConverter.toDTO(users);
	}

	@Override
	public UserDTO searchById(Long userId) {
		User user = this.userDAO.searchUserById(userId);
		UserDTO dtoUser = this.userConverter.toDTO(user);
		
		if (dtoUser != null) {
			List<Event> myEvents = this.eventDAO.searchEventsByIdUser(dtoUser.getId());
			if (!myEvents.isEmpty()) {
				List<EventDTO> myEventsDTO = this.eventConverter.toDTO(myEvents);
				dtoUser.setMyEvents(myEventsDTO);
			}

			List<Long> idsEvents = this.participationDAO.searchEvents(dtoUser.getId());
			if (!idsEvents.isEmpty()) {
				List<Event> entityEvents = this.eventDAO.searchEventsByListIds(idsEvents);
				List<EventDTO> eventsDTO = this.eventConverter.toDTO(entityEvents);
				dtoUser.setParticipationEvents(eventsDTO);
			}
		}

		return dtoUser;
	}

	@Override
	public UserDTO searchUserByEmailAndPassword(String email, String password) {
		User user = this.userDAO.searchUserByEmailAndPassword(email, password);
		UserDTO userDTO = null;
		if (user != null) {
			userDTO = this.userConverter.toDTOSimple(user);
		}
		return userDTO;
	}

}
