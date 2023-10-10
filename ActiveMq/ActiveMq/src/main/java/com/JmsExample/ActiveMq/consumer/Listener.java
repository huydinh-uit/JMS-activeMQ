package com.JmsExample.ActiveMq.consumer;

import com.JmsExample.ActiveMq.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;



@Component
public class Listener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @JmsListener(destination = "second",
            containerFactory = "jmsListenerContainerFactory",
            subscription = "Listener 0",
            selector = "source='System C'")
    public void receiveMessage(SystemMessage systemMessage,
                               @Header("JMSMessageID") String messageID,
                               @Header("JMSXGroupID") String jmxGroupId,
                               @Header("JMSXGroupSeq") String jmxGroupSeq,
                               @Header("JMSXDeliveryCount") String redeliveryCount,
                               @Header("JMSDestination") String destination) {
        try {
            String msg = systemMessage.getMessage();
            LOGGER.info("Receiving message {} from queue {} [RedeliveryCount={}, MessageID={}, JMSXGroupID={}]",
                    msg, destination, redeliveryCount, messageID, jmxGroupId);

            if (systemMessage != null && msg.startsWith("error-")) {
                throw new Exception("Forcing reading jms message problems for message read in queue " + destination);
            }
        } catch (Exception e) {
            LOGGER.error("Problems for consuming messagem from queue {}.", destination);
            throw new RuntimeException(e.getMessage());
        }
    }
}
