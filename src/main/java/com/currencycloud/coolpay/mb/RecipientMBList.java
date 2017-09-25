package com.currencycloud.coolpay.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.model.Recipient;
import com.currencycloud.coolpay.repo.IRecipientRepo;

/**
 * This class is a managed bean list. The purpose is to provide a complete list
 * of the main entities involved in this context, in order to guarantee a
 * minimal network traffic necessary to run the system, and also ensure that the
 * list will be always updated.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @since 1.5
 * @see AbstractMBList
 */
@SessionScoped
@Named(value = "recipientList")
public class RecipientMBList extends AbstractMBList<Recipient> {

	/**
	 * Serial version UID of the class.
	 */
	private static final long serialVersionUID = 513811688225744118L;

	/**
	 * The injection of the <b>IRecipientRepo</b>
	 */
	@Inject
	private IRecipientRepo recipientRepo;

	/**
	 * This method retrieves all the pertinent entities from the server.
	 */
	@Override
	@PostConstruct
	public void retrieveAll() {
		try {
			super.list = recipientRepo.findAll();
		} catch (BadRequestException e) {
			e.printStackTrace();
		} catch (UnauthorisedException e) {
			e.printStackTrace();
		} catch (GenericException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to retrieve the object <b>Recipient</b>.
	 * 
	 * @param recipientId
	 *            Expects the recipient id
	 * @return Recipient
	 */
	public Recipient getRecipient(String recipientId) {
		Recipient result = null;
		for (Recipient r : super.list) {
			if (r.getId().equals(recipientId)) {
				result = r;
			}
		}
		return result;
	}

}