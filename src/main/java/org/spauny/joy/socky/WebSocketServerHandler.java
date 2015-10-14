package org.spauny.joy.socky;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;

/**
 *
 * @author iulian.dafinoiu
 */
public class WebSocketServerHandler {

    public static final int SOCKY_DEFAULT_PORT = 7788;
    
    private WebbitWebsocketServer currentWebbitServer;
    private WebServer currentWebServer;
    
    public static void main (String[] args) {
        WebSocketServerHandler serverHandler = new WebSocketServerHandler();
        serverHandler.startServer();
        
        System.out.println("Welcome to socky interactive shell! You can use: stop-server, close-conn, send-msg <message>  and exit");
        
        Scanner simpleScanner = new Scanner(System.in);
        String command = simpleScanner.nextLine();
        
        while (!command.trim().equalsIgnoreCase("exit")) {
            System.out.println("Your command is: " + command);
            switch (command) {
                case "stop-server":
                    serverHandler.stopServer();
                    break;
                case "close-conn":
                    serverHandler.getCurrentWebbitServer().closeConnection();
                    break;
            }
            if (command.contains("send-msg") && command.length() < 10) {
                System.out.println("Please send a proper message. You don't want to be rude");
            } else if (command.contains("send-msg")) {
                serverHandler.getCurrentWebbitServer().getCurrentConnection().send(command.substring(command.indexOf("send-msg") + 9));
            }
            command = simpleScanner.nextLine();
        }
        
        serverHandler.stopServer();
    }

    public WebSocketServerHandler startServer() {
        return startServer("/", SOCKY_DEFAULT_PORT);
    }
    
    public WebSocketServerHandler startServer(String path, int port) {
        currentWebbitServer = new WebbitWebsocketServer();
        currentWebServer = WebServers.createWebServer(port).add(path, currentWebbitServer);
        currentWebServer.start();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Web Socket Server Started on port: {0} and host: {1}", new Object[]{currentWebServer.getPort(), currentWebServer.getUri()});
        return this;
    }

    public void stopServer() {
        if (currentWebbitServer != null) {
            currentWebbitServer.closeConnection();
        }
        if (currentWebServer != null) {
            currentWebServer.stop();
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Web Socket Server Stopped on port: {0} and host: {1}", new Object[]{currentWebServer.getPort(), currentWebServer.getUri()});
        }
    }

    public WebbitWebsocketServer getCurrentWebbitServer() {
        return currentWebbitServer;
    }

    public WebServer getCurrentWebServer() {
        return currentWebServer;
    }
    
}
