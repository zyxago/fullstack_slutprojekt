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

    @POST
    @Path("register/oauth")
    public Response oauthRegister(@HeaderParam("Authorization") String code) {
        if (authenticationBean.registerOauth(code) < 0) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("login/oauth")
    public Response oauthLogin(@HeaderParam("Authorization") String code) {
        String token = authenticationBean.getToken(code);
        if (token != "") {
            return Response.status(Response.Status.OK).build();
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
