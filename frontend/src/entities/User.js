export default class User {
    constructor(data) {
        this.name = data.username;
        this.token = data.token;
        this.id = data.id;
    }
}