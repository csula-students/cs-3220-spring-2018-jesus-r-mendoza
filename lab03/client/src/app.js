import '@webcomponents/webcomponentsjs';

import {loop} from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';

/**
 * Data flow diagram
 +----------------------------------------------------+
 | +------------------+          +------------------+ |
 | |                  |          |                  | |
++-|       Loop       |<---------|    Generator     | |
|| |                  |          |                  | |
|| +------------------+          +------------------+ |
||G          ^                                        |
||a          +-----------------------------+          |
||m                                        |          |
||e                                        |          |
||                               +------------------+ |
||                               |                  | |
||                               |     Stories      | |
||                               |                  | |
||                               +------------------+ |
|+----------------------------------------------------+
+------------------------------------------------------------+
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|       +----------------------------------------------------+----------+
|       | +------------------+                     +------------------+ |
|       | |                  |        Mutates      |                  | |
|       | |     Reducer      |-------------------->|      State       | |
|       | |                  |                     |                  | |
|       | +------------------+                     +------------------+ |
|       |S          ^                                        |          |
|       |t          |                                        |          |
|       |o          |                                        |          |
|       |r          | Triggers                     Notifies  |          |
|       |e          |                                        |          |
|       |           |                                        v          |
|       | +------------------+                     +------------------+ |
|       | |                  |                     |                  | |
+-------+>|      Action      |                     |    Listeners     | |
        | |                  |                     |                  | |
        | +------------------+                     +------------------+ |
        +-----------^----------------------------------------+----------+
                    |                                        |
                    |                                        |
                    |                                        |
                    |                                        |
                    | Dispatches                             |
                    |                                        |
                    |                                        |
          +------------------+                               |
          |                  |                               |
          |      Views       |              Notifies changes |
          |    Components    |<------------------------------+
          |                  |
          +------------------+
 */
main();

// main function wraps everything at top level
function main () {
	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		example: 'Hello custom element',
		counter: 0,
		generators: [
            {
                type: 'gen',
                name: 'Cursor',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 10,
                unlockValue: 10
            },
            {
                type: 'gen',
                name: 'Hands',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 100,
                unlockValue: 100
            }, 
            {
                type: 'gen',
                name: 'Sneezes',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 500,
                unlockValue: 500
            },
            {
                type: 'gen',
                name: 'People',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 7500,
                unlockValue: 7500
            },
            {
                type: 'gen',
                name: 'Cities',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 50000,
                unlockValue: 50000
            },
            {
                type: 'gen',
                name: 'States',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 500000,
                unlockValue: 500000
            },
            {
                type: 'gen',
                name: 'Countries',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 25000000,
                unlockValue: 25000000
            },
            {
                type: 'gen',
                name: 'Continents',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 1000000000,
                unlockValue: 1000000000
            },
            {
                type: 'gen',
                name: 'Planets',
                description: 'To be written at a later date..',
                rate: 5,
                quantity: 0,
                baseCost: 100000000000,
                unlockValue: 100000000000
            }
            
	   ], 
        story: []
    };

	// initialize store
	const store = new Store(reducer, initialState);
	console.log(ExampleComponent(store));

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	// start game loop
	loop(store);
}
