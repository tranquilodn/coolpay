package com.currencycloud.coolpay.json.service;

import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.currencycloud.coolpay.json.parser.PaymentJsonParser;
import com.currencycloud.coolpay.model.Payment;
import com.currencycloud.coolpay.model.ResponseService;
import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * This class is responsible for submit all requisitions to the server regarding
 * the <i>payment</i> process on the system. It will return the successful new
 * payment submitted and its results, and also a list of all payments which have
 * been done.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.1
 * @see BaseService
 */
public class PaymentService extends BaseService {

	/**
	 * This method returns a list of all payments which are on the server submitted.
	 * 
	 * @return ResponseService
	 */
	@Produces
	public ResponseService retrieveAllPayments() {
		Client client = ClientBuilder.newClient();
		Response response = client.target(super.getBaseUrl() + "/payments").request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", super.getBearerAuthenticator(JsfUtils.getSessionToken())).get();
		ResponseService responseService = new ResponseService();
		responseService.setStatus(response.getStatus());
		responseService.setHeader(response.getHeaders().toString());
		responseService.setBody(response.readEntity(String.class));
		return responseService;
	}

	/**
	 * This method submits a new payment on the systems, and returns an object
	 * <i>payment</i>.
	 * 
	 * @param payment
	 *            Expects a payment object.
	 * @return ResponseService
	 */
	@Produces
	public ResponseService newPayment(Payment payment) {
		Client client = ClientBuilder.newClient();
		PaymentJsonParser parser = new PaymentJsonParser();
		Entity<String> payload = Entity.json(parser.getNewPaymentObject(payment));
		Response response = client.target(super.getBaseUrl() + "/payments").request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", super.getBearerAuthenticator(JsfUtils.getSessionToken())).post(payload);
		ResponseService responseService = new ResponseService();
		responseService.setStatus(response.getStatus());
		responseService.setHeader(response.getHeaders().toString());
		responseService.setBody(response.readEntity(String.class));
		return responseService;
	}

}