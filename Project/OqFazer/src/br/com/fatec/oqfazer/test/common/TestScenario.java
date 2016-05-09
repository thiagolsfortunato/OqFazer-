package br.com.fatec.oqfazer.test.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.Participation;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.fatec.oqfazer.api.service.CategoryService;
import br.com.fatec.oqfazer.api.service.EventService;
import br.com.fatec.oqfazer.api.service.RegionService;
import br.com.fatec.oqfazer.api.service.UserService;
import br.com.fatec.oqfazer.core.converter.CategoryDTOConverter;
import br.com.fatec.oqfazer.core.converter.EventDTOConverter;
import br.com.fatec.oqfazer.core.converter.RegionDTOConverter;
import br.com.fatec.oqfazer.core.converter.UserDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public abstract class TestScenario extends TestBase {
	
	protected CategoryDAO categoryDAO;
	protected CityDAO cityDAO;
	protected EventCategory eventCategory;
	protected EventDAO eventDAO;
	protected Participation participation;
	protected RegionDAO regionDAO;
	protected UserDAO userDAO;
	
	protected CategoryDTOConverter categoryDTOConverter;
	protected EventDTOConverter eventDTOConverter;
	protected RegionDTOConverter regionDTOConverter;
	protected UserDTOConverter userDTOConverter;
	
	protected CategoryService categoryService;
	protected EventService eventService;
	protected RegionService regionService;
	protected UserService userService;
	
	protected Map<Long, Category> categories = Maps.newHashMap();
	protected Map<Long, List<City>> cities = Maps.newHashMap();
	protected Map<Long, User> userParticipations = Maps.newHashMap();
	protected Map<Long, Category> categoryEvent = Maps.newHashMap();
	protected Map<Long, Event> events = Maps.newHashMap();
	protected Map<Long, Region> regions = Maps.newHashMap();
	protected Map<Long, User> users = Maps.newHashMap();

	protected Map<Long, CategoryDTO> categoriesDTO = Maps.newHashMap();
	protected Map<Long, EventDTO> eventsDTO = Maps.newHashMap();
	protected Map<Long, RegionDTO> regionsDTO = Maps.newHashMap();
	protected Map<Long, UserDTO> usersDTO = Maps.newHashMap();

	@Before
	public void upCenario() {
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
		this.cityDAO = ImplFinder.getImpl(CityDAO.class);
		this.eventCategory = ImplFinder.getImpl(EventCategory.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.participation = ImplFinder.getImpl(Participation.class);
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
		this.userDAO = ImplFinder.getImpl(UserDAO.class);
		
		this.categoryDTOConverter = ImplFinder.getFinalImpl(CategoryDTOConverter.class);
		this.eventDTOConverter = ImplFinder.getFinalImpl(EventDTOConverter.class);
		this.regionDTOConverter = ImplFinder.getFinalImpl(RegionDTOConverter.class);
		this.userDTOConverter = ImplFinder.getFinalImpl(UserDTOConverter.class);

		this.categoryService = ImplFinder.getImpl(CategoryService.class);
		this.eventService = ImplFinder.getImpl(EventService.class);
		this.regionService = ImplFinder.getImpl(RegionService.class);
		this.userService = ImplFinder.getImpl(UserService.class);
		
		this.buildCategory();
		this.buildRegion();
		this.buildCity();
		this.buildUser();
		this.buildEvent();		
	}

	@After
	public void downCenario() {
		this.categories = Maps.newHashMap();
		this.users = Maps.newHashMap();
		this.regions = Maps.newHashMap();
		this.cities = Maps.newHashMap();
		this.events = Maps.newHashMap();
	}
	
	private void buildCategory() {
		Category cat1 = new Category(null, "Category 1", null);
		Category cat2 = new Category(null, "Category 2", cat1.getId());
		Category cat3 = new Category(null, "Category 3", cat2.getId());
		
		Long cat1Id = this.categoryDAO.insertCategory(cat1);
		this.categories.put(cat1Id, this.categoryDAO.searchCategoryById(1l));
		Long cat2Id = this.categoryDAO.insertCategory(cat2);
		this.categories.put(cat2Id, this.categoryDAO.searchCategoryById(2l));
		Long cat3Id = this.categoryDAO.insertCategory(cat3);
		this.categories.put(cat3Id, this.categoryDAO.searchCategoryById(3l));
		
		this.categoriesDTO.put(1l, this.categoryDTOConverter.toDTOSimple(this.categories.get(1l)));
		this.categoriesDTO.put(2l, this.categoryDTOConverter.toDTOSimple(this.categories.get(2l)));
		this.categoriesDTO.put(3l, this.categoryDTOConverter.toDTOSimple(this.categories.get(3l)));
		
	}
	
	private void buildUser() {
		User u1 = new User(null, "user1", "user01", "user1@user", 1111);
		User u2 = new User(null, "user2", "user02", "user2@user", 2222);
		User u3 = new User(null, "user3", "user03", "user3@user", 3333);
		
		Long u1Id = this.userDAO.insertUser(u1);
		this.users.put(1l, this.userDAO.searchUserById(u1Id));
		Long u2Id = this.userDAO.insertUser(u2);
		this.users.put(2l, this.userDAO.searchUserById(u2Id));
		Long u3Id = this.userDAO.insertUser(u3);
		this.users.put(3l, this.userDAO.searchUserById(u3Id));
		
		this.usersDTO.put(1l, this.userDTOConverter.toDTOSimple(this.users.get(1l)));
		this.usersDTO.put(2l, this.userDTOConverter.toDTOSimple(this.users.get(2l)));	
		this.usersDTO.put(3l, this.userDTOConverter.toDTOSimple(this.users.get(3l)));	
	}

	private void buildRegion() {
		Region r1 = new Region(null, "Region1");
		Region r2 = new Region(null, "Region2");
		
		Long r1Id = this.regionDAO.insertRegion(r1);
		this.regions.put(1l,this.regionDAO.searchRegionById(r1Id));
		Long r2Id = this.regionDAO.insertRegion(r2);
		this.regions.put(2l,this.regionDAO.searchRegionById(r2Id));
		//verificar se aqui não é toDTO ao invés de toDTOSimple, porque region não tem dependência para outra DTO
		this.regionsDTO.put(1l, this.regionDTOConverter.toDTOSimple(this.regions.get(1l)));
		this.regionsDTO.put(2l, this.regionDTOConverter.toDTOSimple(this.regions.get(2l)));		
	}
	
	private void buildCity() {
		List<City> cities1 = Lists.newArrayList();
		cities1.add(City.SAO_JOSE_DOS_CAMPOS);
		cities1.add(City.JACAREI);
		cities1.add(City.CACAPAVA);
		
		List<City> cities2 = Lists.newArrayList();
		cities2.add(City.GUARULHOS);
		cities2.add(City.SAO_PAULO);
		cities2.add(City.ARUJA);
		
		Long c1Id = this.cityDAO.insertCity(this.regions.get(1l).getId(), cities1);
		this.cities.put(1l,this.cityDAO.searchCityByRegionId(c1Id));
		Long c2Id= this.cityDAO.insertCity(this.regions.get(2l).getId(), cities2);
		this.cities.put(2l,this.cityDAO.searchCityByRegionId(c2Id));
	}

	private void buildEvent() {
		Event event1 = new Event(null, "event1", "desc event1", new Date(), new Date(), "local evn1", "image1", this.regions.get(1l), this.users.get(1l));
		Event event2 = new Event(null, "event2", "desc event2", new Date(), new Date(), "local evn2", "image2", this.regions.get(2l), this.users.get(2l));
		Event event3 = new Event(null, "event3", "desc event3", new Date(), new Date(), "local evn3", "image2", this.regions.get(1l), this.users.get(3l));
		
		Long e1Id = this.eventDAO.inserEvent(event1);
		this.userParticipations.put(e1Id, this.userDAO.searchUserById(e1Id));
		this.userParticipations.put(e1Id, this.userDAO.searchUserById(e1Id));
		this.categoryEvent.put(e1Id, this.categoryDAO.searchCategoryById(e1Id));
		this.events.put(1l, this.eventDAO.searchEventById(e1Id));
		
		Long e2Id = this.eventDAO.inserEvent(event2);
		this.userParticipations.put(e2Id, this.userDAO.searchUserById(e2Id));
		this.userParticipations.put(e2Id, this.userDAO.searchUserById(e2Id));
		this.categoryEvent.put(e2Id, this.categoryDAO.searchCategoryById(e2Id));		
		this.events.put(2l, this.eventDAO.searchEventById(e2Id));
		
		
		Long e3Id = this.eventDAO.inserEvent(event3);
		this.userParticipations.put(e3Id, this.userDAO.searchUserById(e3Id));
		this.userParticipations.put(e3Id, this.userDAO.searchUserById(e3Id));
		this.categoryEvent.put(e3Id, this.categoryDAO.searchCategoryById(e3Id));	
		this.events.put(3l, this.eventDAO.searchEventById(e3Id));
		
		
		this.eventsDTO.put(1l, this.eventDTOConverter.toDTOSimple(this.events.get(e1Id)));
		this.eventsDTO.put(2l, this.eventDTOConverter.toDTOSimple(this.events.get(e2Id)));
		this.eventsDTO.put(3l, this.eventDTOConverter.toDTOSimple(this.events.get(e3Id)));
	}
	
	protected List<EventDTO> getEvents(Long... ids){
		List<EventDTO> events = Lists.newArrayList();
		for (Long id: ids){
			events.add(this.eventsDTO.get(id));
		}
		return events;
	}
	
	protected List<CategoryDTO> getCategories (Long... ids){
		List<CategoryDTO> categories = Lists.newArrayList();
		for (Long id: ids){
			categories.add(this.categoriesDTO.get(id));
		}
		return categories;
	}
	
	protected List<City> getCities (Long... ids){
		List<City> cities = Lists.newArrayList();
		for (Long id: ids){
			cities.addAll(this.cities.get(id));
		}
		return cities;
	}

	public List<UserDTO> getUsersDTO(Long...ids) {
		List<UserDTO> users = Lists.newArrayList();
		for (Long id: ids) {
			users.add(this.usersDTO.get(id));
		}
		return users;
	}
}

