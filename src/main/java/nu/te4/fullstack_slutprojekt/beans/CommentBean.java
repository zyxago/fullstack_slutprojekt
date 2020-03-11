package nu.te4.fullstack_slutprojekt.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

import nu.te4.fullstack_slutprojekt.ConnectionFactory;
import nu.te4.fullstack_slutprojekt.entities.Comment;
import nu.te4.fullstack_slutprojekt.entities.CommentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author erikh
 */
@Stateless
public class CommentBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentBean.class);

    public List<Comment> getComments(int recipeId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comments_full WHERE parent_id = ?");
            stmt.setInt(1, recipeId);
            ResultSet data = stmt.executeQuery();
            while (data.next()) {
                CommentBuilder builder = new CommentBuilder()
                        .setUsername(data.getString("username"))
                        .setLikes(data.getInt("likes"))
                        .setRepports(data.getInt("reports"))
                        .setWriterId(data.getInt("writer_id"))
                        .setText(data.getString("text"))
                        .setId(data.getInt("id"))
                        .setParentId(data.getInt("parent_id"));
                comments.add(builder.build());
            }
        } catch (Exception e) {
            LOGGER.error("Error in CommentBean.getComments: " + e.getMessage());
        }
        return comments;
    }

    public int addComment(Comment comment) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO comment VALUES(null, ?, ?, ?)");
            stmt.setString(1, comment.getText());
            stmt.setInt(2, comment.getParentId());
            stmt.setInt(3, comment.getWriterId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in CommentBean.addComment: " + e.getMessage());
            return 0;
        }
    }

    public int modifyComment(Comment comment) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE comment SET text = ? WHERE id = ?");
            stmt.setString(1, comment.getText());
            stmt.setInt(2, comment.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in CommentBean.modifyComment: " + e.getMessage());
            return 0;
        }
    }

    public int likeComment(int id, int userId) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_like VALUES(?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, id);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in CommentBean.likeComment: " + e.getMessage());
            return 0;
        }
    }

    public int reportComment(int id, int userId) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_report VALUES(?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, id);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in CommentBean.reportComment: " + e.getMessage());
            return 0;
        }
    }

    public int removeComment(int id) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM comment WHERE id = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in CommentBean.removeComment: " + e.getMessage());
            return 0;
        }
    }
}
