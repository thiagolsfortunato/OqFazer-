package br.com.fatec.oqfazer.core.service;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.oqfazer.api.dao.Participation;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.fatec.oqfazer.api.service.UserService;
import br.com.fatec.oqfazer.core.converter.EventDTOConverter;
import br.com.fatec.oqfazer.core.converter.UserDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class UserServiceImpl implements UserService{
	
	private UserDAO userDao;
	private Participation participation;
	
	private UserDTOConverter userConverter;
	private EventDTOConverter eventConverter;
	
	public UserServiceImpl (){
		this.userDao = ImplFinder.getImpl(UserDAO.class);
		this.participation = ImplFinder.getImpl(Participation.class);
		
		this.userConverter = ImplFinder.getFinalImpl(UserDTOConverter.class);
		this.eventConverter = ImplFinder.getFinalImpl(EventDTOConverter.class);
	}

	@Override
	public UserDTO insert(UserDTO userDTO) {
		User entityUser = this.userConverter.toEntity(userDTO);
		Long id = this.userDao.insertUser(entityUser);
		List<EventDTO> eventsList = userDTO.getEvents();
		this.updateParticipations(id, eventsList);
		userDTO.setId(id);
		return userDTO;
	}
	
	
	public void updateParticipations(long userId, List<EventDTO> eventsList){
		List<Event> events = this.eventConverter.toEntity(eventsList);
		this.participation.updateEventParticipations(userId, events);
	}
	
	@Override
	public void update(UserDTO userDTO) {
		User entityUser = this.userConverter.toEntity(userDTO);
		this.userDao.updateUser(entityUser);
		List<EventDTO> participationsEvent = userDTO.getEvents();
		this.updateParticipations(userDTO.getId(), participationsEvent);
	}
	
	@Override
	public void delete(Long userId) {
		this.updateParticipations(userId, new ArrayList<EventDTO>());
		this.userDao.deleteUser(userId);
	}

	@Override
	public List<UserDTO> searchAll() {
		List<User> users = this.userDao.searchAllUsers();
		return this.userConverter.toDTO(users);
	}

	@Override
	public UserDTO searchById(Long userId) {
		User user = this.userDao.searchUserById(userId);
		return this.userConverter.toDTO(user);
	}

	@Override
	public UserDTO searchUserByEmailAndPassword(String email, String password) {
		User user = this.userDao.searchUserByEmailAndPassword(email, password);
		UserDTO userDTO = null;
		if (user != null){
			userDTO = this.userConverter.toDTOSimple(user);
		}
		return userDTO;
	}

}
