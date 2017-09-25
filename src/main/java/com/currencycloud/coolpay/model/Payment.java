package com.currencycloud.coolpay.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.currencycloud.coolpay.model.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This entity class represents the payment, which will be the sending money
 * transactions along the system.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment implements Serializable {

	/**
	 * Serial version UID for the entity class
	 */
	private static final long serialVersionUID = 5969504600291557146L;

	/**
	 * Variable which stores the payment id
	 */
	private String id;

	/**
	 * Variable which stores the amount of the payment
	 */
	@NotNull
	@JsonProperty("amount")
	private Double amount;

	/**
	 * Variable which stores which currency was used on the payment
	 */
	@NotNull
	@JsonProperty("currency")
	private CurrencyType currency;

	/**
	 * Variable which stores the recipient that the money was sent for
	 */
	@NotNull
	@JsonProperty("recipient_id")
	private String recipientId;

	/**
	 * Variable which stores the the status of the payment
	 */
	@JsonProperty("status")
	private String status;

	/**
	 * Default constructor of the entity class
	 */
	public Payment() {
		super();
	}

	/**
	 * Constructor with the currency as parameter.
	 * 
	 * @param currency
	 *            Expects the currency type object.
	 */
	public Payment(CurrencyType currency) {
		super();
		this.currency = currency;
	}

	/**
	 * Override constructor of the class, which contains the parameters in order to
	 * create and fulfil all the properties.
	 * 
	 * @param id
	 *            Expects the id of the payment.
	 * @param amount
	 *            Expects the amount of the payment.
	 * @param currency
	 *            Expects the currency of the payment.
	 * @param recipientId
	 *            Expects the Id of the recipient.
	 * @param status
	 *            Expects the status of the payment.
	 */
	public Payment(String id, Double amount, CurrencyType currency, String recipientId, String status) {
		super();
		this.id = id;
		this.amount = amount;
		this.currency = currency;
		this.recipientId = recipientId;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "payment [id=" + this.id + ", amount=" + this.amount + ", currency=" + this.currency + ", recipientId="
				+ this.recipientId + ", status=" + this.status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}