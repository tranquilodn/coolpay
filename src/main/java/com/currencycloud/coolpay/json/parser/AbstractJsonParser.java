package com.currencycloud.coolpay.json.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Generic abstract class which implements the parsers for the JSON objects.
 * 
 * @author Tranquilo Dognini Neto
 *
 * @param <T>
 *            Expects an Entity Class
 * @version 1.0
 * @see LoginJsonParser
 * @see PaymentJsonParser
 * @see RecipientJsonParser
 */
public abstract class AbstractJsonParser<T> {

	private Class<T> entityClass;

	/**
	 * The constructor of the class.
	 * 
	 * @param entityClass
	 *            Expects the entity class, which will be used to instantiate the
	 *            inherited classes.
	 */
	public AbstractJsonParser(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * This method implements a generic parsing in order to get an object from the
	 * JSON string.
	 * 
	 * @param body
	 *            Expects the body of the result of the transaction with the server.
	 * @param objectName
	 *            The name of the object which will be returned after the parsing.
	 * @return Object
	 */
	public T getObject(String body, String objectName) {
		T object = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(body);
			JsonNode objectNode = rootNode.path(objectName);
			object = objectMapper.readValue(objectNode.traverse(), entityClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * This method implements a generic parsing in order to get an object from the
	 * JSON string.
	 * 
	 * @param body
	 *            Expects the body of the result of the transaction with the server.
	 * @param objectName
	 *            The name of the object which will be returned after the parsing.
	 * @return ArrayList
	 */
	public List<T> getAllObjects(String body, String objectName) {
		List<T> result = new ArrayList<T>(0);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(body);
			JsonNode recipientsNode = rootNode.path(objectName);
			Iterator<JsonNode> elements = recipientsNode.elements();
			while (elements.hasNext()) {
				JsonNode r = elements.next();
				T object = objectMapper.readValue(r.traverse(), entityClass);
				result.add(object);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}