package org.spauny.joy.socky;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

/**
 *
 * @author iulian.dafinoiu
 */
public class WebbitWebsocketServer extends BaseWebSocketHandler implements Serializable {

    private WebSocketConnection currentConnection;
    
    @Override
    public void onOpen(WebSocketConnection connection) throws Exception {
        super.onOpen(connection); 
        currentConnection = connection; 
        Logger.getLogger(WebbitWebsocketServer.class.getName()).log(Level.INFO, "++++++++++ Connection Opened.");
    }

    @Override
    public void onClose(WebSocketConnection connection) throws Exception {
        super.onClose(connection);
        Logger.getLogger(WebbitWebsocketServer.class.getName()).log(Level.INFO, "++++++++++ Connection Closed.");
    }

    @Override
    public void onMessage(WebSocketConnection connection, String msg) throws Throwable {
        super.onMessage(connection, msg);
        Logger.getLogger(WebbitWebsocketServer.class.getName()).log(Level.INFO, "++++++++++ Message: {0}", msg);
    }
    
    public void closeConnection() {
        if (this.currentConnection != null) {
            this.currentConnection.close();
            this.currentConnection = null;
        }
    }

    public WebSocketConnection getCurrentConnection() {
        return currentConnection;
    }

}
