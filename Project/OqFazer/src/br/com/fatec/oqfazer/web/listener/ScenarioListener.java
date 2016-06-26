package br.com.fatec.oqfazer.web.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.ParticipationDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ScenarioListener implements ServletContextListener {

	private Category cat1, cat2, cat3;
	private List<City> citiesRio, citiesSaoPaulo;
	private Event event1, event2, event3;
	private Region region1, region2;
	private User u1,u2,u3;
	
	private CategoryDAO categoryDAO;
	private CityDAO cityDAO;
	private EventDAO eventDAO;
	private RegionDAO regionDAO;
	private UserDAO userDAO;
	private EventCategory eventCategory;
	private ParticipationDAO participation;
	private User admin;
	
		
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener Scenario - destroy");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {				
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
		this.cityDAO = ImplFinder.getImpl(CityDAO.class);
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
		this.userDAO = ImplFinder.getImpl(UserDAO.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.eventCategory = ImplFinder.getImpl(EventCategory.class);
		this.participation = ImplFinder.getImpl(ParticipationDAO.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
				
		this.insertCategory();
		this.inserRegion();
		this.inserUser();
		this.insertEvent();
	}
	
	private void insertCategory(){
		this.cat1 = new Category((long)1, "Category 1", null);
		this.cat2 = new Category((long)2, "Category 2", cat1.getId());
		this.cat3 = new Category((long)3, "Category 3", cat2.getId());
		
		this.categoryDAO.insertCategory(cat1);
		this.categoryDAO.insertCategory(cat2);
		this.categoryDAO.insertCategory(cat3);
		System.out.println("insert category - OK");
	}
	
	private void inserRegion(){
		this.region1 = new Region((long)1, "Region1");
		this.region2 = new Region((long)2, "Region2");
		
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
		this.admin = new User((long)1, "admin", "admin", "admin@user", 1111);
		this.u1 = new User((long)2, "user1", "user01", "user1@user", 1111);
		this.u2 = new User((long)3, "user2", "user02", "user2@user", 2222);
		this.u3 = new User((long)4, "user3", "user03", "user3@user", 3333);
		
		this.userDAO.insertUser(admin);
		this.userDAO.insertUser(u1);
		this.userDAO.insertUser(u2);
		this.userDAO.insertUser(u3);
		
		System.out.println("insert user - OK");
	}
	

	private void insertEvent(){
		this.event1 = new Event();
		
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2016);
		c1.set(Calendar.MONTH, Calendar.OCTOBER);
		c1.set(Calendar.DAY_OF_MONTH, 10);
		Date d1 = c1.getTime();
		
		event1.setId((long) 1);
		event1.setName("Moto Rock");
		event1.setDescription("Shows de Rock");
		event1.setRegistration_date(new Date());
		event1.setEvent_date(d1);
		event1.setLocal("Rua José");
		event1.setImageURL("https://s5.postimg.org/90yx9m577/show1.jpg");
		event1.setRegion(region1);
		event1.setOwner(u1);
		
		this.event2 = new Event();
		
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2016);
		c2.set(Calendar.MONTH, Calendar.APRIL);
		c2.set(Calendar.DAY_OF_MONTH, 21);
		Date d2 = c2.getTime();		
		
		event2.setId((long) 2);
		event2.setName("Festa de Barretos");
		event2.setDescription("Shows de Sertanejo");
		event2.setRegistration_date(new Date());
		event2.setEvent_date(d2);
		event2.setLocal("Rua joão");
		event2.setImageURL("https://s5.postimg.org/eqf5txbdj/show2.jpg");
		event2.setRegion(region2);
		event2.setOwner(u2);
		
		this.event3 = new Event();
		
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.YEAR, 2016);
		c3.set(Calendar.MONTH, Calendar.MARCH);
		c3.set(Calendar.DAY_OF_MONTH, 18);
		Date d3 = c3.getTime();		
		
		event3.setId((long) 3);
		event3.setName("Confraternização");
		event3.setDescription("Amigos");
		event3.setRegistration_date(new Date());
		event3.setEvent_date(d3);
		event3.setLocal("Casa do Amigo");
		event3.setImageURL("https://s5.postimg.org/iljxim9cn/conf1.jpg");
		event3.setRegion(region2);
		event3.setOwner(u3);
					
		long evn1 = this.eventDAO.inserEvent(event1);
		long evn2 = this.eventDAO.inserEvent(event2);
		long evn3 = this.eventDAO.inserEvent(event3);
		
		this.participation.insertParticipation(evn1, u2.getId());
		this.participation.insertParticipation(evn1, u3.getId());
		
		this.participation.insertParticipation(evn2, u1.getId());
		this.participation.insertParticipation(evn2, u3.getId());
		
		this.participation.insertParticipation(evn3, u1.getId());
		this.participation.insertParticipation(evn3, u2.getId());
				
		this.eventCategory.insertEventCategory(evn1, cat1.getId());
		this.eventCategory.insertEventCategory(evn2, cat2.getId());
		this.eventCategory.insertEventCategory(evn3, cat3.getId());
		
		System.out.println("insert event - OK");
	}
}

