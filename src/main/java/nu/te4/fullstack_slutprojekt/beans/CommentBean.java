package nu.te4.fullstack_slutprojekt.beans;

import java.util.List;
import javax.ejb.Stateless;
import nu.te4.fullstack_slutprojekt.entities.Comment;

/**
 *
 * @author erikh
 */
@Stateless
public class CommentBean {
    
        public List<Comment> getComments(){
        return null;
    }
    
    public int addComment(Comment comment){
        return 0;
    }
    
    public int modifyComment(int id){
        return 0;
    }
    
    public int removeComment(int id){
        return 0;
    }
    
}
