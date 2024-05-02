package com.javatechie.service;

import com.javatechie.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message){
        for(int i = 0 ;i<10000;i++) {
            CompletableFuture<SendResult<String, Object>> future = template.send("javatechie-demo-1", message+i);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("sent a message " + message + " with offset " + result.getRecordMetadata().offset());
                } else {
                    System.out.println("failed to send message " + message + " with exception " + ex.getMessage());
                }
            });
        }
    }

    public void sendCustomerToTopic(Customer customer){
            CompletableFuture<SendResult<String, Object>> future = template.send("javatechie-demo-1", customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("sent a message " + customer.toString() + " with offset " + result.getRecordMetadata().offset());
                } else {
                    System.out.println("failed to send message " + customer.toString() + " with exception " + ex.getMessage());
                }
            });
    }
}
