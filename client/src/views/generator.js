export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: add click event
		}
	};
}
