package br.com.fatec.oqfazer.web.action;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.api.service.CategoryService;
import br.com.fatec.oqfazer.web.context.ContextCategory;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class CategoryAction extends OqFazerWebAction {

	/** */
	private static final long serialVersionUID = 1071989853380980252L;
	private static final String IT_WORKED = "worked";
	
	private ContextCategory context = new ContextCategory();
	private CategoryService service;
	
	public CategoryAction() {
		this.service = ImplFinder.getFinalImpl(CategoryService.class);
	}
	
	public String searchAll(){
		this.context.setCategoriesDTO(this.service.searchAll());
		return IT_WORKED;
	}
	
	public String insert(){
		if(this.context.getCategoryDTO().getId() != null){
			this.service.update(this.context.getCategoryDTO());
		}else{
			this.service.insert(this.context.getCategoryDTO());
		}
		return this.searchAll();
	}
	
	public String update(){
		CategoryDTO categoryDTO = this.service.searchById(this.context.getCategoryDTO().getId());
		this.context.setCategoryDTO(categoryDTO);
		return this.searchAll();
	}
	
	public String delete(){
		this.service.delete(this.context.getCategoryDTO().getId());
		return this.searchAll();
	}

	public ContextCategory getContext() {
		return context;
	}

	public void setContext(ContextCategory context) {
		this.context = context;
	}
}
