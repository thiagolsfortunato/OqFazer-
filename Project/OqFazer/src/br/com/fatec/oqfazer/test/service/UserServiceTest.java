package br.com.fatec.oqfazer.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.test.common.TestScenario;

public class UserServiceTest extends TestScenario {

	@Test
	public void insert(){
		UserDTO dto = new UserDTO(null, "William", "william01", "william@test", 33334444);
		//dto.setIsOwner(true);
		dto.setParticipationEvents(this.getEvents(1l));
		
		UserDTO saved = this.userService.insert(dto);
		saved = this.userService.searchById(saved.getId());
		
		Assert.assertEquals(new Long(4), saved.getId());
		Assert.assertEquals("William", saved.getName());
		Assert.assertEquals("william01", saved.getPassword());
		Assert.assertEquals("william@test", saved.getEmail());
		Assert.assertEquals(33334444, saved.getPhone());
		//Assert.assertTrue(saved.getIsOwner());
		
		//Assert.assertEquals(1, saved.getMyEvents().size());
		//Assert.assertEquals(new Long(1l), saved.getMyEvents().get(0).getId());
		
		Assert.assertEquals(1, saved.getParticipationEvents().size());
		//ArrayList<EventDTO> participationEvents = Lists.newArrayList(saved.getMyEvents());
		//Assert.assertEquals(new Long(1), saved.getMyEvents().get(0).getId());
	}
	
	@Test
	public void delete(){
		UserDTO dto = this.usersDTO.get(3l);
		dto.setMyEvents(this.getEvents(3l));
		
		UserDTO saved = this.userService.insert(dto);
		
		this.userService.delete(saved.getId());
		
		Assert.assertEquals(null, saved.getId());
	}
	
	@Test
	public void update(){
		UserDTO dto = this.usersDTO.get(1l);
		dto.setName("William Cezar");
		dto.setPassword("william02");
		dto.setEmail("william@test2");
		dto.setMyEvents(this.getEvents(2l));
		
		this.userService.update(dto);
		dto = this.userService.searchById(dto.getId());
		
		Assert.assertEquals(new Long(1), dto.getId());
		Assert.assertEquals("William Cezar", dto.getName());
		Assert.assertEquals("william02", dto.getPassword());
		Assert.assertEquals("william@test2", dto.getEmail());
	}
	
	@Test
	public void searchAll(){
		List<UserDTO> users = this.userService.searchAll();
		
		Assert.assertEquals(3, users.size());
		Assert.assertEquals("William", users.get(0).getName());
		Assert.assertEquals("Thiago", users.get(1).getName());
		Assert.assertEquals("Carlos", users.get(2).getName());
	}
}
