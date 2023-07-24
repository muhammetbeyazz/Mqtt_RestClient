package com.example.restclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data                   //Lombook ile getter ve setterları ekledik bu annatation sayesinde
@Table(name = "qr")
public class Qr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "qrId")
    private int qrId;

    @Column(name = "qrMessage")
    private String qrMsg;

    @Column(name = "qrName")
    private String qrName;

    @Column(name = "topicName")
    private String topicName;

    @Column(name = "xCordinate")
    private Double xCordinate;

    @Column(name = "yCordinate")
    private Double yCordinate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "msg_createDate")
    private Date createDate;
}
