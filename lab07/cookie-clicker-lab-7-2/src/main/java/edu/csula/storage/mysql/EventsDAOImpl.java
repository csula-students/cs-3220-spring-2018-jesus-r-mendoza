package edu.csula.storage.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

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
	protected static final String addQuery = "INSERT INTO Events VALUES ( ?, ?, ?, ?);";
	protected static final String removeQuery = "";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		// TODO: get all events from jdbc
		
		try ( Connection connection = context.getConnection(); Statement statement = connection.createStatement(); ) {
			ArrayList<Event> events;			
			ResultSet rs = statement.executeQuery(getAllQuery);
			events = new ArrayList<>();
			while(rs.next()) {
				Event e = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("trigger_at"));
				events.add(e);
			}
			return events;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public Optional<Event> getById(int id) {
		System.out.println(id + " -- before");
		try ( Connection connection = context.getConnection(); PreparedStatement statement = connection.prepareStatement(getByIdQuery); ) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			boolean val = rs.next();
			System.out.println(val);
			if( val ) {
				System.out.println(id + " -- right before");
				Event e = new Event(id, rs.getString("name"), rs.getString("description"), rs.getInt("trigger_at"));
				System.out.println(id + " -- right after");
				System.out.println("retrieved values -- " + rs.getInt("id") + ", " + rs.getString("name") + ", " + rs.getString("description") + ", " + rs.getInt("trigger_at"));				
				System.out.println("retrieved values -- " + e.getId() + ", " + e.getName() + ", " + e.getDescription() + ", " + e.getTriggerAt() );				
				return Optional.of(e);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*
		try {
			
			Connection c = context.getConnection();
			PreparedStatement pstmt = c.prepareStatement(getByIdQuery);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeUpdate();
			rs.next();
			Event e = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("trigger_at"));
			return Optional.of(e);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: update specific event by id
	}

	@Override
	public void add(Event event) {
		// TODO: implement jdbc logic to add a new event
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(addQuery)) {
			stmt.setInt(1, event.getId());
			stmt.setString(2, event.getName());
			stmt.setString(3, event.getDescription());
			stmt.setInt(4, event.getTriggerAt());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove event by id
	}
}
