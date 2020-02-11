package nu.te4.fullstack_slutprojekt.beans;

import javax.ejb.Stateless;
import nu.te4.fullstack_slutprojekt.entities.Credentials;

/**
 *
 * @author erikh
 */
@Stateless
public class AuthenticationBean {

    public int register(Credentials credentials) {
        return 0;
    }

    public boolean verify(String token) {
        return false;
    }
}
