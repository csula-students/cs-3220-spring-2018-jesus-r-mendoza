package edu.csula.storage;

import java.util.Collection;

/**
 * Storage defines a few common methods of access/updating storage.
 *
 * The purpose of such interface is to create common need of storage so one can
 * swap out the storage from one to the other without changing of the entire
 * DAO layer.
 *
 * In example,
 *
 * ```java
 * MySQLStorage mysqlStorage = new MySQLStorage(jdbc);
 * EventsDAO eventDAO = new EventsDAOImpl(mysqlStorage);
 *
 * // or simply swap out the storage layer with Application Scope
 * ApplicationScopeStorage applicationScopeStorage = new ApplicationScopeStorage(getServletContext());
 * eventDAO = new EventsDAOImpl(applicationScopeStorage);
 *
 * Collection<Event> events = eventDAO.list();
 * ```
 */
public interface Storage<T> {
	/**
	 * list gets all objects from the given storage
	 *
	 * Usually you don't want to list all objects from storage layer
	 * but with a sort of query and pagination. To simplify our storage layer,
	 * I choose not to cover "query" and "pagination" in the interface
	 */
	public Collection<T> list();
	/**
	 * findById find a certain object from the storage
	 *
	 * Assume the id will all be integer format, we can find certain object
	 * by id
	 */
	public T findById(int id);
	/**
	 * Upsert does two things, it inserts a new object if the given id does not
	 * exist in the storage or update a certain object with its attached id
	 */
	public void upsert(T object);
}
