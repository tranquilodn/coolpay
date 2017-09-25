package com.currencycloud.coolpay.repo;

import java.util.List;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.json.service.RecipientService;
import com.currencycloud.coolpay.model.Recipient;
import com.currencycloud.coolpay.repo.impl.IRecipientRepoImpl;

/**
 * This is the interface for the payment activities. It is an inherited class of
 * the class <b>IAbstractRepo</b> and it is implemented through the class
 * <b>IRecipientRepoImpl</b>.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see IAbstractRepo
 * @see IRecipientRepoImpl
 * @see RecipientService
 */
public interface IRecipientRepo extends IAbstractRepo {

	/**
	 * This method returns a list with all the recipients registered on the system.
	 * 
	 * @return ArrayList
	 * @throws BadRequestException
	 *             Happens when the server returns the error code 400.
	 * @throws UnauthorisedException
	 *             Happens when the server returns the error code 401.
	 * @throws GenericException
	 *             Happens when the server returns any error code but 400 and 401.
	 * @see IRecipientRepoImpl
	 * @see RecipientService
	 */
	public List<Recipient> findAll() throws BadRequestException, UnauthorisedException, GenericException;

	/**
	 * This method is to save a new payment on the system.
	 * 
	 * @param recipient
	 *            Expects a recipient object
	 * @return Recipient
	 * @throws BadRequestException
	 *             Happens when the server returns the error code 400.
	 * @throws UnauthorisedException
	 *             Happens when the server returns the error code 401.
	 * @throws GenericException
	 *             Happens when the server returns any error code but 400 and 401.
	 * @see IRecipientRepoImpl
	 * @see RecipientService
	 */
	public Recipient save(Recipient recipient) throws BadRequestException, UnauthorisedException, GenericException;

	/**
	 * This method provides an interface in order to verify whether a recipient is
	 * already registered on the system.
	 * 
	 * @param name
	 *            Expects the name of the recipient which will be verified.
	 * @return Recipient
	 * @throws BadRequestException
	 *             Happens when the server returns the error code 400.
	 * @throws UnauthorisedException
	 *             Happens when the server returns the error code 401.
	 * @throws GenericException
	 *             Happens when the server returns any error code but 400 and 401.
	 * @see IRecipientRepoImpl
	 * @see RecipientService
	 */
	public Recipient findRecipientByName(String name)
			throws BadRequestException, UnauthorisedException, GenericException;

}