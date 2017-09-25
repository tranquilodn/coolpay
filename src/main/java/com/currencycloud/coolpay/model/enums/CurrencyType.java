package com.currencycloud.coolpay.model.enums;

/**
 * This enumerator implements the list of possible currencies available on the
 * system. If necessary add more currencies, the only action needed is add as
 * follows:
 * <p>
 * GBP("GBP"), EUR("EUR"), USD("USD"), BRL("BRL");
 * </p>
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 */
public enum CurrencyType {

	/*
	 * Add here the other currencies.
	 */
	GBP("GPB");

	private String currencyType;

	private CurrencyType(String type) {
		this.currencyType = type;
	}

	/**
	 * Returns the label of the currency, in order to show in the user interface
	 * 
	 * @return String
	 */
	public String getLabel() {
		return currencyType;
	}

}