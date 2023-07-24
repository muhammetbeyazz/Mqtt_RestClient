package com.example.restclient.serivce;

import com.example.restclient.model.Message1;
import com.example.restclient.model.Qr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QrMessageService {
    public List<Qr> getQrMessage(String qrMsg);
    public Qr createQrMessage(Qr newQrMsg);
    public void deleteQrMessage(int qrId);
    public void updateQrMessage(int qrId, Qr newQrMsg);
    public Qr getQrMsgById(int qrId);

}
