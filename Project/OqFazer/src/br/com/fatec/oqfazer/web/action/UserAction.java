package br.com.fatec.oqfazer.web.action;

import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.service.UserService;
import br.com.fatec.oqfazer.web.context.ContextUser;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class UserAction extends OqFazerWebAction{

	/***/
	private static final long serialVersionUID = 8653106087989952885L;
	private static final String IT_WORKED = "worked";
	
	private ContextUser context = new ContextUser();
	private UserService service;
	
	public UserAction() {
		this.service = ImplFinder.getFinalImpl(UserService.class);
	}
	
	public String searchAll(){
		this.context.setUsersDTO(this.service.searchAll());
		return IT_WORKED;
	}
	
	public String insert(){
		if(this.context.getUserDTO().getId() != null){
			this.service.update(this.context.getUserDTO());
		}else{
			this.service.insert(this.context.getUserDTO());
		}
		return this.searchAll();
	}
	
	public String update(){
		UserDTO userDTO = this.service.searchById(this.context.getUserDTO().getId());
		this.context.setUserDTO(userDTO);
		return this.searchAll();
	}
	
	public String delete(){
		this.service.delete(this.context.getUserDTO().getId());
		return this.searchAll();
	}

	public ContextUser getContext() {
		return context;
	}

	public void setContext(ContextUser context) {
		this.context = context;
	}
}
