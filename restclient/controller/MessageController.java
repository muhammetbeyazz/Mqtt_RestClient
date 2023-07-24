package com.example.restclient.controller;

import com.example.restclient.model.Message1;
import com.example.restclient.serivce.MessageService;
import com.example.restclient.serivce.MqttService;
import com.example.restclient.serivce.MqttService2;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/msg")
@AllArgsConstructor
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MqttService mqttService;
    @Autowired
    private MqttService2 mqttService2;

    @GetMapping
    public ResponseEntity<List<Message1>> getMessage(@RequestParam String msg) {
        return new ResponseEntity<>(messageService.getMessage(msg), OK);
    }

    @PostMapping
    public ResponseEntity<Message1> createMessage(@RequestBody Message1 newMsg) {
        try {
            mqttService.sendMessage(newMsg.getTopicName(), newMsg.getMsg());
            mqttService2.sendMessage(newMsg.getTopicName(), newMsg.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(messageService.createMessage(newMsg), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putMessage(@PathVariable int id, @RequestBody Message1 newMsg) {
        messageService.updateMessage(id, newMsg);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
        return new ResponseEntity<>(OK);
    }

    private Message1 getMsgById(int id) {
        return messageService.getMsgById(id);

    }
}
