package com.example.restclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data                   //Lombook ile getter ve setterları ekledik bu annatation sayesinde
@Table(name = "msg")
public class Message1{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int id;

    @Column(name = "message")
    private String msg;

    @Column(name = "topicName")
    private String topicName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "msg_createDate")
    private Date createDate;
}
