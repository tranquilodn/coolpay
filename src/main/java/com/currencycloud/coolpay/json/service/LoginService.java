package com.currencycloud.coolpay.json.service;

import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.currencycloud.coolpay.json.parser.LoginJsonParser;
import com.currencycloud.coolpay.model.ResponseService;

/**
 * This class is responsible for submit all requisitions to the server regarding
 * the <i>login</i> process on the system. It will return the successful login
 * giving a <b>token</b> in order to authenticate all further requisitions
 * properly on the server.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see BaseService
 */
public class LoginService extends BaseService {

	/**
	 * This methods executes the login process on the systems, returning the
	 * <b>token</b> This token is used to authenticate all further requisitions on
	 * the system.
	 * 
	 * @param username
	 *            Expects the username
	 * @param apikey
	 *            Expects the passwordv(apikey)
	 * @return String
	 */
	@Produces
	public ResponseService login(String username, String apikey) {
		Client client = ClientBuilder.newClient();
		LoginJsonParser parser = new LoginJsonParser();
		Entity<String> payload = Entity.json(parser.getLoginObject(username, apikey));
		Response response = client.target(super.getBaseUrl() + "/login").request(MediaType.APPLICATION_JSON_TYPE)
				.post(payload);
		ResponseService responseService = new ResponseService();
		responseService.setStatus(response.getStatus());
		responseService.setHeader(response.getHeaders().toString());
		responseService.setBody(response.readEntity(String.class));
		return responseService;
	}

}