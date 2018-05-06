package edu.csula.storage;

import java.util.List;
import java.util.Optional;

import edu.csula.models.Guestbook;

public interface GuestbookDAO {
	public List<Guestbook> getAll();

	public void add(Guestbook entry);
}
