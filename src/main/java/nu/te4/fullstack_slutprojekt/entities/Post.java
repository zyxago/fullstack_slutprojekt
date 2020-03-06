/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.entities;

/**
 * @author erikh
 */
public abstract class Post {
    protected int id;
    protected int likes;
    protected int reports;
    protected int writerId;

    public Post(int id, int likes, int reports, int writerId) {
        this.id = id;
        this.likes = likes;
        this.reports = reports;
        this.writerId = writerId;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
}
