package com.currencycloud.coolpay.repo;

import java.util.List;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.json.service.PaymentService;
import com.currencycloud.coolpay.model.Payment;
import com.currencycloud.coolpay.repo.impl.IPaymentRepoImpl;

/**
 * This is the interface for the payment activities. It is an inherited class of
 * the class <b>IAbstractRepo</b> and it is implemented through the class
 * <b>IPaymentRepoImpl</b>.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see IAbstractRepo
 * @see IPaymentRepoImpl
 */
public interface IPaymentRepo extends IAbstractRepo {

	/**
	 * This method returns a whole list of payments, including the recipient objects
	 * for each payment.
	 * 
	 * @return ArrayList
	 * @throws BadRequestException
	 *             Happens when the server returns the error code 400.
	 * @throws UnauthorisedException
	 *             Happens when the server returns the error code 401.
	 * @throws GenericException
	 *             Happens when the server returns any error code but 400 and 401.
	 * @see IPaymentRepoImpl
	 * @see PaymentService
	 */
	public List<Payment> findAll() throws BadRequestException, UnauthorisedException, GenericException;

	/**
	 * This method is to place a new payment on the system.
	 * 
	 * @param payment
	 *            Expects a payment object.
	 * @return Payment
	 * @throws BadRequestException
	 *             Happens when the server returns the error code 400.
	 * @throws UnauthorisedException
	 *             Happens when the server returns the error code 401.
	 * @throws GenericException
	 *             Happens when the server returns any error code but 400 and 401.
	 * @see IPaymentRepoImpl
	 * @see PaymentService
	 */
	public Payment save(Payment payment) throws BadRequestException, UnauthorisedException, GenericException;

}