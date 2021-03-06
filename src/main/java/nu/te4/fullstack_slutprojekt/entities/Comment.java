/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.entities;

/**
 *
 * @author erikh
 */
public class Comment extends Post{
    private int parentId;
    private String username;
    private String text;

    public Comment(CommentBuilder builder){
        super(builder.getId(), builder.getLikes(), builder.getRepports(), builder.getWriterId());
        this.parentId = builder.getParentId();
        this.text = builder.getText();
        this.username = builder.getUsername();
    }

    public Comment(){}

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}