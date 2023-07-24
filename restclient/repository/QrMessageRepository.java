package com.example.restclient.repository;

import com.example.restclient.model.Qr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QrMessageRepository extends JpaRepository<Qr, Integer> {

    List<Qr> findQrByQrMsg(String qrMsg);

}
