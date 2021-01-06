/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.websocketpractise.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @author siux
 */
public class EchoHandler extends TextWebSocketHandler {

    private List<WebSocketSession> clients = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //printing connection details
        System.out.println("Accepted protocol: " + session.getAcceptedProtocol());
        System.out.println("Is open: " + session.isOpen());
        System.out.println("Principal: " + session.getPrincipal());
        System.out.println("Local address: " + session.getLocalAddress());
        System.out.println("Text message size limit: " + session.getTextMessageSizeLimit());
        System.out.println("URI: " + session.getUri());
        
        clients.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clients.remove(session);
    }
    
    
    
    @Override
    protected void handleTextMessage(WebSocketSession session, final TextMessage message) throws Exception {
        clients.forEach((WebSocketSession t) -> {
            try {
                t.sendMessage(new TextMessage(message.getPayload()));
            } catch (IOException ex) {
                Logger.getLogger(EchoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
//        session.sendMessage(new TextMessage(message.getPayload()));
    }
      
}
