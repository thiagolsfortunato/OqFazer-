package br.com.fatec.oqfazer.test.common;

import org.junit.After;
import org.junit.BeforeClass;

import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;
import br.com.spektro.minispring.core.query.QueryExecutorService;


public class TestBase {
	
	@BeforeClass
	public static void setUp() {
		ContextSpecifier.setContext("br.com.fatec.oqfazer");
		ConfigDBMapper.setDefaultConnectionName("test");
		LiquibaseRunnerService.run();
	}
	
	@After
	public void setDown() {
		QueryExecutorService.executeQuery("DELETE FROM "+Category.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM "+City.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM "+Event.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM "+Region.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM "+User.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM PARTICIPATION");
		QueryExecutorService.executeQuery("DELETE FROM EVENT_CATEGORY");

		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_CATEGORY RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_REGION RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_EVENT RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_USER RESTART WITH 1");
	}
	
}
