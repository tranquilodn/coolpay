package com.currencycloud.coolpay.json.parser;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.currencycloud.coolpay.json.service.LoginService;
import com.currencycloud.coolpay.model.Payment;

/**
 * The <b>PaymentJsonParserr</b> is used to parse the json object as a result of
 * a requisition of the class <i>LoginService</i>
 * 
 * @author Tranquilo Dognini Neto
 * @see LoginService
 * @version 1.0
 */
public class PaymentJsonParser extends AbstractJsonParser<Payment> {

	public PaymentJsonParser() {
		super(Payment.class);
	}

	/**
	 * This method build up the new payment json object in order to submit to the
	 * server a new payment for the <i>recipient</i>.
	 * 
	 * @param payment
	 *            Expects a payment object.
	 * @return String
	 */
	public String getNewPaymentObject(Payment payment) {
		JsonObjectBuilder paymentBuilder = Json.createObjectBuilder();
		paymentBuilder.add("amount", payment.getAmount().toString()).add("currency", payment.getCurrency().toString())
				.add("recipient_id", payment.getRecipientId());
		JsonObject paymentJsonObject = paymentBuilder.build();
		return paymentJsonObject.toString();
	}

}