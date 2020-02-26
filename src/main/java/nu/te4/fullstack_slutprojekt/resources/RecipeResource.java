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

import nu.te4.fullstack_slutprojekt.beans.RecipeBean;
import nu.te4.fullstack_slutprojekt.entities.Recipe;

/**
 * @author erikh
 */
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeResource {

    @EJB
    RecipeBean recipeBean;

    @GET
    @Path("/recipes")
    public Response getRecepies() {
        return null;
    }

    @POST
    @Path("/recipe")
    public Response addRecipe(Recipe recipe) {
        return null;
    }

    @POST
    @Path("/recipe/repport/{id}")
    public Response repportRecipe(@PathParam("id") int id) {
        return null;
    }

    @POST
    @Path("/recipe/like/{id}")
    public Response likeRecipe(@PathParam("id") int id) {
        return null;
    }

    @PUT
    @Path("/recipe/{id}")
    public Response modifyRecipe(@PathParam("id") int id, Recipe recipe) {
        return null;
    }

    @DELETE
    @Path("/recipe/{id}")
    public Response removeRecipe(@PathParam("id") int id) {
        return null;
    }
}
