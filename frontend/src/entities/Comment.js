export default class Comment{
    constructor(data) {
        this.id = data.id;
        this.writerId = data.writerId;
        this.username = data.username;
        this.text = data.text;
        this.parentId = data.parentId;
        this.likes = data.likes;
        this.reports = data.reports;
    }
}