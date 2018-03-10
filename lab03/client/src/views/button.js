export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
            this.textContent = 'button init';
			this.onStateChange = this.handleStateChange.bind(this);
            console.log(this.textContent, this.store);
			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)=
        }
        
        handleStateChange (newState) {
            console.log('ExampleComponent#stateChange', this);
            this.textContent = 'btn clicked';
            console.log(this.textContent);
        }

        connectedCallback () {
            console.log('ExampleComponent#onConnectedCallback');
            this.store.subscribe(this.onStateChange);
        }

        disconnectedCallback () {
            console.log('ExampleComponent#onDisconnectedCallback');
            this.store.unsubscribe(this.onStateChange);
        }
        
	};
}
