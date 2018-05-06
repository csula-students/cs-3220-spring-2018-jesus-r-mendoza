package edu.csula.storage.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.EventsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Event;

public class EventsDAOImpl implements EventsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM Events;";
	protected static final String getByIdQuery = "SELECT * FROM Events WHERE id = ?;";
	protected static final String setQuery = "";
	protected static final String addQuery = "";
	protected static final String removeQuery = "";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		// TODO: get all events from jdbc
				
		try {
			ArrayList<Event> events;			
			Connection c = context.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(getAllQuery);
			events = new ArrayList<>();
			while(rs.next()) {
				Event e = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("trigger_at"));
				events.add(e);
				return events;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return new ArrayList<>();
	}

	@Override
	public Optional<Event> getById(int id) {
		try {
			
			Connection c = context.getConnection();
			PreparedStatement pstmt = c.prepareStatement(getByIdQuery);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(getByIdQuery);
			rs.next();
			Event e = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("trigger_at"));
			return Optional.of(e);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: update specific event by id
	}

	@Override
	public void add(Event event) {
		// TODO: implement jdbc logic to add a new event
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove event by id
	}
}
