package com.currencycloud.coolpay.mb;

import java.io.Serializable;
import java.util.Currency;

import javax.enterprise.context.Conversation;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * This abstract class is used to implement some common functions which will be
 * used in all forms.
 * 
 * @author Tranquilo Dognini Neto
 * @param <T>
 *            Entity class
 * @param <PK>
 *            Primary key data type
 * @version 1.0
 * @since 1.5
 * @see LoginMB
 * @see PaymentMB
 * @see RecipientMB
 */
public abstract class AbstractMB<T, PK> implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -1042332315353405498L;

	/**
	 * This variable is used to control the <i>edit</i> form, in order to use it to
	 * create a new object entity and also to show them to the user.
	 */
	private Boolean managed;

	/**
	 * Primary key of the entity.
	 */
	private PK id;

	/**
	 * Variable used to trigger the event, in order to update the list of the object
	 * from the server.
	 */
	@Inject
	protected Event<T> entityEventSrc;

	/**
	 * This variable controls the conversational status of the transaction.
	 */
	@Inject
	private Conversation conversation;

	/**
	 * Default constructor of the managed bean
	 */
	public AbstractMB() {
	}

	/**
	 * Default initialiser of the managed bean.
	 */
	protected void init() {
		setManaged(false);
	}

	/**
	 * Returns the <b>PK</b> which is the primary key of the entity.
	 * 
	 * @return PK
	 */
	public PK getId() {
		return id;
	}

	/**
	 * Defines the <b>PK</b> which is the primary key of the entity.
	 * 
	 * @param id
	 *            Expects the entity id
	 */
	public void setId(PK id) {
		this.id = id;
	}

	/**
	 * This method returns the </i>conversation</i> object, in order to control the
	 * conversations throughout the system.
	 * 
	 * @return Conversation
	 */
	protected Conversation getConversation() {
		return conversation;
	}

	/**
	 * This method returns the state of the forms <i>edit</i> and <i>view</i>.
	 * 
	 * @return boolean
	 */
	public boolean isManaged() {
		return this.managed;
	}

	/**
	 * This method is used to define the state of the forms <i>edit</i> and
	 * <i>view</i>.
	 * 
	 * @param managed
	 *            Expects the state of the managed bean.
	 */
	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	/**
	 * This method provides the <i>Locale</i> in order to configure the number
	 * properly on the user interface.
	 * 
	 * @return String
	 */
	public String getLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
	}

	/**
	 * This method states the default <i>Locale</i> in order to show in the user
	 * interface with the number.
	 * 
	 * @return String
	 */
	public String getCurrencySymbol() {
		return Currency.getInstance(FacesContext.getCurrentInstance().getViewRoot().getLocale()).getSymbol();
	}

	/**
	 * Used in the view, this method states the default fraction digits in order to
	 * show at the user interface de number rendered properly.
	 * 
	 * @return int
	 */
	public int getDefaultFractionDigits() {
		return Currency.getInstance(FacesContext.getCurrentInstance().getViewRoot().getLocale())
				.getDefaultFractionDigits();
	}

	/**
	 * Starts a transaction in order to create a new entity. this is a generic
	 * method which can be used by default, I mean, without any further
	 * implementation on the inherited classes, and also can be extended, if
	 * necessary to do so.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void actionNew(ActionEvent evt) {
		setId(null);
		setManaged(false);
		getConversation().begin();
		JsfUtils.pageRedirect(evt); // controls the navigation
	}

	/**
	 * This method close the transaction (conversation) and finish the process.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void actionDone(ActionEvent evt) {
		setId(null);
		setManaged(false);
		if (!getConversation().isTransient())
			getConversation().end();
		JsfUtils.pageRedirect(evt);
	}

	/**
	 * This method cancels a transaction with no changes on the system.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void actionCancel(ActionEvent evt) {
		setId(null);
		setManaged(false);
		if (!getConversation().isTransient())
			getConversation().end();
		JsfUtils.pageRedirect(evt);
	}

	/**
	 * This method is used to show the entity, and visualise the details of it.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	@SuppressWarnings("unchecked")
	public void actionView(ActionEvent evt) {
		setId((PK) evt.getComponent().getAttributes().get("entityId"));
		JsfUtils.pageRedirect(evt);
	}

}