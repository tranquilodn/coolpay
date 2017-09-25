package com.currencycloud.coolpay.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.model.Recipient;
import com.currencycloud.coolpay.repo.IRecipientRepo;
import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * 
 * This class represents the <i>recipients</i> managed bean, which is the main
 * managed bean for this system. This is an extended class from the
 * <b>AbstractMB</b> class.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @since 1.5
 * @see AbstractMB
 */
@SessionScoped
@Named(value = "recipientMB")
public class RecipientMB extends AbstractMB<Recipient, String> implements Serializable {

	/**
	 * Serial version UID of the class
	 */
	private static final long serialVersionUID = -6435346363306824932L;

	/**
	 * Injection of the <b>IRecipientRepo</b>. this is the repository which will be
	 * used to make the transactions with the server.
	 */
	@Inject
	private IRecipientRepo repo;

	/**
	 * Injection of the <b>RecipientMBList</b>. I'm doing this in order to save
	 * network traffic.
	 * 
	 */
	@Inject
	private RecipientMBList recipientList;

	/**
	 * Variable which represents the recipient
	 */
	private Recipient recipient;

	/**
	 * Default constructor of the class.
	 */
	public RecipientMB() {
	}

	/**
	 * Default initialiser of the class.
	 */
	@Override
	@PostConstruct
	public void init() {
		super.init();
	}

	/**
	 * "getter" for the recipient
	 * 
	 * @return Recipient
	 */
	public Recipient getRecipient() {
		return recipient;
	}

	/**
	 * "setter" for the recipient
	 * 
	 * @param recipient
	 *            Expects a recipient object
	 */
	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	/**
	 * This method prepares the system to create a new recipient.
	 */
	public void prepareEdit() {
		this.recipient = new Recipient();
	}

	/**
	 * This method is used to prepare the view for the details of a recipient which
	 * was already placed on the system.
	 */
	public void prepareView() {
		/*
		 * I'm using the recipientList here, because through this way I can save network
		 * traffic. I'm using the events when I'm creating or updating the entity, and
		 * only that time I update the list of payments.
		 */
		if (getId() != null)
			this.recipient = recipientList.getRecipient(getId());
	}

	/**
	 * This method is invoked by the user interface in order to save a new recipient
	 * on the system.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void actionSave(ActionEvent evt) {
		try {
			recipient = repo.save(recipient);
			// trigger the event in order to proceed the update of the list.
			super.entityEventSrc.fire(recipient);
			if (!getConversation().isTransient())
				super.getConversation().end();
			JsfUtils.pageRedirect(evt);
			JsfUtils.addMessageInfo(JsfUtils.getMessage("label.saving") + ":",
					JsfUtils.getMessage("recipient.created"));
		} catch (BadRequestException e) {
			JsfUtils.addMessageError(JsfUtils.getMessage("label.saving.error") + ":", e.getMessage());
			return;
		} catch (UnauthorisedException e) {
			JsfUtils.addMessageError(JsfUtils.getMessage("label.saving.error") + ":", e.getMessage());
			return;
		} catch (GenericException e) {
			JsfUtils.addMessageError(JsfUtils.getMessage("label.saving.error") + ":", e.getMessage());
			return;
		}
	}

}