package com.example.restclient.serivce;

import com.example.restclient.model.Message1;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MessageService {
    public List<Message1> getMessage(String msg);
    public Message1 createMessage(Message1 newMsg);
    public void deleteMessage(int id);
    public void updateMessage(int id, Message1 newMsg);
    public Message1 getMsgById(int id);

}
