export default class Comment{
    constructor(data) {
        this.writerId = data.writerId;
        this.username = data.username;
        this.text = data.text;
        this.parentId = data.parentId;
    }
}