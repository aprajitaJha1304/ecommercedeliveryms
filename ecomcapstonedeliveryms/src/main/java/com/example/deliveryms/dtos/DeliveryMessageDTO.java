package com.example.deliveryms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryMessageDTO {
    private String orderID;
    private String deliveryID;
    private String status;


}
