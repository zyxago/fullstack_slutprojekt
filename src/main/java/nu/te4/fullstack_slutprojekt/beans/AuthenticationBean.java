package nu.te4.fullstack_slutprojekt.beans;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.security.SecureRandom;
import java.util.Base64;

import at.favre.lib.crypto.bcrypt.BCrypt;
import nu.te4.fullstack_slutprojekt.ConnectionFactory;
import nu.te4.fullstack_slutprojekt.entities.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author erikh
 */
@Stateless
public class AuthenticationBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationBean.class);
    private final String CLIENT_ID = "3ddec322c9443ab7553b";
    private final String CLIENT_SECRET = "76bc5b865370fef03cea019024729e5d12908d66";

    public int registerOauth(String code) {
        String token = getToken(code);
        Credentials cred = new Credentials();
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.github.com/user?access_token=" + token);
            JsonObject jsonData = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
            int id = jsonData.getInt("id");
            String name = jsonData.getString("name");
            cred.setOauthId(id);
            cred.setUsername(name);
            cred.setToken(token);
            saveCred(cred);
        return 0;
    }

    public String getToken(String code) {
        String url = String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s", CLIENT_ID, CLIENT_SECRET, code);
        Client client = ClientBuilder.newClient();
        String result = client.target(url).request().post(null, String.class);
        return result.substring(13, result.indexOf("&"));
    }

    public boolean verify(String token) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.github.com/user?access_token=" + token);
        return target.request(MediaType.APPLICATION_JSON).get().getStatus() == 200;
    }

    public int saveCred(Credentials cred) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user VALUES(null, ?, ?, ?, ?)");
            stmt.setString(1, cred.getUsername());
            stmt.setString(2, BCrypt.withDefaults().hashToString(10, cred.getPassword().toCharArray()));
            stmt.setInt(3, cred.getOauthId());
            stmt.setString(4, cred.getToken());
            return stmt.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    private int updateToken(String token, Credentials cred) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET token = ? WHERE name = ?");
            stmt.setString(1, token);
            stmt.setString(2, cred.getUsername());
            return stmt.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean compareHash(String pass, String hash) {
        return BCrypt.verifyer().verify(pass.toCharArray(), hash).verified;
    }

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    /**
     * Generates a token for future authentication
     *
     * @return
     */
    public static String generateNewToken() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public Credentials createCredentials(String basicAuth) {
        try {
            basicAuth = basicAuth.substring(6).trim();
            byte[] bytes = Base64.getDecoder().decode(basicAuth);
            basicAuth = new String(bytes);
            int colon = basicAuth.indexOf(":");
            String username = basicAuth.substring(0, colon);
            String password = basicAuth.substring(colon + 1);
            return new Credentials(username, password);
        } catch (Exception e) {
            LOGGER.error("Error in AuthenticationBean.createCredentials: " + e.getMessage());
        }
        return null;
    }
}
