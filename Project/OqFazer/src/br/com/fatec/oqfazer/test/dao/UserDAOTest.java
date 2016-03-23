package br.com.fatec.oqfazer.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class UserDAOTest extends TestBase{

	private UserDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(UserDAO.class);
	}
	
	@Test
	public void testSave(){
		User saveUser = new User();
		saveUser.setId((long)1);
		saveUser.setName("william");
		saveUser.setEmail("williamcezart@gmail.com");
		saveUser.setPassword("facebook");
		saveUser.setPhone(36535959);
		
		Long id = this.dao.insertUser(saveUser);
		User savedUser = this.dao.searchUserById(id);
		
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(String.valueOf(1), savedUser.getId());
		Assert.assertEquals("William", savedUser.getName());
		Assert.assertEquals("williamcezart@gmail.com", savedUser.getEmail());
		Assert.assertEquals("facebook", savedUser.getPassword());
		Assert.assertEquals(36535959, savedUser.getPhone());
	}
	
	@Test
	public void testUpdate() {
		User user = new User();
		user.setId((long)1);
		user.setName("william");
		user.setEmail("williamcezart@gmail.com");
		user.setPassword("facebook");
		user.setPhone(36535959);
		
		Long id = this.dao.insertUser(user);
		User atualizeUser = this.dao.searchUserById(id);

		atualizeUser.setName("william penna");
		atualizeUser.setPassword("nova_senha");

		this.dao.updateUser(atualizeUser);;
		User atualizedUser = this.dao.searchUserById(id);

		Assert.assertNotNull(atualizedUser);
		Assert.assertEquals(String.valueOf(1), atualizedUser.getId());
		Assert.assertEquals("william penna", atualizedUser.getName());
		Assert.assertEquals("williamcezart@gmail.com", atualizedUser.getEmail());
		Assert.assertEquals("nova_senha", atualizedUser.getPassword());
		Assert.assertEquals(36535959, atualizedUser.getPhone());
	}
	
	@Test
	public void testDelete() {
		User user = new User();
		user.setId((long)1);
		user.setName("william");
		user.setEmail("williamcezart@gmail.com");
		user.setPassword("facebook");
		user.setPhone(36535959);

		Long id = this.dao.insertUser(user);
		this.dao.deleteUser(id);;

		User deletedUser = this.dao.searchUserById(id);

		Assert.assertNull(deletedUser);
	}
	
	@Test
	public void testFindAll() {
		User user1 = new User();
		user1.setId((long)1);
		user1.setName("william");
		user1.setEmail("williamcezart@gmail.com");
		user1.setPassword("facebook");
		user1.setPhone(36535959);
		
		User user2 = new User();
		user2.setId((long)1);
		user2.setName("thiago");
		user2.setEmail("thiago@test");
		user2.setPassword("teste");
		user2.setPhone(111111);

		this.dao.insertUser(user1);
		this.dao.insertUser(user2);

		List<User> listUsers = this.dao.searchAllUsers();

		Assert.assertEquals(2, listUsers.size());
		Assert.assertEquals(String.valueOf(1), listUsers.get(0).getId());
		Assert.assertEquals("william penna", listUsers.get(0).getName());
		Assert.assertEquals("williamcezart@gmail.com", listUsers.get(0).getEmail());
		Assert.assertEquals("nova_senha", listUsers.get(0).getPassword());
		Assert.assertEquals(36535959, listUsers.get(0).getPhone());
		
		Assert.assertEquals(String.valueOf(2), listUsers.get(1).getId());
		Assert.assertEquals("thiago", listUsers.get(1).getName());
		Assert.assertEquals("thiago@test", listUsers.get(1).getEmail());
		Assert.assertEquals("test", listUsers.get(1).getPassword());
		Assert.assertEquals(111111, listUsers.get(1).getPhone());
	}
		
}
