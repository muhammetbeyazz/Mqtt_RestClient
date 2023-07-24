package com.example.restclient.serivce;

import com.example.restclient.model.Message1;
import com.example.restclient.model.Qr;
import com.example.restclient.repository.MessageRepository;
import com.example.restclient.repository.QrMessageRepository;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MqttService2 implements MqttCallback {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private QrMessageRepository qrMessageRepository;
    MqttClient client1 = null;
    @Autowired
    private QrService qrService;
    private Message1 msg = new Message1();
    private Qr qrMsg = new Qr();


    @Override
    public void connectionLost(Throwable throwable) {

    }
    //Gelen mesajları dinler
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String mqttMsg = mqttMessage.toString();
        msg.setMsg(mqttMsg);

        qrMsg.setQrMsg(mqttMsg);


        System.err.println(msg);
        System.err.println(qrMsg);

        qrService.splitQrMessage(String.valueOf(qrMsg.getQrMsg()));



        msg.setCreateDate(new Date());
        messageRepository.save(msg);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    public void sendMessage(String topicName, String message)throws Exception{
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(1);

        client1.publish(topicName,mqttMessage);
    }

    public void start(){

        MemoryPersistence persistence = new MemoryPersistence();


        try {
            client1 =new MqttClient("tcp://localhost:1883",MqttClient.generateClientId(),persistence);
            client1.setCallback(this);

        }catch (Exception e){
            System.err.println("Client 1 bağlanamadı cunku"+e.getMessage());
        }
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setMaxInflight(3000);
        connectOptions.setAutomaticReconnect(true);


        try {

            IMqttToken mqttConnectionToken = client1.connectWithResult(connectOptions);
            client1.subscribe("topic2");

        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
