package com.JmsExample.ActiveMq.Producer;

import com.JmsExample.ActiveMq.model.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {
    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/sendMessageToQueue")
    public ResponseEntity<String> sendMessageToQueue(@RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("first", systemMessage);
            return ResponseEntity.ok("Message sent");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
    @PostMapping("/sendMessageToTopic")
    public ResponseEntity<String> sendMessageToTopic(@RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("second", systemMessage, m -> {
                m.setStringProperty("source", systemMessage.getSource());
                return m;
            });
            return ResponseEntity.ok("Message sent");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
}
