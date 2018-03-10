import Generator from '../models/generator';

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
            this.addEventListener('click', () => {
                payload: {
                    name: generator.name                    
                }
            });
            
		}
        
        handleStateChange (newState) {
			this.textContent = newState.example;
            console.log("binded");
		}
        
		connectedCallback () {
            
            this.id = this.dataset.id;
			const generator = new Generator(Object.assign({}, this.store.state.generators[this.id]));
            
            this.innerHTML = `
                <div class="generators">
          
                    <div class="top_row">
                        <label>Cursor</label>
                        <label class="quantity">amt</label>
                    </div>
          
                    <p class="description">Description... 
                        dolor sit amet, consectetur adipiscing elit. Maecenas congue, 
                        mauris quis mollis cursus, felis tellus ultricies nunc, eu sodales 
                        dolor urna quis augue
                    </p>
          
                    <div class="btm_row">
                        <label class="rate">rate</label>
                        <label class="price">Price</label>
                    </div>
          
                </div>`;
            this.store.subscribe(this.onStateChange);
            console.log("  gen connected  ");
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
            console.log("disconnected");
            this.removeEventListener('click', this.onClickEvent);
		}
        
	};
}
