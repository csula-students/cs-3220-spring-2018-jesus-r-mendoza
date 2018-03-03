export default function reducer (state, action) {
	switch (action.type) {
	case 'EXAMPLE_MUTATION':
		state.example = action.payload;
		return state;
    case 'BUY_GENERATOR':
	default:
		return state;
	}
}

