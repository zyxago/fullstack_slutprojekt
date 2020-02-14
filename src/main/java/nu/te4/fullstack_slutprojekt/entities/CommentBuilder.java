package nu.te4.fullstack_slutprojekt.entities;

public class CommentBuilder {
    private int parentId;
    private String text;
    private int id;
    private int likes;
    private int repports;
    private int writerId;

    public CommentBuilder() {
    }

    public Comment build() throws IllegalStateException{
        if (getId() < 0) {
            setId(0);
        }
        if (getText() == null) {
            throw new IllegalStateException("Comment must contain text");
        }
        if (getParentId() < 0) {
            setParentId(0);
        }
        if (getWriterId() < 0) {
            setWriterId(0);
        }
        return new Comment(this);
    }

    public int getParentId() {
        return parentId;
    }

    public CommentBuilder setParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public int getId() {
        return id;
    }

    public CommentBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public CommentBuilder setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getRepports() {
        return repports;
    }

    public CommentBuilder setRepports(int repports) {
        this.repports = repports;
        return this;
    }

    public int getWriterId() {
        return writerId;
    }

    public CommentBuilder setWriterId(int writerId) {
        this.writerId = writerId;
        return this;
    }
}
