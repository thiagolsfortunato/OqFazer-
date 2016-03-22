package br.com.fatec.oqfazer.test.common;

import org.junit.After;
import org.junit.BeforeClass;
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
		QueryExecutorService.executeQuery("DELETE FROM ");
		QueryExecutorService.executeQuery("DELETE FROM ");
		QueryExecutorService.executeQuery("DELETE FROM ");
		QueryExecutorService.executeQuery("DELETE FROM ");
		QueryExecutorService.executeQuery("DELETE FROM ");

		QueryExecutorService
				.executeQuery("ALTER SEQUENCE SEQ_PROJETO_PAPEL RESTART WITH 1");
		QueryExecutorService
				.executeQuery("ALTER SEQUENCE SEQ_PROJETO_GRUPO_PAPEL RESTART WITH 1");
		QueryExecutorService
				.executeQuery("ALTER SEQUENCE SEQ_PROJETO_USUARIO RESTART WITH 1");
		;
	}
	
}
