package br.com.fatec.oqfazer.test.multidb;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

public class PostgresTest {
	
	@BeforeClass
	public static void setUp() {
		ContextSpecifier.setContext("br.com.fatec.oqfazer");
		ConfigDBMapper.setDefaultConnectionName("postgres");
		LiquibaseRunnerService.run();
	}

	@Test
	public void test() {
	}
}
