package com.currencycloud.coolpay.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 * This class is an static utility class made in order to carry on some common
 * activities such as navigation, session variables and also messages to the
 * user.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 */
public class JsfUtils {

	/**
	 * Constant which represent the content of the "token" which is used to verify
	 * whether the user is authenticated on the system.
	 */
	public static final String SESSION_TOKEN = "token";

	/**
	 * This is a method which proceed the page redirection. the managed bean
	 * receives the parameter containing the addressed page, and redirects to the
	 * referred page.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public static void pageRedirect(ActionEvent evt) {
		String pageRedirect = (String) evt.getComponent().getAttributes().get("pageRedirect");
		try {
			redirect(pageRedirect);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method provides an interface to return the token whith is stored as a
	 * session variable.
	 * 
	 * @return String
	 */
	public static String getSessionToken() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		return (String) session.getAttribute(JsfUtils.SESSION_TOKEN);
	}

	/**
	 * This method provides an interface to store the token variable on the session
	 * variables.
	 * 
	 * @param token
	 *            Expects the token which is the authentication result.
	 */
	public static void setSessionToken(String token) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute(JsfUtils.SESSION_TOKEN, token);
	}

	/**
	 * This method is used on the logout method in order to clear all the session
	 * variables and invalidate the session.
	 */
	public static void clearSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
	}

	/**
	 * This is an auxiliary method to perform the page redirection.
	 * 
	 * @param pageRedirect
	 *            Expects the page which will the system be redirected to.
	 * @throws IOException
	 *             This exception happens when the informed page doesn't exists.
	 */
	public static void redirect(String pageRedirect) throws IOException {
		String redirect = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
				+ pageRedirect;
		FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
	}

	/**
	 * This method is used to send a message to the user interface.
	 * 
	 * @param summary
	 *            Expects the title of the message.
	 * @param message
	 *            Expects the message itself.
	 */
	public static void addMessageInfo(String summary, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, message));
	}

	/**
	 * This method is used to send an error message to the user interface.
	 * 
	 * @param summary
	 *            Expects the title of the message.
	 * @param message
	 *            Expects the message itself.
	 */
	public static void addMessageError(String summary, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, message));
	}

	/**
	 * Dynamic allocation of the message.
	 * 
	 * @param key
	 *            Expects the key in order to return the message in its own
	 *            language, according to the <i>Locale</i>
	 * @return String
	 */
	public static String getMessage(String key) {
		String result = null;
		try {
			Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
			result = bundle.getString(key);
		} catch (MissingResourceException e) {
			result = key;
		}
		return result;
	}

}