package com.currencycloud.coolpay.json.parser;

import java.io.IOException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import com.currencycloud.coolpay.json.service.LoginService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The <b>LoginJsonParser</b> is used to parse the json object as a result of a
 * requisition of the class <i>LoginService</i>
 * 
 * @author Tranquilo Dognini Neto
 * @see LoginService
 * @version 1.0
 */
public class LoginJsonParser extends AbstractJsonParser<String> {

	public LoginJsonParser() {
		super(String.class);
	}

	/**
	 * This method build up the String which will be submitted to the server in
	 * order to obtain the <i>token</i> which means the user is authenticated in the
	 * system.
	 * 
	 * @param username
	 *            Expects the username.
	 * @param apikey
	 *            Expects the password (apikey).
	 * @return String
	 */
	public String getLoginObject(String username, String apikey) {
		JsonObjectBuilder userBuilder = Json.createObjectBuilder();
		userBuilder.add("username", username).add("apikey", apikey);
		return userBuilder.build().toString();
	}

	/**
	 * This method gets from the result of the transaction, the <i>token</i>.
	 * 
	 * @param body
	 *            Expects the body of the content returned as a result of the
	 *            transaction with the server.
	 * @return String
	 */
	public String getToken(String body) {
		String token = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode objectNode = objectMapper.readValue(body, JsonNode.class);
			List<String> list = objectNode.findValuesAsText("token");
			for (String str : list) {
				token = str;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}

}
