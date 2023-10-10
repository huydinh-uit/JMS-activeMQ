package com.JmsExample.ActiveMq.Producer.service;

import com.JmsExample.ActiveMq.consumer.Listener;
import com.JmsExample.ActiveMq.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessageToQueue(SystemMessage systemMessage) {
        for (int i = 0; i < 10; i++) {
            try {
                jmsTemplate.convertAndSend("second", systemMessage, m -> {
                    m.setStringProperty("source", systemMessage.getSource());
                    m.setStringProperty("JMSXGroupID", systemMessage.getSource());
                    return m;
                });
                LOGGER.info("Sending Message '{}'", i);
            }
            catch (Exception e) {
                LOGGER.error("Error while sending a message to the queue {}", e.getMessage());
            }
    }
}
}
