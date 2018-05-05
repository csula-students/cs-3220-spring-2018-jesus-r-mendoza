/*eslint-env node, jest */
import reducer from '../src/reducer';
import constants from '../src/constants';
import * as mock from './mock';

test('should be able to set example state from "EXAMPLE_MUTATION" action', () => {
	const action = {
		type: constants.actions.EXAMPLE,
		payload: 'mutated'
	};
	const initialState = {
		example: 'hello'
	};
	const expected = action.payload;
	expect(reducer(initialState, action).example).toBe(expected);
});

test('should be able to muate resource and generators on "BUY_GENERATOR" action', () => {
	const action = {
		type: constants.BUY_GENERATOR,
		payload: {
			name: 'Grandma',
			quantity: 1
		}
	};
	const initialState = {
		counter: 10,
		generators: [ mock.generator ]
	};
	const expected = {
		counter: 0,
		generators: [ Object.assign({}, mock.generator, {quantity: 1}) ]
	};
	expect(reducer(initialState, action)).toEqual(expected);
});

test('should be able to increment counter based on modifier from "INCREMENT" action', () => {
	const action = {
		type: constants.INCREMENT,
		payload: 15
	};
	const initialState = {
		counter: 0,
		generators: [],
		stories: []
	};
	const expected = Object.assign({}, initialState, {counter: 15});
	expect(reducer(initialState, action)).toEqual(expected);
});

test('should be able to mutate story state on "CHECK_STORY" action', () => {
	const action = {
		type: constants.CHECK_STORY
	};
	const initialState = {
		counter: 11,
		generators: [mock.generator],
		story: [mock.story]
	};
	const expected = {
		counter: 11,
		generators: [mock.generator],
		story: [Object.assign({}, mock.story, {state: 'visible'})]
	};
	expect(reducer(initialState, action)).toEqual(expected);
});
