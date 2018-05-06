package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import edu.csula.storage.Database;
import edu.csula.storage.GuestbookDAO;
import edu.csula.models.Guestbook;

public class GuestbookDAOImpl implements GuestbookDAO {
	private Database context;

	public GuestbookDAOImpl(Database database) {
		this.context = database;
	}

	public List<Guestbook> getAll() {
		List<Guestbook> result = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM guestbooks;");
			while (rs.next()) {
				Guestbook entry = new Guestbook();
				entry.id = rs.getInt(1);
				entry.username = rs.getString(2);
				entry.comment = rs.getString(3);
				result.add(entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return result;
	}

	public void add(Guestbook entry) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement("INSERT INTO guestbooks (username, comment) VALUES (?, ?)")) {
			stmt.setString(1, entry.username);
			stmt.setString(2, entry.comment);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
