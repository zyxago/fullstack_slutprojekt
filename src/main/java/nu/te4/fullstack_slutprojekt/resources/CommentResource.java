package nu.te4.fullstack_slutprojekt.resources;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.fullstack_slutprojekt.beans.CommentBean;
import nu.te4.fullstack_slutprojekt.entities.Comment;

/**
 *
 * @author erikh
 */
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @EJB
    CommentBean commentBean;

    @GET
    @Path("/comments")
    public Response getComments() {
        return null;
    }

    @POST
    @Path("/comment")
    public Response addComment(Comment comment) {
        return null;
    }

    @PUT
    @Path("/comment/{id}")
    public Response modifyComment(@PathParam("id") int id) {
        return null;
    }

    @DELETE
    @Path("/comment/{id}")
    public Response removeComment(@PathParam("id") int id) {
        return null;
    }
}
