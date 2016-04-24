package br.com.fatec.oqfazer.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OqFazerWebAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	private static final long serialVersionUID = -822020793624550037L;
	
	protected HttpServletRequest servletRequest;
	protected HttpServletResponse servletResponse;
	
	
	protected Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

}
