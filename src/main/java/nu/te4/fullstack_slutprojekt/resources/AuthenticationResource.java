package nu.te4.fullstack_slutprojekt.resources;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.fullstack_slutprojekt.beans.AuthenticationBean;
import nu.te4.fullstack_slutprojekt.entities.Credentials;

/**
 *
 * @author erikh
 */
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @EJB
    AuthenticationBean authenticationBean;

    @POST
    @Path("register")
    public Response register(Credentials credentials) {
        return null;
    }

    @GET
    @Path("register")
    public Response verify(String token) {
        return null;
    }
}
