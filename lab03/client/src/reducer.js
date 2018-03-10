import Generator from '../src/models/generator';
import constants from './constants';

export default function reducer (state, action) {
	switch (action.type) {
	case constants.actions.EXAMPLE:
		state.example = action.payload;
		return state;
    case constants.actions.BUY_GENERATOR:
            
        let index = 0;

		for (let i = 0; i < state.generators.length; i++) {
		if (state.generators[i].name == action.payload.name) {
			index = i;
		  }
        }    
		const generator = new Generator(Object.assign({}, state.generators[index]));
		let price = Math.ceil(generator.getCost());

		if (state.counter >= price) {
            state.counter = state.counter - price;
            state.generators[index].quantity++;
		}
        return state;
            
	default:
		return state;
	}
}