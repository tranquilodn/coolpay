package com.currencycloud.coolpay.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This entity class implements the Recipient, which represents who will receive
 * the money send for.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipient implements Serializable {

	/**
	 * Serial version UID of the class
	 */
	private static final long serialVersionUID = -5450750428617006290L;

	/**
	 * Property id, which contains the id of the recipient
	 */
	private String id;

	/**
	 * Property name of the recipient
	 */
	@NotNull
	@JsonProperty("name")
	private String name;

	/**
	 * Default constructor of the class.
	 */
	public Recipient() {
		super();
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param name
	 *            Expects the Recipient Name.
	 */
	public Recipient(String name) {
		super();
		this.name = name;
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param id
	 *            Expects the Recipient Id.
	 * @param name
	 *            Expects the Recipient Name.
	 */
	public Recipient(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "recipient [name=" + this.name + "]";
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
		Recipient other = (Recipient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}