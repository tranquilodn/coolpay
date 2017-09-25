package com.currencycloud.coolpay.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.model.Payment;
import com.currencycloud.coolpay.model.Recipient;
import com.currencycloud.coolpay.model.enums.CurrencyType;
import com.currencycloud.coolpay.repo.IPaymentRepo;
import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * This class is the managed bean for the payments.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @since 1.5
 * @see AbstractMB
 */
@SessionScoped
@Named(value = "paymentMB")
public class PaymentMB extends AbstractMB<Payment, String> implements Serializable {

	/**
	 * Serial version UID of the class.
	 */
	private static final long serialVersionUID = -143941902762310018L;

	/**
	 * Payments repository
	 */
	@Inject
	private IPaymentRepo repo;

	/**
	 * Payment list managed bean.
	 */
	@Inject
	private PaymentMBList paymentList;

	/**
	 * Recipient list managed bean.
	 */
	@Inject
	private RecipientMBList recipientList;

	/**
	 * Payment object.
	 */
	private Payment payment;

	/**
	 * Recipient object.
	 */
	private Recipient recipient;

	/**
	 * Default constructor of the class.
	 */
	public PaymentMB() {
	}

	/**
	 * Initialiser method of the managed bean.
	 */
	@Override
	@PostConstruct
	public void init() {
		super.init();
	}

	/**
	 * "getter" for the <i>Payment</i>
	 * 
	 * @return Payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * "setter" for the <i>Payment</i>
	 * 
	 * @param payment
	 *            Expects a payment object.
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	/**
	 * "getter" for the <i>Recipient</i>
	 * 
	 * @return Payment
	 */
	public Recipient getRecipient() {
		return recipient;
	}

	/**
	 * "setter" for the <i>Recipient</i>, used to render the recipient's name on the
	 * screen.
	 * 
	 * @param recipient
	 *            Expects a recipient object.
	 */
	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	/**
	 * Method which returns a list of all payments for a specific <i>recipient</i>.
	 * 
	 * @return ArrayList
	 */
	public List<Payment> getPayments() {
		return paymentList.getPayments(this.recipient.getId());
	}

	/**
	 * Provides a list of the <i>currencies</i> available on the system to use. This
	 * list is provided in a <i>enum</i> class.
	 * 
	 * @return CurrencyType[]
	 * @see CurrencyType
	 */
	public CurrencyType[] getCurrencies() {
		return CurrencyType.values();
	}

	/**
	 * This method prepares the system to create a new payment.
	 */
	public void prepareEdit() {
		this.payment = new Payment(CurrencyType.GBP);
		this.payment.setRecipientId(this.recipient.getId());
	}

	/**
	 * This method is used to prepare the view of a payment which was already placed
	 * on the system.
	 */
	public void prepareView() {
		if (getId() != null)
			this.payment = paymentList.getPayment(getId());
	}

	/**
	 * This method is used to invoke the <i>view</i> of all the payments for a
	 * specific <i>recipient</i>
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void actionList(ActionEvent evt) {
		String id = (String) evt.getComponent().getAttributes().get("entityId");
		this.recipient = recipientList.getRecipient(id);
		JsfUtils.pageRedirect(evt);
	}

	/**
	 * This method is override from the default one of the superclass, because we
	 * need an additional action, which is the recipient object in order to create a
	 * new payment entity.
	 */
	@Override
	public void actionNew(ActionEvent evt) {
		String recipientId = (String) evt.getComponent().getAttributes().get("entityId");
		this.recipient = recipientList.getRecipient(recipientId);
		super.actionNew(evt);
	}

	/**
	 * This method is invoked by the user interface in order to save a new payment
	 * on the system.
	 * 
	 * @param evt
	 *            Expects the event triggered on the user interface.
	 */
	public void actionSave(ActionEvent evt) {
		try {
			payment = repo.save(payment);
			// triggers the event which will update the list of payments.
			super.entityEventSrc.fire(payment);
			if (!getConversation().isTransient())
				super.getConversation().end();
			JsfUtils.pageRedirect(evt);
			JsfUtils.addMessageInfo(JsfUtils.getMessage("label.saving") + ":", JsfUtils.getMessage("payment.created"));
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