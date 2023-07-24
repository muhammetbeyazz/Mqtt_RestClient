package com.example.restclient.repository;

import com.example.restclient.model.Message1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message1, Integer> {
    List<Message1> findMessageByMsg(String msg);
}
