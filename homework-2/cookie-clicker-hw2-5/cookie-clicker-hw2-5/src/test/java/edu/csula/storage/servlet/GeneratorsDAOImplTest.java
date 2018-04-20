package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.models.Generator;
import edu.csula.storage.GeneratorsDAO;

import org.junit.*;

import org.mockito.stubbing.Answer;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GeneratorsDAOImplTest {
	private ServletContext context;
	private GeneratorsDAO dao;
	private List<Generator> list;

	@Before
	public void setup() {
		context = mock(ServletContext.class);
		doAnswer((Answer) invocation -> {
			return list;
		}).when(context).getAttribute(eq(GeneratorsDAOImpl.CONTEXT_NAME));
		doAnswer((Answer) invocation -> {
			list = (List<Generator>) invocation.getArgument(1);
			return null;
		}).when(context).setAttribute(eq(GeneratorsDAOImpl.CONTEXT_NAME), any(List.class));
		dao = new GeneratorsDAOImpl(context);
	}

	@Test
	public void getAll() throws Exception {
		// set up initial mock state
		List<Generator> mockList = new ArrayList<>();
		mockList.add(new Generator(1, "name", "desc", 10, 10, 0));
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(mockList);

		// actual method execution
		List<Generator> actual = dao.getAll();

		// assert and verify
		verify(context).getAttribute(GeneratorsDAOImpl.CONTEXT_NAME);
		assertEquals(1, actual.size());
	}

	@Test
	public void getAllWhenNull() throws Exception {
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(null);
		// actual method execution
		List<Generator> actual = dao.getAll();

		// should return an empty list
		assertEquals(0, actual.size());
	}

	@Test
	public void getById() throws Exception {
		// set up initial mock state
		List<Generator> mock = new ArrayList<>();
		mock.add(new Generator(1, "name", "desc", 10, 10, 0));
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(mock);

		// actual method execution
		Optional<Generator> actual = dao.getById(1);

		// assert and verify
		verify(context).getAttribute(GeneratorsDAOImpl.CONTEXT_NAME);
		assertTrue(actual.isPresent());
		assertEquals(new Generator(1, "name", "desc", 10, 10, 0), actual.get());
	}

	@Test
	public void set() throws Exception {
		// set up initial mock state
		dao.add(new Generator(1, "name", "desc", 10, 10, 0));

		// actual method execution
		dao.set(1, new Generator(1, "new name", "new desc", 20, 50, 10));
		Optional<Generator> actualEvent = dao.getById(1);

		// assert and verify
		assertTrue(actualEvent.isPresent());
		assertEquals(new Generator(1, "new name", "new desc", 20, 50, 10), actualEvent.get());
	}

	@Test
	public void add() throws Exception {
		// set up
		List<Generator> expected = new ArrayList<>();
		expected.add(new Generator(1, "name", "desc", 10, 10, 0));
		// actual execution
		dao.add(new Generator(1, "name", "desc", 10, 10, 0));
		// verify
		assertEquals(expected, dao.getAll());
	}

	@Test
	public void remove() throws Exception {
		dao.add(new Generator(1, "name", "desc", 10, 10, 0));
		assertEquals(1, dao.getAll().size());
		dao.remove(1);
		assertEquals(0, dao.getAll().size());
	}
}
