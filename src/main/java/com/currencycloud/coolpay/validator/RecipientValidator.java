package com.currencycloud.coolpay.validator;

import java.io.Serializable;
import java.util.Iterator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.outputlabel.OutputLabel;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.model.Recipient;
import com.currencycloud.coolpay.repo.IRecipientRepo;
import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * This validator is to verify whether a recipient is already registered on the
 * system.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see Validator
 */
@RequestScoped
@Named(value = "recipientValidator")
public class RecipientValidator implements Validator, Serializable {

	private static final long serialVersionUID = -7642439489183232319L;

	/**
	 * Injection of the repository.
	 */
	@Inject
	private IRecipientRepo repo;

	@Override
	/**
	 * This override method is a custom validation for the recipient, whilst trying
	 * to register a new one.
	 */
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Recipient recipient = null;
		try {
			recipient = repo.findRecipientByName((String) value);
			if (recipient != null) {
				FacesMessage message = new FacesMessage(retrieveOutputLabel(component) + ":",
						JsfUtils.getMessage("recipient.alreadyRegistered"));
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		} catch (BadRequestException e) {
			JsfUtils.addMessageError(JsfUtils.getMessage("label.validation.error") + ":", e.getMessage());
		} catch (UnauthorisedException e) {
			JsfUtils.addMessageError(JsfUtils.getMessage("label.validation.error") + ":", e.getMessage());
		} catch (GenericException e) {
			JsfUtils.addMessageError(JsfUtils.getMessage("label.validation.error") + ":", e.getMessage());
		}
	}

	/**
	 * Method to retrieve the component's name in order to show the message.
	 * 
	 * @param uiComponent
	 *            Expects the user interface component in order to get its label to
	 *            compose the message.
	 * @return String
	 */
	private String retrieveOutputLabel(UIComponent uiComponent) {
		OutputLabel ol = null;
		if (!uiComponent.getParent().getChildren().isEmpty()) {
			Iterator<UIComponent> iterator = uiComponent.getParent().getChildren().iterator();
			while (iterator.hasNext()) {
				UIComponent component = ((UIComponent) iterator.next());
				if (component instanceof OutputLabel) {
					if (((OutputLabel) component).getFor().toString()
							.equals(new String(uiComponent.getId().toString()))) {
						ol = ((OutputLabel) component);
						break;
					}
				}
			}
		}
		return (ol == null) ? "" : ol.getValue().toString();
	}

}