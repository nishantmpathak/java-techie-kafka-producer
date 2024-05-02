package com.javatechie.model;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
public class Customer {
    private String id;
    private String name;
    private String contactNo;
    private String address;
}
