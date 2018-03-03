import Genrator from '..models/generator'

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
            
            switch(this.dataset.id) {
                case 1:
                    this.meta = {
                        type: 'gen',
                        name: 'Cursor',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 2:
                    this.meta = {
                        type: 'gen',
                        name: 'Hands',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 3:
                    this.meta = {
                        type: 'gen',
                        name: 'Sneezes',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 4:
                    this.meta = {
                        type: 'gen',
                        name: 'People',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 5:
                    this.meta = {
                        type: 'gen',
                        name: 'Cities',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 6:
                    this.meta = {
                        type: 'gen',
                        name: 'States',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 7:
                    this.meta = {
                        type: 'gen',
                        name: 'Countries',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 8:
                    this.meta = {
                        type: 'gen',
                        name: 'Continents',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                case 9:
                    this.meta = {
                        type: 'gen',
                        name: 'Planets',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                default:
                    this.meta = {
                        type: 'gen',
                        name: 'Cursor',
                        description: 'To be written at a later date..',
                        rate: 10,
                        baseCost: 10,
                        quantity: 0,
                        unlockValue: 10
                    }
                    break;
                    
            }
            
            
            
			// TODO: render generator initial view
            this.generator = new Generator(this.meta);
            
            connectedCallBack() {
                this.innerHTML = `
                <div class="generators">

                    <div class="top_row">
                        <label>Continents</label>
                        <label class="quantity">amt</label>
                    </div>

                    <p class="description">Description...</p>

                    <div class="btm_row">
                        <label class="rate">rate</label>
                        <label class="price">Price</label>
                    </div>

                </div>   `
            }
            

			// TODO: subscribe to store on change event
            this.store.subscribe(action => {
                
            });

			// TODO: add click event
            this.addEventListener('click', () => {
                console.log("vlecked");
               this.store.dispatch({
                   type: 'BUY_GENERAOTR',
                   payload: 'You bought a generator'
               }); 
            });
            
            // const btn = document.querySelector(this.ty);
            
        }
        
	};
}
