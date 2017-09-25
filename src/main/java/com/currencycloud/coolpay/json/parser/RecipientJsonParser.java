package com.currencycloud.coolpay.json.parser;

import com.currencycloud.coolpay.model.Recipient;

public class RecipientJsonParser extends AbstractJsonParser<Recipient> {

	public RecipientJsonParser() {
		super(Recipient.class);
	}

	/**
	 * This method returns an object in order to submit a registration of a new
	 * recipient.
	 * 
	 * @param name
	 *            Expects the name of the recipient.
	 * @return String
	 */
	public String getNewRecipientObject(String name) {
		String result = null;
		result = "{ 'recipient': { 'name': '" + name.trim() + "' }}";
		return result;
	}

}