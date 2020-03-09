package nu.te4.fullstack_slutprojekt.resources;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nu.te4.fullstack_slutprojekt.beans.AuthenticationBean;
import nu.te4.fullstack_slutprojekt.entities.Credentials;

/**
 * @author erikh
 */
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @EJB
    AuthenticationBean authenticationBean;

    @GET
    @Path("authenticate")
    public Response oauthRegister(@HeaderParam("Authorization") String code) {
        Credentials cred = authenticationBean.authenticate(code);
        if (!cred.getToken().isEmpty()) {
            return Response.status(Response.Status.OK).entity(cred).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("verify")
    public Response verify(@HeaderParam("Authorization") String token) {
        if (authenticationBean.verify(token)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
