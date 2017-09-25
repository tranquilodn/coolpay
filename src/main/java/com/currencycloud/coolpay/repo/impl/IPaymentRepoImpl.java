package com.currencycloud.coolpay.repo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.currencycloud.coolpay.exception.BadRequestException;
import com.currencycloud.coolpay.exception.GenericException;
import com.currencycloud.coolpay.exception.UnauthorisedException;
import com.currencycloud.coolpay.json.parser.PaymentJsonParser;
import com.currencycloud.coolpay.json.service.PaymentService;
import com.currencycloud.coolpay.model.Payment;
import com.currencycloud.coolpay.model.ResponseService;
import com.currencycloud.coolpay.repo.IPaymentRepo;

/**
 * This is the implemented class for the <i>payment</i> activities.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see IPaymentRepo
 */
@Stateless
public class IPaymentRepoImpl implements IPaymentRepo {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.currencycloud.coolpay.repo.IPaymentRepo#findAll(java.util.List)
	 */
	@Override
	public List<Payment> findAll() throws BadRequestException, UnauthorisedException, GenericException {
		List<Payment> result = new ArrayList<Payment>(0);
		PaymentService service = new PaymentService();
		ResponseService resultService = service.retrieveAllPayments();
		int status = resultService.getStatus();
		if (status == 200) {
			PaymentJsonParser parser = new PaymentJsonParser();
			result.clear();
			result.addAll(parser.getAllObjects(resultService.getBody(), "payments"));
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
	 * com.currencycloud.coolpay.repo.IPaymentRepo#save(com.currencycloud.coolpay.
	 * model.Payment)
	 */
	@Override
	public Payment save(Payment payment) throws BadRequestException, UnauthorisedException, GenericException {
		Payment result = null;
		PaymentService service = new PaymentService();
		ResponseService resultService = service.newPayment(payment);
		int status = resultService.getStatus();
		if (status == 201) {
			PaymentJsonParser parser = new PaymentJsonParser();
			result = parser.getObject(resultService.getBody(), "payment");
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