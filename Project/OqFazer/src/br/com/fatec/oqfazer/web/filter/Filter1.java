package br.com.fatec.oqfazer.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter1 implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		resp.getWriter().println("Filtro 1 - IDA");
		chain.doFilter(req, resp);
		resp.getWriter().println("Filtro 1 - VOTA");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
