window.incrementalGame = {
    state: {
        counter: 0
    }
};

// PubSub is single object for publish data to multiple subscribers
class PubSub {
    constructor () {
        this.subscribers = [];
    }

    // subscribe allows a new subscriber to listen for changes by providing
    // callback function in the parameter
    subscribe (fn) {
        this.subscribers.push(fn);
    }

    // one can publish any data to subscribers
    publish (data) {
        this.subscribers.forEach(subscriber => {
            subscriber(data);
        });
    }
}

const pubSub = new PubSub();
const resCount = document.querySelector('#count');
const btn = document.querySelector('button');

btn.addEventListener('click', () => {
    pubSub.publish(window.incrementalGame.state.counter++);
});

pubSub.subscribe(action => {
    resCount.innerHTML = window.incrementalGame.state.counter;
});