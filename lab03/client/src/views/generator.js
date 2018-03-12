import Generator from '../models/generator';
import constants from '../constants';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view    
            
			// TODO: subscribe to store on change event
            this.onStateChange = this.handleStateChange.bind(this);
            
			// TODO: add click event            
		}
        
        handleStateChange (newState) {
			this.innerHTML = this.render();
            console.log("binded");
		}
        
		connectedCallback () {
                    
            let generator = new Generator(Object.assign({}, this.store.state.generators[this.dataset.id]));

            this.innerHTML = this.render(generator);
            
            this.store.subscribe(this.onStateChange);
            
            this.addEventListener('click', () => {
                this.store.dispatch({
                    type: constants.actions.BUY_GENERATOR,
                    payload: {
                        name: generator.name
                    }
                });
            });
            console.log("  gen connected  ");           
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
            console.log("disconnected");
            this.removeEventListener('click', this.onClickEvent);
		}
        
        
        render(generator) {
            return `
                <div class="generators">
          
                    <div class="top_row">
                        <label>${generator.name}</label>
                        <label class="quantity">${generator.quantity}</label>
                    </div>
          
                    <p class="description">${generator.description}
                    </p>
          
                    <div class="btm_row">
                        <label class="rate">Rate: ${generator.rate}</label>
                        <label class="price">Price: ${generator.getCost()}</label>
                    </div>
          
                </div>`;
        }
        
	};
}
