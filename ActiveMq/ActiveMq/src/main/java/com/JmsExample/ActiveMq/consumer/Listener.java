package com.JmsExample.ActiveMq.consumer;

import com.JmsExample.ActiveMq.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;



@Component
public class Listener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @JmsListener(destination = "first", selector = "source='System A'")
    public void receiveMessage(SystemMessage SystemMessage) {
        LOGGER.info("Received message='{}'", SystemMessage);
    }
}
