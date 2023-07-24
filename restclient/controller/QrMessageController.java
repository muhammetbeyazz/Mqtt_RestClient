package com.example.restclient.controller;

import com.example.restclient.model.Qr;
import com.example.restclient.serivce.MqttService;
import com.example.restclient.serivce.MqttService2;
import com.example.restclient.serivce.QrMessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/qr")
@AllArgsConstructor
public class QrMessageController {

    @Autowired
    private QrMessageService qrMessageService;
    @Autowired
    private MqttService mqttService;
    @Autowired
    private MqttService2 mqttService2;

    @GetMapping
    public ResponseEntity<List<Qr>> getQrMessage(@RequestParam String qrMsg) {
        return new ResponseEntity<>(qrMessageService.getQrMessage(qrMsg), OK);
    }

    @PostMapping
    public ResponseEntity<Qr> createQrMessage(@RequestBody Qr newQrMsg) {
        try {
            mqttService.sendMessage(newQrMsg.getTopicName(), newQrMsg.getQrMsg());
            mqttService2.sendMessage(newQrMsg.getTopicName(), newQrMsg.getQrMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(qrMessageService.createQrMessage(newQrMsg), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putQrMessage(@PathVariable int qrId, @RequestBody Qr newQrMsg) {
        qrMessageService.updateQrMessage(qrId, newQrMsg);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQrMessage(@PathVariable int qrId) {
        qrMessageService.deleteQrMessage(qrId);
        return new ResponseEntity<>(OK);
    }

    private Qr getQrMsgById(int qrId) {
        return qrMessageService.getQrMsgById(qrId);
    }



}
