package com.currencycloud.coolpay.repo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.json.parser.RecipientJsonParser;
import com.currencycloud.coolpay.json.service.RecipientService;
import com.currencycloud.coolpay.model.Recipient;
import com.currencycloud.coolpay.model.ResponseService;
import com.currencycloud.coolpay.repo.IRecipientRepo;

/**
 * This is the implemented class for the <i>recipient</i> activities.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see IRecipientRepo
 */
@Stateless
public class IRecipientRepoImpl implements IRecipientRepo {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.currencycloud.coolpay.repo.IRecipientRepo#findAll()
	 */
	@Override
	public List<Recipient> findAll() throws BadRequestException, UnauthorisedException, GenericException {
		List<Recipient> result = new ArrayList<Recipient>(0);
		RecipientService service = new RecipientService();
		ResponseService resultService = service.retrieveAllRecipients();
		int status = resultService.getStatus();
		if (status == 200) {
			RecipientJsonParser parser = new RecipientJsonParser();
			result.clear();
			result.addAll(parser.getAllObjects(resultService.getBody(), "recipients"));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.currencycloud.coolpay.repo.IRecipientRepo#save(com.currencycloud.coolpay.
	 * model.Recipient)
	 */
	@Override
	public Recipient save(Recipient recipient) throws BadRequestException, UnauthorisedException, GenericException {
		Recipient result = null;
		RecipientService service = new RecipientService();
		ResponseService resultService = service.newRecipient(recipient);
		int status = resultService.getStatus();
		if (status == 201) {
			RecipientJsonParser parser = new RecipientJsonParser();
			result = parser.getObject(resultService.getBody(), "recipient");
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

	/*
	 * (non-Javadoc)
	 * @see com.currencycloud.coolpay.repo.IRecipientRepo#findRecipientByName(java.lang.String)
	 */
	public Recipient findRecipientByName(String name)
			throws BadRequestException, UnauthorisedException, GenericException {
		Recipient result = null;
		List<Recipient> resultList = new ArrayList<Recipient>(0);
		RecipientService service = new RecipientService();
		ResponseService resultService = service.findRecipientByName(name);
		int status = resultService.getStatus();
		if (status == 200) {
			RecipientJsonParser parser = new RecipientJsonParser();
			resultList.clear();
			resultList.addAll(parser.getAllObjects(resultService.getBody(), "recipients"));
			for (Recipient r : resultList) {
				if (r.getName().equals(name)) {
					result = r;
				}
			}
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