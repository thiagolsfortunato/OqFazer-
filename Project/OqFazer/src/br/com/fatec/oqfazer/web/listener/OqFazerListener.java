package br.com.fatec.oqfazer.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

public class OqFazerListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ContextSpecifier.setContext("br.com.fatec.oqfazer");
		ConfigDBMapper.setDefaultConnectionName("test");
		LiquibaseRunnerService.run();
		
	}
	
	
}
