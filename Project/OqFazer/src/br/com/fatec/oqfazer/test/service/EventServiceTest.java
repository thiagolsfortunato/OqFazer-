package br.com.fatec.oqfazer.test.service;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.test.common.TestScenario;
import org.junit.Assert;

public class EventServiceTest extends TestScenario {

	@Test
	public void insert(){
		EventDTO event = new EventDTO(null, "Virada Cultural", "descricao", null, new Date(06, 06, 2016), "Ibirapuera", null, this.getRegionsDTO(1l).get(0), this.getUsersDTO(1l).get(0));
		event.setCategories(this.getCategories(1l));
		event.setParticipation(this.getUsersDTO(1l));
		
		EventDTO saved = this.eventService.insert(event);
		saved = this.eventService.searchById(saved.getId());
		
		Assert.assertEquals(new Long(4), saved.getId());
		Assert.assertEquals("Virada Cultural", saved.getName());
		Assert.assertEquals("descricao", saved.getDescription());
		Assert.assertEquals("Ibirapuera", saved.getLocal());
		Assert.assertNull(saved.getImageURL());
	}
	
	@Test
	public void delete(){
		EventDTO event = this.eventsDTO.get(3l);
		event.setCategories(this.getCategories(3l));
		event.setParticipation(this.getUsersDTO(3l));
		
		EventDTO saved = this.eventService.insert(event);
		
		this.eventService.delete(saved.getId());
	}
	
	@Test
	public void update(){
		EventDTO event = this.eventsDTO.get(3l);
		event.setName("Anitta");
		event.setDescription("Animado");
		event.setLocal("São José dos Campos");
		event.setImageURL("image5");
		event.setCategories(this.getCategories(3l));
		event.setParticipation(this.getUsersDTO(3l));
		
		EventDTO saved = this.eventService.insert(event);
		
		this.eventService.update(saved);
		
		Assert.assertEquals(new Long(4), saved.getId());
		Assert.assertEquals("Anitta", saved.getName());
		Assert.assertEquals("Animado", saved.getDescription());
		Assert.assertEquals("São José dos Campos", saved.getLocal());
		Assert.assertEquals("image5", saved.getImageURL());
	}
	
	@Test
	public void searchAll(){
		List<EventDTO> events = Lists.newArrayList();
		
		events = this.eventService.searchAll();
		
		Assert.assertEquals(3, events.size());
		Assert.assertEquals("event1", events.get(0).getName());
		Assert.assertEquals("event2", events.get(1).getName());
		Assert.assertEquals("event3", events.get(2).getName());
	}
}
