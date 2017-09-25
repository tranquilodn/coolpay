package com.currencycloud.coolpay.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.repo.ILoginRepo;
import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * 
 * Managed bean which controls the authentication process, and also provides
 * information to control the user interface.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @since 1.5
 * @see AbstractMB
 */
@RequestScoped
@Named(value = "loginMB")
public class LoginMB extends AbstractMB<String, Integer> implements Serializable {

	/**
	 * Serial version UID of the class.
	 */
	private static final long serialVersionUID = -5250263596023896406L;

	/**
	 * Injection of the repository which controls the login transacions.
	 */
	@Inject
	private ILoginRepo repo;

	/**
	 * Username variable, to control the login.
	 */
	private String username = null;

	/**
	 * <b>APIKEY</b> variable, which works as a password to authenticate, which is
	 * the process to obtain the <i>token</i>
	 */
	private String apikey = null;

	/**
	 * Default constructor of the class.
	 */
	public LoginMB() {
	}

	/**
	 * Initializer of the managed bean.
	 */
	@Override
	@PostConstruct
	public void init() {
		super.init();
	}

	/**
	 * "getter" for the username
	 * 
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * "setter" for the username
	 * 
	 * @param username
	 *            Expects the username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * "getter" for the apikey
	 * 
	 * @return String
	 */
	public String getApikey() {
		return apikey;
	}

	/**
	 * setter for the apikey
	 * 
	 * @param apikey
	 *            Expects the password (apikey)
	 */
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	/**
	 * This method is used to authenticate the user.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void login(ActionEvent evt) {
		String token = null;
		if (username == null || apikey == null) {
			JsfUtils.addMessageInfo(JsfUtils.getMessage("login") + ":", JsfUtils.getMessage("login.insertUsernameOrPassword"));
			return;
		}
		try {
			token = repo.login(username, apikey);
			if (token != null) {
				JsfUtils.setSessionToken(token);
				JsfUtils.pageRedirect(evt);
			} else {
				JsfUtils.addMessageInfo(JsfUtils.getMessage("login.fail") + ":",
						JsfUtils.getMessage("login.invalidUsernameOrPassword"));
			}
		} catch (BadRequestException e) {
			e.printStackTrace();
			JsfUtils.addMessageError(JsfUtils.getMessage("login.fail") + ":", e.getMessage());
			return;
		} catch (UnauthorisedException e) {
			e.printStackTrace();
			JsfUtils.addMessageError(JsfUtils.getMessage("login.fail") + ":", e.getMessage());
			return;
		} catch (GenericException e) {
			e.printStackTrace();
			JsfUtils.addMessageError(JsfUtils.getMessage("login.fail") + ":", e.getMessage());
			return;
		}
	}

}