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
            this.addEventListener('click', () => {
                this.store.dispatch({
                    type: 'BUTTON_CLICK'
                });
            });
        }
        
        handleStateChange (newState) {
        }

        connectedCallback () {            
            this.innerHTML = `<button>Clicke Me</button>`;
            
            this.store.subscribe(this.onStateChange);
        }

        disconnectedCallback () {
            this.store.unsubscribe(this.onStateChange);
        }
        
	};
}
