package com.currencycloud.coolpay.repo.impl;

import javax.ejb.Stateless;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.json.parser.LoginJsonParser;
import com.currencycloud.coolpay.json.service.LoginService;
import com.currencycloud.coolpay.model.ResponseService;
import com.currencycloud.coolpay.repo.ILoginRepo;

/**
 * This is the implemented class for the <i>login</i> activities.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see ILoginRepo
 */
@Stateless
public class ILoginRepoImpl implements ILoginRepo {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.currencycloud.coolpay.repo.ILoginRepo#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String login(String username, String apikey)
			throws BadRequestException, UnauthorisedException, GenericException {
		String result = null;
		LoginService service = new LoginService();
		ResponseService resultService = service.login(username, apikey);
		int status = resultService.getStatus();
		if (status == 200) {
			LoginJsonParser parser = new LoginJsonParser();
			result = parser.getToken(resultService.getBody());
		} else {
			if (status == 400) {
				throw new BadRequestException(resultService.getBody());
			} else if (status == 401) {
				throw new UnauthorisedException(resultService.getBody());
			} else {
				throw new GenericException(resultService.getBody());
			}
		}
		return result;
	}

}