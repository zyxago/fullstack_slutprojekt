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

import java.util.List;

/**
 * @author erikh
 */
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @EJB
    CommentBean commentBean;

    @GET
    @Path("/comments/{parentId}")
    public Response getComments(@PathParam("parentId") int parentId) {
        List<Comment> comments = commentBean.getComments(parentId);
        if (!comments.isEmpty()) {
            return Response.status(Response.Status.OK).entity(comments).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/comment")
    public Response addComment(Comment comment) {
        if (commentBean.addComment(comment) > 0) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/comment/report/{id}/{userId}")
    public Response reportComment(@PathParam("id") int id, @PathParam("userId") int userId) {
        if (commentBean.reportComment(id, userId) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/comment/like/{id}/{userId}")
    public Response likeComment(@PathParam("id") int id, @PathParam("userId") int userId) {
        if (commentBean.likeComment(id, userId) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/comment")
    public Response modifyComment(Comment comment) {
        if (commentBean.modifyComment(comment) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/comment/{id}")
    public Response removeComment(@PathParam("id") int id) {
        if (commentBean.removeComment(id) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
