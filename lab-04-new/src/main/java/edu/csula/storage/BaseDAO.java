package edu.csula.storage;

/**
 * BaseDAO defines the common need of abstracting out the storage option from the
 * DAO
 */
public abstract class BaseDAO<T> {
	private final Storage<T> storage;

	public BaseDAO(Storage<T> storage) {
		this.storage = storage;
	}
}
