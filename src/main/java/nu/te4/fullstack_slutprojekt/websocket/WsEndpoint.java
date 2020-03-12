package nu.te4.fullstack_slutprojekt.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/endpoint")
public class WsEndpoint {

    private static final Set<Session> SESSIONS = new HashSet<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(WsEndpoint.class);

    public static void recipeUpdate() {
        try {
            for (Session session : SESSIONS) {
                session.getBasicRemote().sendText("recipeUpdate");
            }
        } catch (Exception e) {
            LOGGER.error("Error in WsEndpoint.recipeUpdate: " + e.getMessage());
        }
    }

        @OnMessage
        public void onMessage (Session user, String message){

        }

        @OnOpen
        public void onOpen (Session user){
            SESSIONS.add(user);
            LOGGER.info("New session established");
        }

        @OnClose
        public void onClose (Session user){
            SESSIONS.remove(user);
            LOGGER.info("A session was closed");
        }

    }
