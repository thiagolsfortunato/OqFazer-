package br.com.fatec.oqfazer.web.action;

import java.util.Date;

import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.service.UserService;
import br.com.fatec.oqfazer.web.context.ContextLogin;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class LoginAction extends OqFazerWebAction {

	/** */
	private static final long serialVersionUID = 7059748509020357437L;
	private static final String IT_WORKED = "worked";

	private UserService service;
	private ContextLogin context = new ContextLogin();

	public LoginAction() {
		this.service = ImplFinder.getImpl(UserService.class);
	}

	public String login() {
		UserDTO user = this.context.getUser();
		UserDTO userFound = this.service.searchUserByEmailAndPassword(user.getEmail(), user.getPassword());

		if (userFound != null) {
			userFound.setStartSession(new Date().getTime());
			if(userFound.getName().equals("admin")) userFound.setIsAdmin(true);
			this.getSession().put("usuario", userFound);
		}
		this.context.setUser(userFound);
		return IT_WORKED;
	}

	public String logout() {
		this.context.setUser(null);
		this.getSession().remove("user");
		return IT_WORKED;
	}

	public ContextLogin getContext() {
		return this.context;
	}

	public void setContext(ContextLogin context) {
		this.context = context;
	}
}
