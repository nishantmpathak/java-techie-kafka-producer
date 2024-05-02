package com.javatechie.controller;

import com.javatechie.model.Customer;
import com.javatechie.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publisher-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher messagePublisher;

    @GetMapping(value = "/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try{
            messagePublisher.sendMessageToTopic(message);
            return ResponseEntity.ok("Message sent successfully");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to sent message");
        }

    }

    @PostMapping(value = "/publish")
    public ResponseEntity<?> publishCustomer(@RequestBody Customer customer) {
        try {
            messagePublisher.sendCustomerToTopic(customer);
            return ResponseEntity.ok("Customer sent successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to sent Customer");
        }
    }

}
