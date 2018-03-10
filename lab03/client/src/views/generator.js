export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view    
            
			// TODO: subscribe to store on change event
            this.onStateChange = this.handleStateChange.bind(this);
            
			// TODO: add click event            
            console.log("constructed ---");
            
		}
        
        handleStateChange (newState) {
			this.textContent = newState.example;
            console.log("binded");
		}
        
		connectedCallback () {
            console.log("  btn connected  ")
			const generator = new Generator(Object.assign({}, store.state.generators[this.dataset.id]));

            this.innerHTML = `<h1>hello</h1>`;

		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
            console.log("disconnected");
            this.removeEventListener('click', this.onClickEvent);
		}
        
	};
}
