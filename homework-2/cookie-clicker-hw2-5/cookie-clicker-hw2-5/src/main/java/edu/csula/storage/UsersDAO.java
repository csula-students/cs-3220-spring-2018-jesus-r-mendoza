package edu.csula.storage;

import java.util.Collection;
import java.util.Optional;

import edu.csula.models.User;

/**
 * UsersDAO defines the database access layer between the business layer and
 * the database layer
 */
public interface UsersDAO {
	/**
	 * Authenticate user and return true if user is authenticated
	 */
	public boolean authenticate(String username, String password);

	/**
	 * Returns true if the user is current authenticated
	 */
	public Optional<User> getAuthenticatedUser();

	/**
	 * Log out current user
	 */
	public void logout();
}
