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
	protected static final String setQuery = "UPDATE Events SET ? = ? WHERE id = ?;";
	protected static final String addQuery = "INSERT INTO Events VALUES ( ?, ?, ?, ?);";
	protected static final String removeQuery = "DELETE FROM Events WHERE id = ?;";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}
	
	
	/*  Please read the __GRADLE-ISSUE-INFO.txt
	 *  
	 *  In this assignment I encountered a problem while running the "gradlew build" & "gradlew check" commands.
	 *  I only included the untested logic for each method in order to not leave a blank assignment.
	 *  Please read the file mentioned above to view the details and stacktrace. Thank you. 
	 */	
	
	
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
		
		/* Untested due to gradle issue ; will try to resolve by soon  */
		
		try ( Connection connection = context.getConnection(); PreparedStatement statement = connection.prepareStatement(getByIdQuery); ) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if( rs.next() ) {
				Event e = new Event(id, rs.getString("name"), rs.getString("description"), rs.getInt("trigger_at"));
				return Optional.of(e);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		
		/* Untested due to gradle issue ; will try to resolve by soon  */
		
		// TODO: update specific event by id
		try ( Connection connection = context.getConnection(); PreparedStatement statement = connection.prepareStatement(setQuery); ) {
			
			String[] attributes = new String[3];
			for ( String str : attributes ) {
				statement.setString(1, str);
				switch (str) {
					case "name":
						statement.setString(2, event.getName());
						break;
					case "description":
						statement.setString(2, event.getDescription());
						break;
					case "trigger_at":
						statement.setInt(2, event.getTriggerAt());
				}
				statement.setInt(3, id);
				statement.executeUpdate();
			}
									
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Event event) {
		
		/* Untested due to gradle issue ; will try to resolve by soon  */
		
		// TODO: implement jdbc logic to add a new event
		try (Connection c = context.getConnection(); PreparedStatement statement = c.prepareStatement(addQuery)) {
			statement.setInt(1, event.getId());
			statement.setString(2, event.getName());
			statement.setString(3, event.getDescription());
			statement.setInt(4, event.getTriggerAt());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		
		/* Untested due to gradle issue ; will try to resolve by soon  */
		
		// TODO: implement jdbc logic to remove event by id
		try (Connection c = context.getConnection(); PreparedStatement statement = c.prepareStatement(removeQuery)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
