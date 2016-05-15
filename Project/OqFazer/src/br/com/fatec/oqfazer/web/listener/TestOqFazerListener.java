package br.com.fatec.oqfazer.web.listener;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;


public class TestOqFazerListener implements ServletContextListener {

	private Category cat1, cat2, cat3;
	private List<City> citiesRio, citiesSaoPaulo;
	private Event event1, event2;
	private Region region1, region2;
	private User u1,u2,u3;
	
	private CategoryDAO categoryDAO;
	private CityDAO cityDAO;
	private EventDAO eventDAO;
	private RegionDAO regionDAO;
	private UserDAO userDAO;
	
	public TestOqFazerListener() {
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
		this.cityDAO = ImplFinder.getImpl(CityDAO.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
		this.userDAO = ImplFinder.getImpl(UserDAO.class);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener OqFazer - destroy");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		this.insertCategory();
		this.inserRegion();
		this.inserUser();
		this.insertEvent();
		System.out.println("Listener OqFazer - Init");		
		ContextSpecifier.setContext("br.com.fatec.oqfazer");
		ConfigDBMapper.setDefaultConnectionName("test");
		LiquibaseRunnerService.run();
	}
	
	private void insertCategory(){
		this.cat1 = new Category(null, "Category 1", null);
		this.cat2 = new Category(null, "Category 2", cat1.getId());
		this.cat3 = new Category(null, "Category 3", cat2.getId());
		
		this.categoryDAO.insertCategory(cat1);
		this.categoryDAO.insertCategory(cat2);
		this.categoryDAO.insertCategory(cat3);
		System.out.println("insert category - OK");
	}
	
	private void inserRegion(){
		this.region1 = new Region(null, "Region1");
		this.region2 = new Region(null, "Region2");
		
		Long r1Id = this.regionDAO.insertRegion(region1);
		Long r2Id = this.regionDAO.insertRegion(region2);
		
		this.citiesRio = Lists.newArrayList();
		citiesRio.add(City.SAO_JOSE_DOS_CAMPOS);
		citiesRio.add(City.JACAREI);
		citiesRio.add(City.CACAPAVA);
		
		this.citiesSaoPaulo = Lists.newArrayList();
		citiesSaoPaulo.add(City.GUARULHOS);
		citiesSaoPaulo.add(City.SAO_PAULO);
		citiesSaoPaulo.add(City.ARUJA);
		
		this.cityDAO.insertCity(r1Id, citiesRio);		
		this.cityDAO.insertCity(r2Id, citiesSaoPaulo);
		
		System.out.println("insert region + city - OK");
	}
	
	private void inserUser(){
		this.u1 = new User(null, "user1", "user01", "user1@user", 1111);
		this.u2 = new User(null, "user2", "user02", "user2@user", 2222);
		this.u3 = new User(null, "user3", "user03", "user3@user", 3333);
		
		this.userDAO.insertUser(u1);
		this.userDAO.insertUser(u2);
		this.userDAO.insertUser(u3);
		
		System.out.println("insert user - OK");
	}
	
	private void insertEvent(){
		this.event1 = new Event();
		event1.setId((long) 1);
		event1.setName("Show da Banda");
		event1.setDescription("Show da banda fulana");
		event1.setRegistration_date(new Date());
		event1.setEvent_date(new Date());
		event1.setLocal("Rua Jos�");
		event1.setImageURL(null);
		event1.setRegion(region1);
		event1.setOwner(u1);
		
		this.event2 = new Event();
		event2.setId((long) 1);
		event2.setName("Show da Banda2");
		event2.setDescription("Show da banda fulana2");
		event2.setRegistration_date(new Date());
		event2.setEvent_date(new Date());
		event2.setLocal("Rua jo�o");
		event2.setImageURL(null);
		event2.setRegion(region2);
		event2.setOwner(u2);
					
		this.eventDAO.inserEvent(event1);
		this.eventDAO.inserEvent(event2);
		
		System.out.println("insert event - OK");
	}
}
