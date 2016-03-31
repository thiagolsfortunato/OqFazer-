package br.com.fatec.oqfazer.test.multidb;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.fatec.oqfazer.test.dao.UserDAOTest;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

public class MySQLTest {

	@BeforeClass
	public static void setUp() {
		ContextSpecifier.setContext("br.com.fatec.oqfazer");
		ConfigDBMapper.setDefaultConnectionName("mysql");
		LiquibaseRunnerService.run();
	}

	@Test
	public void teste() {
		new UserDAOTest().testFindAll();;
	}
}
