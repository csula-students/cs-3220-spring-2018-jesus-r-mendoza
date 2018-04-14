/*eslint-env node, jest */
import reducer from '../src/reducer';

it('reducer should be able to set example state from "EXAMPLE_MUTATION" action', () => {
	const action = {
		type: 'EXAMPLE_MUTATION',
		payload: 'mutated'
	};
	const initialState = {
		example: 'hello'
	};
	const expected = action.payload;
	expect(reducer(initialState, action).example).toBe(expected);
});
