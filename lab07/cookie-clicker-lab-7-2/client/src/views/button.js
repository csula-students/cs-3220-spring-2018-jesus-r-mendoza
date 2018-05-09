export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);

			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)
		}
	};
}
