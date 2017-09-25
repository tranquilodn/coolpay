package com.currencycloud.coolpay.repo;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.json.service.LoginService;
import com.currencycloud.coolpay.repo.impl.ILoginRepoImpl;

/**
 * This is the interface for the login activities. It is an inherited class of
 * the class <b>IAbstractRepo</b> and it is implemented through the class
 * <b>ILoginRepoImpl</b>.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see IAbstractRepo
 * @see ILoginRepoImpl
 */
public interface ILoginRepo extends IAbstractRepo {

	/**
	 * Method login is used to authenticate the current user on the system.
	 * 
	 * @param username
	 *            username
	 * @param apikey
	 *            password key
	 * @return String
	 * @throws BadRequestException
	 *             Happens when the server returns the error code 400.
	 * @throws UnauthorisedException
	 *             Happens when the server returns the error code 401.
	 * @throws GenericException
	 *             Happens when the server returns any error code but 400 and 401.
	 * @see ILoginRepoImpl
	 * @see LoginService
	 */
	public String login(String username, String apikey)
			throws BadRequestException, UnauthorisedException, GenericException;

}
