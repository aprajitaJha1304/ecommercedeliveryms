package com.example.deliveryms.dtos;// OrderInfo.java
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
public class OrderInfo {


    private Long id;
    private Long customerId;

}
