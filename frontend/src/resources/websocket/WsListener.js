export default class WsListener{

    constructor(mainPath) {
        this.url = `ws://${window.location.host}${mainPath}/endpoint`;
        this.ws = new WebSocket(this.url);
    }
}