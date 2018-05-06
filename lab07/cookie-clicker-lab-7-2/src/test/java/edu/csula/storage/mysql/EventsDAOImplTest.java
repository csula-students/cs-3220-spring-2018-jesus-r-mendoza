package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.csula.models.Event;
import edu.csula.storage.Database;
import edu.csula.storage.EventsDAO;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventsDAOImplTest {
	private Database context;
	private EventsDAO dao;

	@Test
	public void getAll() throws Exception {
		ResultSet getAllResultSet = mock(ResultSet.class);
		when(getAllResultSet.next()).thenReturn(true).thenReturn(false);
		when(getAllResultSet.getInt(1)).thenReturn(1);
		when(getAllResultSet.getString(2)).thenReturn("name");
		when(getAllResultSet.getString(3)).thenReturn("desc");
		when(getAllResultSet.getInt(4)).thenReturn(10);

		Statement getAllStatement = mock(Statement.class); 
		when(getAllStatement.executeQuery(EventsDAOImpl.getAllQuery)).thenReturn(getAllResultSet);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.createStatement()).thenReturn(getAllStatement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new EventsDAOImpl(context);

		// set up initial mock state
		Collection<Event> mockEvents = new ArrayList<>();
		mockEvents.add(new Event(1, "name", "desc", 10));

		// actual method execution
		Collection<Event> actualEvents = dao.getAll();

		// assert and verify
		assertEquals(1, actualEvents.size());
	}

	@Test
	public void getById() throws Exception {
		ResultSet resultSet = mock(ResultSet.class);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		when(resultSet.getInt(1)).thenReturn(1);
		when(resultSet.getString(2)).thenReturn("name");
		when(resultSet.getString(3)).thenReturn("desc");
		when(resultSet.getInt(4)).thenReturn(10);

		PreparedStatement getAllStatement = mock(PreparedStatement.class); 
		when(getAllStatement.executeQuery()).thenReturn(resultSet);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.prepareStatement(EventsDAOImpl.getByIdQuery)).thenReturn(getAllStatement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new EventsDAOImpl(context);

		// actual method execution
		Optional<Event> actualEvent = dao.getById(1);

		// assert and verify
		assertTrue(actualEvent.isPresent());
		assertEquals(actualEvent.get(), new Event(1, "name", "desc", 10));
	}

	@Test
	public void set() throws Exception {
		PreparedStatement statement = mock(PreparedStatement.class);
		doNothing().when(statement).setString(anyInt(), anyString());
		doNothing().when(statement).setInt(anyInt(), anyInt());
		when(statement.executeUpdate()).thenReturn(1);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.prepareStatement(EventsDAOImpl.setQuery)).thenReturn(statement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new EventsDAOImpl(context);

		// actual execution
		dao.set(1, new Event(1, "updated event", "description", 10));

		// verify
		verify(getAllConnection, times(1)).prepareStatement(EventsDAOImpl.setQuery);
		verify(statement, times(1)).executeUpdate();
	}

	@Test
	public void add() throws Exception {
		PreparedStatement addStatement = mock(PreparedStatement.class);
		doNothing().when(addStatement).setString(anyInt(), anyString());
		doNothing().when(addStatement).setInt(anyInt(), anyInt());
		when(addStatement.executeUpdate()).thenReturn(1);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.prepareStatement(EventsDAOImpl.addQuery)).thenReturn(addStatement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new EventsDAOImpl(context);

		// actual execution
		dao.add(new Event(1, "new event", "description", 10));

		// verify
		verify(getAllConnection, times(1)).prepareStatement(EventsDAOImpl.addQuery);
		verify(addStatement, times(1)).executeUpdate();
	}
}
