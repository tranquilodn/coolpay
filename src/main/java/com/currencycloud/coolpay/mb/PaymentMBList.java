package com.currencycloud.coolpay.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.model.Payment;
import com.currencycloud.coolpay.repo.IPaymentRepo;

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
@Named(value = "paymentList")
public class PaymentMBList extends AbstractMBList<Payment> {

	/**
	 * Serial version UID of the class.
	 */
	private static final long serialVersionUID = 7001386333359072846L;

	/**
	 * The injection of the <b>IPaymentRepo</b>
	 */
	@Inject
	private IPaymentRepo paymentRepo;

	/**
	 * This method retrieves all the pertinent entities from the server.
	 */
	@Override
	@PostConstruct
	public void retrieveAll() {
		try {
			super.list = paymentRepo.findAll();
		} catch (BadRequestException e) {
			e.printStackTrace();
		} catch (UnauthorisedException e) {
			e.printStackTrace();
		} catch (GenericException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to retrieve the object <b>Payment</b>.
	 * 
	 * @param paymentId
	 *            Expects the payment id
	 * @return Payment
	 */
	public Payment getPayment(String paymentId) {
		Payment result = null;
		for (Payment p : super.list) {
			if (p.getId().equals(paymentId)) {
				result = p;
			}
		}
		return result;
	}

	/**
	 * This method returns the whole list of <i>payments</i> of a specific
	 * <i>recipient</i>
	 * 
	 * @param recipientId
	 *            Expects the id of the recipient.
	 * @return ArrayList
	 */
	public List<Payment> getPayments(String recipientId) {
		List<Payment> result = new ArrayList<Payment>(0);
		try {
			for (Payment p : super.list) {
				/*
				 * In order to avoid null pointer exception, we need to test whether there is a
				 * recipient object in the payment.
				 */
				if (p.getRecipientId() != null) {
					if (p.getRecipientId().equals(recipientId)) {
						result.add(p);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}