package edu.csula.models;

public class User {
	private int id;
	private String username;
	private String password;

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final User user = (User) obj;
		return this.id == user.id && this.username.equals(user.username) &&
			this.password.equals(user.password);
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
