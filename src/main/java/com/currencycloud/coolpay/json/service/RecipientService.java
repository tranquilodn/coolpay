package com.currencycloud.coolpay.json.service;

import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.currencycloud.coolpay.json.parser.RecipientJsonParser;
import com.currencycloud.coolpay.model.Recipient;
import com.currencycloud.coolpay.model.ResponseService;
import com.currencycloud.coolpay.utils.JsfUtils;

/**
 * This class is responsible for submit all requisitions to the server regarding
 * the <i>recipient</i> process on the system. It will return the successful new
 * recipient submitted and its results, and also a list of all recipients which
 * have been registered.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.1
 * @see BaseService
 */
public class RecipientService extends BaseService {

	/**
	 * This method returns a list of all recipients which are already registered on
	 * the system.
	 * 
	 * @return ResponseService
	 */
	@Produces
	public ResponseService retrieveAllRecipients() {
		Client client = ClientBuilder.newClient();
		Response response = client.target(super.getBaseUrl() + "/recipients").request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", super.getBearerAuthenticator(JsfUtils.getSessionToken())).get();
		ResponseService responseService = new ResponseService();
		responseService.setStatus(response.getStatus());
		responseService.setHeader(response.getHeaders().toString());
		responseService.setBody(response.readEntity(String.class));
		return responseService;
	}

	/**
	 * This method submits a new recipient on the systems, and returns an object
	 * <i>recipient</i> created.
	 * 
	 * @param recipient
	 *            Expects a recipient object.
	 * @return ResponseService
	 */
	@Produces
	public ResponseService newRecipient(Recipient recipient) {
		Client client = ClientBuilder.newClient();
		RecipientJsonParser parser = new RecipientJsonParser();
		Entity<String> payload = Entity.json(parser.getNewRecipientObject(recipient.getName()));
		Response response = client.target(super.getBaseUrl() + "/recipients").request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", super.getBearerAuthenticator(JsfUtils.getSessionToken())).post(payload);
		ResponseService responseService = new ResponseService();
		responseService.setStatus(response.getStatus());
		responseService.setHeader(response.getHeaders().toString());
		responseService.setBody(response.readEntity(String.class));
		return responseService;
	}

	@Produces
	/**
	 * This method provides an interface in order to verify whether a recipient is
	 * already registered on the system.
	 * 
	 * @param name
	 *            Expects the name of the recipient which will be verified.
	 * @return ResponseService
	 */
	public ResponseService findRecipientByName(String name) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(super.getBaseUrl() + "/recipients?name=" + name)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", super.getBearerAuthenticator(JsfUtils.getSessionToken())).get();
		ResponseService responseService = new ResponseService();
		responseService.setStatus(response.getStatus());
		responseService.setHeader(response.getHeaders().toString());
		responseService.setBody(response.readEntity(String.class));
		return responseService;
	}

}