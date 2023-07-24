package com.example.restclient.serivce;

import com.example.restclient.model.Message1;
import com.example.restclient.repository.MessageRepository;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service implements MessageService {
    private MessageRepository messageRepository;

    @Override
    public List<Message1> getMessage(String msg){

        if (msg == null) {
            return messageRepository.findAll();
        }else {
            return messageRepository.findMessageByMsg(msg);
        }
    }
    @Override
    public Message1 createMessage(Message1 newMsg) {
        newMsg.setCreateDate(new Date());
        return messageRepository.save(newMsg);
    }

    @Override
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message1 getMsgById(int id) {
        return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found !! "));
    }

    @Override
    public void updateMessage(int id, Message1 newMsg) {
        Message1 oldMsg = getMsgById(id);
        oldMsg.setMsg(newMsg.getMsg());
        oldMsg.setCreateDate(new Date());
        messageRepository.save(newMsg);
    }

}
