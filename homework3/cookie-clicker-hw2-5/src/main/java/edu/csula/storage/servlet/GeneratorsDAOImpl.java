package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "generators";

	public GeneratorsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get a list of generators from the context
		Object obj = context.getAttribute(CONTEXT_NAME);
		if ( obj != null )
			return (ArrayList<Generator>) obj;
		return new ArrayList<>();
	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get a certain generator from context
		List<Generator> gens = this.getAll();
		for ( Generator g : gens ) {
			if ( g.getId() == id )
				return Optional.of(g);
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: change a certain generator from context
		List<Generator> gen = this.getAll();
		for ( int i = 0; i < gen.size(); i++ ) {
			if ( gen.get(i).getId() == id )
				gen.set(i, generator);
		}
		this.context.setAttribute(CONTEXT_NAME, generator);
	}

	@Override
	public void add(Generator generator) {
		// TODO: add a new generator to the context
		List<Generator> gens = getAll();
		gens.add(generator);
		this.context.setAttribute(CONTEXT_NAME, gens);
	}

	@Override
	public void remove(int id) {
		// TODO: remove a single generator from the context
		List<Generator> gens = this.getAll();
		for ( int i = 0; i < gens.size(); i++ ) {
			if ( gens.get(i).getId() == id )
				gens.remove(i);
		}
		this.context.setAttribute(CONTEXT_NAME, gens);
	}
}
