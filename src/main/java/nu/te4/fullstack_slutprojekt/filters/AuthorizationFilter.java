/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.filters;

import nu.te4.fullstack_slutprojekt.beans.AuthenticationBean;

import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    @EJB
    AuthenticationBean authenticationBean;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!requestContext.getMethod().equals("GET")) {
            if (!requestContext.getHeaders().containsKey("authorization") || !authenticationBean.verify(requestContext.getHeaders().getFirst("authorization"))) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }

}
