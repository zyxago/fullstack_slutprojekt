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

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

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
    public Response getRecipes() {
        List<Recipe> recipes = recipeBean.getRecipes();
        if (!recipes.isEmpty()) {
            return Response.status(Response.Status.OK).entity(recipes).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/recipe/{id}/img")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getRecipeImg(@PathParam("id") int id) {
        InputStream image = recipeBean.getImage(id);
        if (image != null) {
            return Response.status(Response.Status.OK).entity(image).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/recipe/{id}/img")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response addImgToRecipe(@PathParam("id") int id, InputStream image) {
        if (recipeBean.addImage(id, image) > 0) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/recipe")
    public Response addRecipe(Recipe recipe) {
        if (recipeBean.addRecipe(recipe) > 0) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/recipe/repport/{id}")
    public Response repportRecipe(@PathParam("id") int id) {
        if (recipeBean.repportRecipe(id) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/recipe/like/{id}")
    public Response likeRecipe(@PathParam("id") int id) {
        if (recipeBean.likeRecipe(id) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/recipe/{id}")
    public Response modifyRecipe(@PathParam("id") int id, Recipe recipe) {
        if (recipeBean.modifyRecipe(id, recipe) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/recipe/{id}")
    public Response removeRecipe(@PathParam("id") int id) {
        if (recipeBean.removeRecipe(id) > 0) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
