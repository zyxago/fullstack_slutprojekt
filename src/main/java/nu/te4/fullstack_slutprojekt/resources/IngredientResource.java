package nu.te4.fullstack_slutprojekt.resources;

import nu.te4.fullstack_slutprojekt.beans.IngredientBean;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class IngredientResource {
    @EJB
    IngredientBean ingredientBean;

    @GET
    @Path("/ingredients")
    public Response getIngredients(){
        List<String> ingredients = ingredientBean.getIngredients();
        if(!ingredients.isEmpty()){
            return Response.status(Response.Status.OK).entity(ingredients).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/ingredients/measurement")
    public Response getMeasurement(){
        List<String> measurements = ingredientBean.getMeasurement();
        if(!measurements.isEmpty()){
            return Response.status(Response.Status.OK).entity(measurements).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
