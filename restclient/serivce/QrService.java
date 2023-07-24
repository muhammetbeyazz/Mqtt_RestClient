package com.example.restclient.serivce;

import com.example.restclient.model.Qr;
import com.example.restclient.repository.QrMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class QrService implements QrMessageService{
    @Autowired
    private QrMessageRepository qrMessageRepository;


    private String[] splitMsg;

    @Override
    public List<Qr> getQrMessage(String qrMsg) {
        if (qrMsg == null) {
            return qrMessageRepository.findAll();
        }else {
            return qrMessageRepository.findQrByQrMsg(qrMsg);
        }
    }

    @Override
    public Qr createQrMessage(Qr newQrMsg) {
        newQrMsg.setCreateDate(new Date());
        return qrMessageRepository.save(newQrMsg);
    }

    @Override
    public void deleteQrMessage(int qrId) {
        qrMessageRepository.deleteById(qrId);
    }

    @Override
    public void updateQrMessage(int qrId, Qr newQrMsg) {
        Qr oldMsg = getQrMsgById(qrId);
        oldMsg.setQrMsg(newQrMsg.getQrMsg());
        oldMsg.setCreateDate(new Date());
        qrMessageRepository.save(newQrMsg);    }

    @Override
    public Qr getQrMsgById(int qrId) {
        return qrMessageRepository.findById(qrId).orElseThrow(() -> new RuntimeException("User Not Found !! "));
    }

    public void splitQrMessage(String qrMsg){
        Qr qr = new Qr();
        splitMsg = qrMsg.split(";");

        qr.setQrName(splitMsg[0]);
        qr.setXCordinate(Double.valueOf(splitMsg[1]));
        qr.setYCordinate(Double.valueOf(splitMsg[2]));
        qr.setQrMsg(qrMsg);

        qr.setCreateDate(new Date());
        qrMessageRepository.save(qr);
    }

}
