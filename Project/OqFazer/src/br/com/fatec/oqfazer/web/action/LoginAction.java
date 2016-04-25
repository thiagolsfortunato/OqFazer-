package br.com.fatec.oqfazer.web.action;

import java.util.Date;

import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.service.UserService;
import br.com.fatec.oqfazer.web.context.ContextLogin;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class LoginAction extends OqFazerWebAction {

	/** */
	private static final long serialVersionUID = 7059748509020357437L;
	private static final String DEU_CERTO = "foi";

	private UserService service;
	private ContextLogin contexto = new ContextLogin();

	public LoginAction() {
		this.service = ImplFinder.getImpl(UserService.class);
	}

	public String login() {
		UserDTO user = this.contexto.getUserDTO();
		UserDTO userFound = this.service.searchUserByEmailAndPassword(user.getEmail(), user.getPassword());

		if (userFound != null) {
			userFound.setStartSession(new Date().getTime());
			this.getSession().put("usuario", userFound);
		}
		this.contexto.setUserDTO(userFound);
		return DEU_CERTO;
	}

	public String logout() {
		this.contexto.setUserDTO(null);
		this.getSession().remove("user");
		return DEU_CERTO;
	}

	public ContextLogin getContexto() {
		return this.contexto;
	}

	public void setContexto(ContextLogin contexto) {
		this.contexto = contexto;
	}

}
