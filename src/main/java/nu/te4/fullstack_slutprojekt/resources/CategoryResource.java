package nu.te4.fullstack_slutprojekt.resources;

import nu.te4.fullstack_slutprojekt.beans.CategoryBean;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    @EJB
    CategoryBean categoryBean;

    @GET
    @Path("/categories")
    public Response getIngredients(){
        List<String> categories = categoryBean.getCategories();
        if(!categories.isEmpty()){
            return Response.status(Response.Status.OK).entity(categories).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
