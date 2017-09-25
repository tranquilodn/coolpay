package com.currencycloud.coolpay.filters;

import java.io.IOException;

import javax.faces.application.ViewExpiredException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * This class is used to filter the requisitions in order to redirect to the
 * <i>login</i>.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 */
@WebFilter(urlPatterns = { "/pages/*" })
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	/**
	 * Initialiser of the filter.
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * Method executed when we destroy the object.
	 */
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		HttpSession httpSession = null;
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		String token = null;
		try {
			httpSession = ((HttpServletRequest) request).getSession();
			httpServletRequest = (HttpServletRequest) request;
			httpServletResponse = (HttpServletResponse) response;
			token = (String) httpSession.getAttribute(JsfUtils.SESSION_TOKEN);
			if (token == null) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.faces");
			} else {
				chain.doFilter(request, response);
			}
		} catch (IOException ioe) {
			Throwable t = ioe.getCause();
			t.printStackTrace();
		} catch (ServletException se) {
			Throwable t = se.getCause();
			t.printStackTrace();
		} catch (ViewExpiredException vee) {
			try {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.faces");
			} catch (IOException ioe) {
				Throwable t = ioe.getCause();
				t.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}