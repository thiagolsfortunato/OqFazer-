package br.com.fatec.oqfazer.core.converter;

import java.util.List;
import com.google.common.collect.Lists;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.Participation;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class UserDTOConverter implements DTOConverter<User, UserDTO>{
	
	private EventDAO eventDAO;
	private Participation participationDAO;
	private EventDTOConverter eventConverter;
	
	public UserDTOConverter() {
		ImplFinder.getImpl(UserDAO.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.participationDAO = ImplFinder.getImpl(Participation.class);
	}
	
	@Override
	public UserDTO toDTO(User entityUser) {
		return this.toDTO(entityUser, true);
	}
	
	public UserDTO toDTO(User entityUser, boolean convertDependences){
		UserDTO dtoUser = this.toDTOSimple(entityUser);
		Long id = entityUser.getId();
		if(id!=null && convertDependences){
			List<Long> idsEvents = this.participationDAO.searchEvents(id);
			List<Event> entityEvents = this.eventDAO.searchEventsByListIds(idsEvents);
			List<EventDTO> eventsDTO = this.eventConverter.toDTO(entityEvents);
			dtoUser.setEvents(eventsDTO);
			dtoUser.setIsOwner(dtoUser.isOwner(dtoUser.getName()));
		}
		return dtoUser;
	}
	
	public UserDTO toDTOSimple (User entityUser){
		UserDTO dto = new UserDTO();
		dto.setId(entityUser.getId());
		dto.setName(entityUser.getName());
		dto.setEmail(entityUser.getEmail());
		dto.setPassword(entityUser.getPassword());
		dto.setPhone(entityUser.getPhone());
		return dto;
	}
	
	@Override
	public List<UserDTO> toDTO(List<User> users) {
		return this.toDTO(users, false);
	}
	
	public List<UserDTO> toDTOSimple(List<User> users) {
		return this.toDTO(users, true);
	}
	
	@Override
	public User toEntity(UserDTO dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setPhone(dto.getPhone());
		return entity;
	}
	@Override
	public List<User> toEntity(List<UserDTO> dtos) {
		List<User> entities = Lists.newArrayList();
		for(UserDTO dto: dtos){
			entities.add(this.toEntity(dto));
		}
		return entities;
	}
	
	private List<UserDTO> toDTO (List<User> entities, boolean isSimple){
		List<UserDTO> dtos = Lists.newArrayList();
		for (User entity: entities){
			dtos.add(this.toDTO(entity, isSimple));
		}
		return dtos;
	}

}
