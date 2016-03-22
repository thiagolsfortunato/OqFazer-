package br.com.fatec.oqfazer.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.Participation;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.City;

import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class EventDAOTest extends TestBase {
	
	private EventDAO eventDAO;
	private UserDAO userDAO;
	private CategoryDAO categoryDAO;
	private RegionDAO regionDAO;
	private Participation participation;
	private EventCategory eventCategory;
	
	private User user, user2, user3;
	private Category category, category2;
	private Region region, region2;
	
	@Before
	public void config() {
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.userDAO = ImplFinder.getImpl(UserDAO.class);
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
		this.regionDAO = ImplFinder.getFinalImpl(RegionDAO.class);
		
		//USERS
		User user = new User();
		user.setId((long) 1);
		user.setName("User 1");
		user.setEmail("user1@test");
		user.setPassword("user1");
		user.setPhone(11111);
		
		User user2 = new User();
		user2.setId((long) 2);
		user2.setName("User 2");
		user2.setEmail("user2@test");
		user2.setPassword("user2");
		user2.setPhone(22222);
		
		User user3 = new User();
		user3.setId((long) 3);
		user3.setName("User 3");
		user3.setEmail("user3@test");
		user3.setPassword("user3");
		user3.setPhone(3333);
		
		//CATEGORIES
		Category category1 = new Category();
		category1.setId((long) 1);
		category1.setName("Show");
		category1.setCategory(null);
		
		Category category2 = new Category();
		category1.setId((long) 1);
		category2.setName("Show Rock");
		category2.setCategory(category1);
		
		//LIST DE CITIES AND REGIONS
		List<City> cities = null;
		cities.add(City.CACAPAVA);
		cities.add(City.TAUBATE);		
		
		Region region1 = new Region();
		region1.setId((long) 1);
		region1.setName("Sentido Rio");
		region1.setCity(cities);
		
		List<City> cities2 = null;
		cities2.add(City.SAO_JOSE_DOS_CAMPOS);
		cities2.add(City.JACAREI);
		
		Region region2 = new Region();
		region1.setId((long) 1);
		region1.setName("Sentido Sao Paulo");
		region1.setCity(cities2);
		
		//INSERTS
		Long idUser = this.userDAO.insertUser(user);
		Long idUser2 = this.userDAO.insertUser(user2);
		Long idUser3 = this.userDAO.insertUser(user3);
		Long idCategory = this.categoryDAO.insertCategory(category1);
		Long idCategory2 = this.categoryDAO.insertCategory(category2);
		Long idRegion = this.regionDAO.insertRegion(region1);
		Long idRegion2 = this.regionDAO.insertRegion(region2);
		
				
		this.user = this.userDAO.searchUserById(idUser);
		this.user2 = this.userDAO.searchUserById(idUser2);
		this.user3 = this.userDAO.searchUserById(idUser3);
		this.category = this.categoryDAO.searchCategoryById(idCategory);
		this.category2 = this.categoryDAO.searchCategoryById(idCategory2);
		this.region = this.regionDAO.searchRegionById(idRegion);
		this.region2 = this.regionDAO.searchRegionById(idRegion2);
	}
	
	@Test
	public void testSave() {
		Date date = new Date();
		Date dateEvent = new Date();
		
		dateEvent.setDate(01);
		dateEvent.setMonth(01);
		dateEvent.setYear(2016);
		
		Event event = new Event();
		event.setId((long) 1);
		event.setName("Show da Banda");
		event.setDescription("Show da banda fulana");
		event.setRegistration_date(date);
		event.setEvent_date(dateEvent);
		event.setLocal("Rua josé");
		event.setImageURL(null);
		event.setRegion(region);
		event.setOwner(user);
		//event.setCategory(category);
					
		Long id = this.eventDAO.inserEvent(event);
		Event savedEvent = this.eventDAO.searchEventById(id);
		
		List<User> users = null;
		users.add(user2);
		users.add(user3);
		this.participation.updateListParticipation(id, users);
		this.eventCategory.updateCategory(id, category);
		
		Assert.assertNotNull(savedEvent);
		Assert.assertEquals(String.valueOf(1), savedEvent.getId());
		Assert.assertEquals("Show da Banda", savedEvent.getName());
		Assert.assertEquals("Show da banda fulana", savedEvent.getDescription());
		Assert.assertEquals(new Date(), savedEvent.getRegistration_date());
		Assert.assertEquals(new Date(01,01,2016), savedEvent.getEvent_date());
		Assert.assertEquals("Rua José", savedEvent.getLocal());
		Assert.assertEquals(null, savedEvent.getImageURL());
		Assert.assertEquals(this.region, savedEvent.getRegion());
		Assert.assertEquals(this.user, savedEvent.getOwner());
		//Assert.assertEquals(this.category, savedEvent.getCategory());
	}
	
	@Test
	public void testUpdate() {
		Date date = new Date();
		Date dateEvent = new Date();
		
		dateEvent.setDate(01);
		dateEvent.setMonth(01);
		dateEvent.setYear(2016);
		
		Event event = new Event();
		event.setId((long) 1);
		event.setName("Show da Banda");
		event.setDescription("Show da banda fulana");
		event.setRegistration_date(date);
		event.setEvent_date(dateEvent);
		event.setLocal("Rua josé");
		event.setImageURL(null);
		event.setRegion(region);
		event.setOwner(user);
		//event.setCategory(category);
		
		Long id = this.eventDAO.inserEvent(event);
		Event updateEvent = this.eventDAO.searchEventById(id);
		
		List<User> users = null;
		users.add(user);
		users.add(user3);
		this.participation.updateListParticipation(id, users);
		
		updateEvent.setName("Show da Banda de Musica");
		updateEvent.setDescription("Show da banda fulana de musica");
		updateEvent.setRegistration_date(date);
		updateEvent.setEvent_date(new Date(02, 02, 2017));
		updateEvent.setLocal("Rua joao");
		updateEvent.setImageURL(null);
		updateEvent.setRegion(region2);
		updateEvent.setOwner(user2);
		//updateEvent.setCategory(category2);
		
		this.eventDAO.updateEvent(updateEvent);
		
		Assert.assertNotNull(updateEvent);
		Assert.assertEquals(String.valueOf(1), updateEvent.getId());
		Assert.assertEquals("Show da Banda de musica", updateEvent.getName());
		Assert.assertEquals("Show da banda fulana de musica", updateEvent.getDescription());
		Assert.assertEquals(new Date(), updateEvent.getRegistration_date());
		Assert.assertEquals(new Date(02,02,2017), updateEvent.getEvent_date());
		Assert.assertEquals("Rua joao", updateEvent.getLocal());
		Assert.assertEquals(null, updateEvent.getImageURL());
		Assert.assertEquals(this.region2, updateEvent.getRegion());
		Assert.assertEquals(this.user2, updateEvent.getOwner());
		//Assert.assertEquals(this.category2, updateEvent.getCategory());		
	}
	
	@Test
	public void testDelete() {
		Date date = new Date();
		Date dateEvent = new Date();
		
		dateEvent.setDate(01);
		dateEvent.setMonth(01);
		dateEvent.setYear(2016);
		
		Event event = new Event();
		event.setId((long) 1);
		event.setName("Show da Banda");
		event.setDescription("Show da banda fulana");
		event.setRegistration_date(date);
		event.setEvent_date(dateEvent);
		event.setLocal("Rua josé");
		event.setImageURL(null);
		event.setRegion(region);
		event.setOwner(user);
		//event.setCategory(category);
		
		Long id = this.eventDAO.inserEvent(event);
		Event deletedEvent = this.eventDAO.searchEventById(id);
		
		List<User> users = null;
		users.add(user2);
		users.add(user3);
		this.participation.updateListParticipation(id, users);	
		
		this.eventDAO.deleteEvent(id);
		
		Assert.assertNull(deletedEvent);
	}
	
	@Test
	public void testsearchAll() {
		Date date = new Date();
		Date dateEvent = new Date();
		
		dateEvent.setDate(01);
		dateEvent.setMonth(01);
		dateEvent.setYear(2016);
		
		Event event = new Event();
		event.setId((long) 1);
		event.setName("Show da Banda");
		event.setDescription("Show da banda fulana");
		event.setRegistration_date(date);
		event.setEvent_date(dateEvent);
		event.setLocal("Rua josé");
		event.setImageURL(null);
		event.setRegion(region);
		event.setOwner(user);
		//event.setCategory(category);
		
		Long idEvent = this.eventDAO.inserEvent(event);
		
		List<User> users = null;
		users.add(user2);
		users.add(user3);
		this.participation.updateListParticipation(idEvent, users);
		
		Event event2 = new Event();
		event.setId((long) 1);
		event.setName("Show da Banda2");
		event.setDescription("Show da banda fulana2");
		event.setRegistration_date(dateEvent);
		event.setEvent_date(date);
		event.setLocal("Rua joão");
		event.setImageURL(null);
		event.setRegion(region2);
		event.setOwner(user2);
		//event.setCategory(category2);
		
		Long idEvent2 = this.eventDAO.inserEvent(event);
		
		List<User> users2 = null;
		users2.add(user);
		users2.add(user3);
		this.participation.updateListParticipation(idEvent2, users2);
		
		List<Event> listEvent = this.eventDAO.searchAllEvents();
		
		Assert.assertEquals(2, listEvent.size());
		Assert.assertEquals(String.valueOf(1), listEvent.get(0).getId());
		Assert.assertEquals("Show da Banda", listEvent.get(0).getName());
		Assert.assertEquals("Show da banda fulana", listEvent.get(0).getDescription());
		Assert.assertEquals(date, listEvent.get(0).getRegistration_date());
		Assert.assertEquals(dateEvent, listEvent.get(0).getEvent_date());
		Assert.assertEquals("Rua josé", listEvent.get(0).getLocal());
		Assert.assertEquals(null, listEvent.get(0).getImageURL());
		Assert.assertEquals(this.region, listEvent.get(0).getRegion());
		Assert.assertEquals(this.user, listEvent.get(0).getOwner());
		//Assert.assertEquals(this.category, listEvent.get(0).getCategory());
		
		Assert.assertEquals(2, listEvent.size());
		Assert.assertEquals(String.valueOf(1), listEvent.get(1).getId());
		Assert.assertEquals("Show da Banda2", listEvent.get(1).getName());
		Assert.assertEquals("Show da banda fulana2", listEvent.get(1).getDescription());
		Assert.assertEquals(dateEvent, listEvent.get(1).getRegistration_date());
		Assert.assertEquals(date, listEvent.get(1).getEvent_date());
		Assert.assertEquals("Rua joão", listEvent.get(1).getLocal());
		Assert.assertEquals(null, listEvent.get(1).getImageURL());
		Assert.assertEquals(this.region2, listEvent.get(1).getRegion());
		Assert.assertEquals(this.user2, listEvent.get(1).getOwner());
		//Assert.assertEquals(this.category2, listEvent.get(1).getCategory());
	}
	
}
