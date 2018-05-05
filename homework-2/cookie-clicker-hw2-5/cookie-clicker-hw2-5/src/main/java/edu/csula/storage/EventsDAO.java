package edu.csula.storage;

import java.util.List;
import java.util.Optional;

import edu.csula.models.Event;

/**
 * EventsDAO defines the database access layer between the business layer and
 * the database layer
 */
public interface EventsDAO {
	/**
	 * Find all events given storage
	 */
	public List<Event> getAll();
	/**
	 * Find certain event given its id
	 */
	public Optional<Event> getById(int id);
	/**
	 * Change a event given its id
	 */
	public void set(int id, Event event);
	/**
	 * adds a new event to database storage layer
	 */
	public void add(Event event);
	/**
	 * remove an existing event by id
	 */
	public void remove(int id);
}
