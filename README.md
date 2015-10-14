# socky
Socky - Simple websocket server (no headache)

How to use?

Run WebSocketServerHandler class  (has a simple main method)

This will start the websocket server. In order to test the server you can use http://www.websocket.org/echo.html

When you start the server, you will be able to see the host and port. Use those details in the location input on websocket.org.  Example: 

INFO: Web Socket Server Stopped on port: 7,788 and host: http://iulian.local:7788/

Being a websocket connection you will have to use ws instead of http.

The websocket server handler has also a small interactive shell that allows you to stop the server(stop-server), close the last connection (close-conn) or send message to the last connected client (send-msg <message>). 

Very basic for now... more stuff to come!
