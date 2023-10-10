package com.JmsExample.ActiveMq.consumer;

import com.JmsExample.ActiveMq.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class ListenerTwo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @JmsListener(destination = "second", containerFactory = "jmsListenerContainerFactory",
            subscription = "Listener 2")
    public void receiveMessage(SystemMessage SystemMessage) {
        LOGGER.info("I am ListenerTwo Received message ='{}'", SystemMessage);
    }
}
