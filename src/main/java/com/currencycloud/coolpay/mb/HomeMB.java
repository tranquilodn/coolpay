package com.currencycloud.coolpay.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import com.currencycloud.coolpay.qualifiers.LoggedIn;
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
@SessionScoped
@Named(value = "homeMB")
public class HomeMB extends AbstractMB<String, Integer> implements Serializable {

	/**
	 * Serial version UID of the class.
	 */
	private static final long serialVersionUID = 8659857427868718403L;

	/**
	 * <b>TOKEN</b> variable, which works as a password to proceed the authorisation
	 * process for further requisitions to the server.
	 */
	private String token = null;

	/**
	 * Default constructor of the class.
	 */
	public HomeMB() {
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
	 * This method prepares the system to create a new payment.
	 */
	public void prepareHome() {
		this.token = JsfUtils.getSessionToken();
	}

	/**
	 * This method proceed the logout of the user on the system.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void logout(ActionEvent evt) {
		this.token = null;
		JsfUtils.clearSession();
		JsfUtils.pageRedirect(evt);
	}

	/**
	 * This method provides the information to control the user interface. It is
	 * used to controls the <i>menus</i> exhibition and also the buttons
	 * <b>login</b>, <b>logout</b>, etc.
	 * 
	 * @return boolean
	 * @see LoggedIn
	 */
	@Produces
	@LoggedIn
	public boolean isLoggedIn() {
		return token != null;
	}

}