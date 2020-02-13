package nu.te4.fullstack_slutprojekt.websocket;

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

    @OnMessage
    public void onMessage(Session user, String message) {

    }

    @OnOpen
    public void onOpen(Session user) {
        SESSIONS.add(user);
    }

    @OnClose
    public void onClose(Session user) {
        SESSIONS.remove(user);
    }

}
