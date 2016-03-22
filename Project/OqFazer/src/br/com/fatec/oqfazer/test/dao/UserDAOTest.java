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
		saveUser.setSex('M');
		saveUser.setPhone(36535959);
		
		Long id = this.dao.insertUser(saveUser);
		User savedUser = new User();
		
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(String.valueOf(id), savedUser.getId());
		Assert.assertEquals("William", savedUser.getName());
		Assert.assertEquals("williamcezart@gmail.com", savedUser.getEmail());
		Assert.assertEquals("080486", savedUser.getPassword());
		Assert.assertEquals('M', savedUser.getSex());
		Assert.assertEquals("988598345", savedUser.getPhone());
	}
	
	@Test
	public void testUpdate() {
		User user = new User();
		user.setName("william");
		user.setPassword("senha_william");

		Long id = this.dao.insertUser(user);
		User atualizeUser = this.dao.searchUserById(id);

		atualizeUser.setName("william penna");
		atualizeUser.setPassword("nova_senha");

		this.dao.updateUser(atualizeUser);;
		User atualizedUser = this.dao.searchUserById(id);

		Assert.assertNotNull(atualizedUser);
		Assert.assertEquals("william penna", atualizedUser.getName());
		Assert.assertEquals("nova_senha", atualizedUser.getPassword());
	}
	
	@Test
	public void testDelete() {
		User user = new User();
		user.setName("william");
		user.setPassword("senha_william");

		Long id = this.dao.insertUser(user);
		this.dao.deleteUser(id);;

		User deletedUser = this.dao.searchUserById(id);

		Assert.assertNull(deletedUser);
	}
	
	@Test
	public void testFindAll() {
		User user1 = new User();
		user1.setName("user 1");
		user1.setPassword("password_1");
		User user2 = new User();
		user2.setName("user 2");
		user2.setPassword("password_2");

		this.dao.insertUser(user1);
		this.dao.insertUser(user2);

		List<User> listUsers = this.dao.searchAllUsers();

		Assert.assertEquals(2, listUsers.size());
		Assert.assertEquals("user 1", listUsers.get(0).getName());
		Assert.assertEquals("password_1", listUsers.get(0).getPassword());
		Assert.assertEquals("user 2", listUsers.get(1).getName());
		Assert.assertEquals("password_2", listUsers.get(1).getPassword());
	}
		
}
