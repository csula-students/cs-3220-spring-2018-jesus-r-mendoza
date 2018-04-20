package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

import org.junit.*;

import org.mockito.stubbing.Answer;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UsersDAOImplTest {
	private HttpSession context;
	private UsersDAO dao;
	private User user;

	@Before
	public void setup() {
		context = mock(HttpSession.class);
		doAnswer((Answer) invocation -> {
			return user;
		}).when(context).getAttribute(eq(UsersDAOImpl.CONTEXT_NAME));
		doAnswer((Answer) invocation -> {
			user = (User) invocation.getArgument(1);
			return null;
		}).when(context).setAttribute(eq(UsersDAOImpl.CONTEXT_NAME), any(User.class));
		doAnswer((Answer) invocation -> {
			user = null;
			return null;
		}).when(context).invalidate();
		dao = new UsersDAOImpl(context);
	}

	@Test
	public void authenticate() throws Exception {
		// set up
		when(context.getAttribute(UsersDAOImpl.CONTEXT_NAME)).thenReturn(null);
		// verify
		assertEquals(true, dao.authenticate("admin", "cs3220password"));
		User authenticatedUser = new User(0, "admin", "cs3220password");
		// should set the new user into the HttpSession
		verify(context).setAttribute(UsersDAOImpl.CONTEXT_NAME, authenticatedUser);
	}

	@Test
	public void getAuthenticatedUser() throws Exception {
		// verify
		dao.authenticate("admin", "cs3220password");
		User authenticatedUser = new User(0, "admin", "cs3220password");
		// verify
		assertEquals(authenticatedUser, dao.getAuthenticatedUser().get());
	}

	@Test
	public void logout() throws Exception {
		assertEquals(true, dao.authenticate("admin", "cs3220password"));
		dao.logout();
		verify(context).invalidate();
		assertFalse(dao.getAuthenticatedUser().isPresent());
	}
}
