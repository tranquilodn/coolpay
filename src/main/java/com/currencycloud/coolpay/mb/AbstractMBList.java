package com.currencycloud.coolpay.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;

/**
 * This class is the abstract class base to provide common behaviour for all the
 * <i>list</i> user interfaces for the entities, if necessary.
 * 
 * @author Tranquilo Dognini Neto
 *
 * @param <T>
 *            Entity
 * @version 1.0
 * @since 1.5
 * @see RecipientMBList
 */
public abstract class AbstractMBList<T> implements Serializable {

	/**
	 * The serial version UID of the class.
	 */
	private static final long serialVersionUID = -6318621258494372219L;

	/**
	 * This variable provides an object which store a list of all stored entities.
	 * Once the managed bean is initialised, a list is fulfilled. After that, only
	 * when a new entity is stored, updated or deleted this list is updated.
	 */
	protected List<T> list;

	/**
	 * This method is user on the user interface to provide the list of the objects
	 * which will be used to fulfil the <b>dataGrid</b> which will show the
	 * entities.
	 * 
	 * @return ArrayList
	 */
	@Produces
	public List<T> getList() {
		return list;
	}

	/**
	 * This is an observer method. It will be invoked always when the the user save
	 * a new entity.
	 * 
	 * @param e
	 *            is the object <b>T</b> created.
	 */
	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final T e) {
		retrieveAll();
	}

	/**
	 * Abstract method which will be used on the inherited classes.
	 */
	public abstract void retrieveAll();

}