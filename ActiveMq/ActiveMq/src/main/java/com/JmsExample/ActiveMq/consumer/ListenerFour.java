package com.JmsExample.ActiveMq.consumer;

import com.JmsExample.ActiveMq.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class ListenerFour {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @JmsListener(destination = "second", containerFactory = "jmsListenerContainerFactory",
            subscription = "Listener 4")
    public void receiveMessage(SystemMessage SystemMessage) {
        LOGGER.info("I am ListenerFour Received message ='{}'", SystemMessage);
    }
}
